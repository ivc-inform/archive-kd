package com.simplesys.container.upload

import java.io.File
import java.util.Properties

import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings.newLine
import com.simplesys.container.java.JOrdDoc
import com.simplesys.container.scala.RecorderOrdDoc
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.json.{JsonLong, JsonObject, JsonString}
import com.simplesys.messages.ActorConfig.SendMessage
import com.simplesys.messages.Message
import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, HTMLContent, ServletContext}
import com.simplesys.util.DT
import oracle.jdbc.OracleConnection
import oracle.jdbc.dcn.{DatabaseChangeEvent, DatabaseChangeListener, DatabaseChangeRegistration}
import org.apache.commons.fileupload.ProgressListener
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

trait UploadData extends JSObject {
    val maxValue: JSUndefined[Double]
    val fileName: JSUndefined[String]
    val fileSize: JSUndefined[Double]
    val elapsedTime: JSUndefined[String]
}

trait ErrorStr extends JSObject {
    val message: JSUndefined[String]
    val stack: JSUndefined[String]
}

object UploadContainer {

    @RSTransfer(urlPattern = "/logic/arx_attatch/Upload")
    class UploadActor(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

        val requestData = new DSRequestDyn(request)
        val connection: OracleConnection = oraclePool.getConnection().asInstanceOf[OracleConnection]

        logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

        //val dataSet = DocizvopDS(ds)

        def receive = {
            case GetData => {
                val startTime = System.currentTimeMillis()

                val isMultipart = ServletFileUpload.isMultipartContent(request)
                response.ContentType = HTMLContent

                logger.debug(" \n\n\n----------------------------------------------------------- Parametrs -------------------------------------------------------------------")
                request.Parameters.foreach {
                    case (key, value) ⇒
                        logger.debug(s"key: $key, value: ${value.getOrElse("None")}")
                }
                logger.debug(" ------------------------------------------------------- End Parametrs1 -------------------------------------------------------------------")

                val idAttatch = request.Parameter("id").map(_.toLong)
                val channelMessageEndUpload = request.Parameter("p1")
                val channelMessageError = request.Parameter("p2")
                val channelMessageNextStep = request.Parameter("p3")
                val channelMessageMaxValue = request.Parameter("p4")
                val channelMessageRecordInBase = request.Parameter("p5")

                val dcr: Option[DatabaseChangeRegistration] = {
                    Try {
                        val prop = new Properties()
                        prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true")
                        val dcr = connection.registerDatabaseChangeNotification(prop)

                        dcr.addListener(new DatabaseChangeListener {
                            def onDatabaseChangeNotification(dce: DatabaseChangeEvent): Unit = {
                                ///?????
                            }
                        })
                        dcr
                    } match {
                        case Success(dcr) ⇒ Some(dcr)
                        case Failure(e) ⇒ None
                    }
                }

                val factory = new DiskFileItemFactory()
                val file = new File(s"./temp")

                if (!file.exists() || !file.isDirectory)
                    file.mkdir()

                factory setRepository file
                val upload: ServletFileUpload = new ServletFileUpload(factory)

                if (!isMultipart) {
                    channelMessageError.foreach(channelMessageError ⇒ SendMessage(Message(data = JsonObject("message" → JsonString("No file uploaded"), "stack" → JsonString("No file uploaded")), channels = channelMessageError)))
                    OutFailure(new RuntimeException("No file uploaded"))
                    connection.close()
                } else {
                    Try {

                        val progressListener = new ProgressListener() {
                            private var megaBytes = -1L
                            private var step = 1

                            private var firstStep = true

                            override def update(pBytesRead: Long, pContentLength: Long, pItems: Int): Unit = {
                                val stepSize = pContentLength / 100

                                if (firstStep) {
                                    channelMessageMaxValue.foreach(channelMessageMaxValue ⇒ SendMessage(Message(data = JsonObject("maxValue" → JsonLong(pContentLength)), channels = channelMessageMaxValue)))
                                    firstStep = false
                                }

                                if (pBytesRead >= stepSize * step) {
                                    step += 1
                                    channelMessageNextStep.foreach(channelMessageNextStep ⇒ SendMessage(Message(channels = channelMessageNextStep)))
                                }

                                if (pBytesRead == pContentLength)
                                    channelMessageRecordInBase.foreach(channelMessageRecordInBase ⇒ SendMessage(Message(channels = channelMessageRecordInBase)))
                            }
                        }

                        upload setProgressListener progressListener

                        upload.parseRequest(request).asScala.headOption.map { fi ⇒ new RecorderOrdDoc(idAttatch, dcr).writeOrdDoc(fi.getInputStream, fi.getName, fi.getContentType); fi }
                    }
                    match {
                        case Success(fi) ⇒
                            channelMessageEndUpload.foreach(channelMessageEndUpload ⇒ SendMessage(Message(data = JsonObject(
                                "elapsedTime" → JsonString(DT(System.currentTimeMillis() - startTime).toString),
                                "fileName" → JsonString(fi.get.getName),
                                "fileSize" → JsonLong(fi.get.getSize)
                            ), channels = channelMessageEndUpload)))

                            fi.foreach(_.delete())
                            Out("Ok")
                        case Failure(e) ⇒

                            channelMessageError.foreach(channelMessageError ⇒ SendMessage(Message(data = JsonObject("message" → JsonString(e.getMessage), "stack" → JsonString(e.getStackTraceString)), channels = channelMessageError)))
                            OutFailure(e)
                    }
                }

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    }
}
