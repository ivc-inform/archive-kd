package com.simplesys.container.upload

import java.io.File
import java.util.Properties

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.json.{JsonLong, JsonObject, JsonString}
import com.simplesys.messages.ActorConfig.SendMessage
import com.simplesys.messages.Message
import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, HTMLContent, ServletContext}
import com.simplesys.util.DT
import oracle.jdbc.OracleConnection
import oracle.jdbc.dcn.{DatabaseChangeEvent, DatabaseChangeListener}
import oracle.jdbc.pool.OracleDataSource
import org.apache.commons.fileupload.ProgressListener
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload

import scala.collection.JavaConverters._
import scala.compat.Platform.EOL
import scala.util.{Failure, Success, Try}
import scala.xml.{Elem, Node, Null}

object UploadServlet {
    implicit def node2Elem(node: Node): Elem = Elem(node.prefix, node.label, Null, node.scope, true, node.child: _*)

    implicit class NodeOpt(node: Node) {
        def addChild(newChild: Elem): Elem = {
            val elem: Elem = node
            elem.copy(child = elem.child ++ newChild.child)
        }
    }

    implicit class ResponceOpt(response: HttpServletResponse) {
        def makePageMessage(msg: String) = {
            response.Print(
                //@formatter:off
                <html>
                    <head><title>Servlet upload</title></head>
                    <body><p>{msg}</p></body>
                </html>
                //@formatter:on
            )
        }
    }
}

@RSTransfer(urlPattern = "/TestUploadServlet")
class TestUploadServlet(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {
    private val filePath: String = "web-ui/target/upload"

    def receive = {
        case GetData => {

            val startTime = System.currentTimeMillis()

            val isMultipart = ServletFileUpload.isMultipartContent(request)
            response.ContentType = HTMLContent

            println(" \n\n\n----------------------------------------------------------- Parametrs -------------------------------------------------------------------")
            request.Parameters.foreach {
                case (key, value) ⇒
                    println(s"key: $key, value: ${value.getOrElse("None")}")
            }
            println(" ------------------------------------------------------- End Parametrs -------------------------------------------------------------------")

            val channelMessageEndUpload = request.Parameter("channelMessageEndUpload")
            val channelMessageError = request.Parameter("channelMessageError")
            val channelMessageNextStep = request.Parameter("channelMessageNextStep")
            val channelMessageMaxValue = request.Parameter("channelMessageMaxValue")
            val channelMessageRecordInBase = request.Parameter("channelMessageRecordInBase")

            import UploadServlet._

            if (!isMultipart) {
                response.makePageMessage("No file uploaded")
            } else {

                val factory = new DiskFileItemFactory()

                val file = new File(s"./temp")
                if (!file.exists() || !file.isDirectory)
                    file.mkdir()

                factory setRepository file
                val upload: ServletFileUpload = new ServletFileUpload(factory)

                val connection = Option(oraclePool.getConnection.asInstanceOf[OracleConnection])

                val dcr = connection.map {
                    conn ⇒
                        conn setAutoCommit false

                        val prop = new Properties()
                        prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true")
                        val dcr = conn.registerDatabaseChangeNotification(prop)

                        dcr.addListener(new DatabaseChangeListener {
                            def onDatabaseChangeNotification(dce: DatabaseChangeEvent): Unit = {
                                println(dce.toString)
                            }
                        })
                        dcr
                }

                Try {

                    //@formatter:off
                    var out: Node = <html><head><title>Servlet upload</title></head></html>
                    //@formatter:on

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
                    var body = <body></body>

                    println("point 1")
                    upload.parseRequest(request).asScala.foreach {
                        fi ⇒
                            println("point 2")
                            println(fi.isFormField)

                            if (!fi.isFormField) {
                                val fieldName = fi.getFieldName
                                println(s"fieldName: $fieldName")

                                val fileName = fi.getName
                                println(s"fileName: $fileName")

                                val contentType = fi.getContentType
                                println(s"contentType: $contentType")

                                val isInMemory = fi.isInMemory
                                println(s"isInMemory: $isInMemory")

                                val sizeInBytes = fi.getSize
                                println(s"sizeInBytes: $sizeInBytes")

                                //val file = new File(filePath + "//" + fileName)
                                //fi write file

                                println(s"before inputStream")
                                val inputStream = fi.getInputStream()
                                println(s"after inputStream")

                                val sql = "INSERT INTO TEST_UPLOAD_FILES VALUES(?, ?, ?)"

                                connection.foreach {
                                    conn ⇒
                                        val pstmt = conn prepareStatement sql

                                        println(s"before pstmt.setLong(1, 1L); elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")
                                        pstmt.setLong(1, 1L)
                                        println(s"after pstmt.setLong(1, 1L); elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                        println(s"before pstmt.setString(2, $fileName)")
                                        pstmt.setString(2, fileName)
                                        println(s"after pstmt.setString(2, $fileName) ; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                        println(s"before pstmt.setBlob(3, inputStream, ${fi.getSize})")

                                        pstmt.setBlob(3, inputStream, fi.getSize)

                                        println(s"after pstmt.setBlob(3, inputStream, ${fi.getSize}) ; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                        println(s"before pstmt.executeUpdate")

                                        pstmt.executeUpdate()

                                        println(s"post pstmt.executeUpdate; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                        conn.commit()
                                        
                                        fi.delete()

                                        val elapsedTime = System.currentTimeMillis() - startTime
                                        println(s"after conn.commit() elapsedTime for $fileName : ${DT(elapsedTime).toString}")

                                        //@formatter:off
                                        body = body addChild <h2>{s"Uploaded File : $fileName" + s"; elapsedTime : ${DT(elapsedTime).toString}"}</h2>
                                        //@formatter:on
                                }
                            }
                    }

                    out = out addChild body
                    out
                } match {
                    case Success(out) ⇒
                        connection.foreach {
                            conn ⇒
                                dcr.foreach(conn unregisterDatabaseChangeNotification _)
                                conn.close()
                        }

                        channelMessageEndUpload.foreach(channelMessageEndUpload ⇒ SendMessage(Message(data = JsonObject("elapsedTime" → JsonString(DT(System.currentTimeMillis() - startTime).toString)), channels = channelMessageEndUpload)))

                        Out("Ok")
                    case Failure(e) ⇒

                        channelMessageError.foreach(channelMessageError ⇒ SendMessage(Message(data = JsonObject("message" → JsonString(e.getMessage), "stack" → JsonString(e.getStackTrace().mkString("", EOL, EOL))), channels = channelMessageError)))
                        OutFailure(e)
                        connection.foreach(_.close())
                }
            }

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
