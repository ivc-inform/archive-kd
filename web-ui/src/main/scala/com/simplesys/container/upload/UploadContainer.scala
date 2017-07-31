package com.simplesys.container.upload

import java.io.{File, InputStream, OutputStream}
import java.time.{Instant, LocalDateTime, ZoneId}
import java.util.Properties

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings.newLine
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}
import com.simplesys.container.scala.OrdDoc._
import com.simplesys.container.scala.{GetAttFile, OrdDoc, OrdSource}
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import com.simplesys.json.{JsonLong, JsonObject, JsonString}
import com.simplesys.messages.ActorConfig.SendMessage
import com.simplesys.messages.Message
import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, HTMLContent, ServletContext}
import com.simplesys.util.DT
import oracle.jdbc.dcn.{DatabaseChangeEvent, DatabaseChangeListener}
import oracle.jdbc.{OracleBlob, OracleConnection}
import org.apache.commons.fileupload.ProgressListener
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.apache.commons.io.IOUtils.copy

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

object UploadContainer {

    @RSTransfer(urlPattern = "/logic/arx_attatch/Upload")
    class UploadActor(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {
        val requestData = new DSRequestDyn(request)

        logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

        //val dataSet = DocizvopDS(ds)

        def receive = {
            case GetData => {
                implicit val connection: OracleConnection = ds.getConnection().asInstanceOf[OracleConnection]

                val startTime = System.currentTimeMillis()

                val isMultipart = ServletFileUpload.isMultipartContent(request)
                response.ContentType = HTMLContent

                logger.debug(" \n\n\n----------------------------------------------------------- Parametrs -------------------------------------------------------------------")
                request.Parameters.foreach {
                    case (key, value) ⇒
                        logger.debug(s"key: $key, value: ${value.getOrElse("None")}")
                }
                logger.debug(" ------------------------------------------------------- End Parametrs -------------------------------------------------------------------")

                val idAttatch = request.Parameter("idAttach").map(_.toLong)
                val channelMessageEndUpload = request.Parameter("channelMessageEndUpload")
                val channelMessageError = request.Parameter("channelMessageError")
                val channelMessageNextStep = request.Parameter("channelMessageNextStep")
                val channelMessageMaxValue = request.Parameter("channelMessageMaxValue")
                val channelMessageRecordInBase = request.Parameter("channelMessageRecordInBase")

                val dcr = {
                    connection setAutoCommit false

                    val prop = new Properties()
                    prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true")
                    val dcr = connection.registerDatabaseChangeNotification(prop)

                    dcr.addListener(new DatabaseChangeListener {
                        def onDatabaseChangeNotification(dce: DatabaseChangeEvent): Unit = {
                            ///?????
                        }
                    })
                    dcr
                }

                val factory = new DiskFileItemFactory()
                //val file = new File(s"./temp")
                val file = new File(s"./temp")

                if (!file.exists() || !file.isDirectory)
                    file.createNewFile()

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
                        upload.parseRequest(request).asScala.headOption.foreach {
                            fi ⇒
                                idAttatch.foreach {
                                    idAttatch ⇒
                                        val blob:OracleBlob = connection.createBlob().asInstanceOf[OracleBlob]
                                        val out: InputStream = blob.getBinaryStream()
                                        val in: OutputStream = fi.getOutputStream
                                        copy(in, out)
                                        
                                        def getEmptySource =
                                            new OrdSource {
                                                override val srcName: Option[String] = Some(fi.getName)
                                                override val srcLocation: Option[String] = None
                                                override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                                                override val local: Option[BigDecimal] = None
                                                override val srcType: Option[String] = Some("FILE")
                                                override val localData: Option[OracleBlob] = Some(blob)
                                            }

                                        val ordDoc: JOrdDoc = GetAttFile.getOrdDoc(idAttatch) match {
                                            case Some(ordDoc) ⇒
                                                val _source = ordDoc.source match {
                                                    case Some(source) ⇒
                                                        new OrdSource {
                                                            override val srcName: Option[String] = Some(fi.getName)
                                                            override val srcLocation: Option[String] = None
                                                            override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                                                            override val local: Option[BigDecimal] = source.local
                                                            override val srcType: Option[String] = source.srcType
                                                            override val localData: Option[OracleBlob] = Some(blob)
                                                        }
                                                    case None ⇒
                                                        getEmptySource
                                                }

                                                new OrdDoc {
                                                    override val comments: Option[String] = Some("Updated by UploadContainer !!")
                                                    override val format: Option[String] = ordDoc.format
                                                    override val source: Option[OrdSource] = Some(_source)
                                                    override val mimeType: Option[String] = Some(fi.getContentType)
                                                    override val contentLength: Option[BigDecimal] = Some(fi.getSize)
                                                }

                                            case None ⇒
                                                new OrdDoc {
                                                    override val comments: Option[String] = Some("Inserted by UploadContainer !!")
                                                    override val format: Option[String] = None
                                                    override val source: Option[OrdSource] = Some(getEmptySource)
                                                    override val mimeType: Option[String] = Some(fi.getContentType)
                                                    override val contentLength: Option[BigDecimal] = Some(fi.getSize)
                                                }
                                        }

                                        prepareStatement(connection, "UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?") {
                                            preparedStatement ⇒
                                                preparedStatement.setObject(1, ordDoc)
                                                preparedStatement.setLong(2, idAttatch)
                                                preparedStatement.executeUpdate()

                                                connection unregisterDatabaseChangeNotification dcr
                                        }
                                }
                        }
                    }
                    match {
                        case Success(out) ⇒
                            channelMessageEndUpload.foreach(channelMessageEndUpload ⇒ SendMessage(Message(data = JsonObject("elapsedTime" → JsonString(DT(System.currentTimeMillis() - startTime).toString)),
                                channels = channelMessageEndUpload)))

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
