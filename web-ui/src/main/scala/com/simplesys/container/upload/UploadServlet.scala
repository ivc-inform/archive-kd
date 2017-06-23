package com.simplesys.container.upload

import java.io.File
import java.util.Properties
import javax.servlet.annotation.WebServlet

import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.HTMLContent
import com.simplesys.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import com.simplesys.util.DT
import oracle.jdbc.OracleConnection
import oracle.jdbc.dcn.{DatabaseChangeEvent, DatabaseChangeListener}
import oracle.jdbc.pool.OracleDataSource
import org.apache.commons.fileupload.ProgressListener
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload

import scala.collection.JavaConverters._
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

@WebServlet(urlPatterns = Array("/UploadServlet"))
class UploadServlet extends HttpServlet {
    private val filePath: String = "web-ui/target/upload"

    override protected def DoPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
        val startTime = System.currentTimeMillis()

        val isMultipart = ServletFileUpload.isMultipartContent(request)
        response.ContentType = HTMLContent

        import UploadServlet._

        if (!isMultipart) {
            response.makePageMessage("No file uploaded")
        } else {

            val factory = new DiskFileItemFactory()

            val file = new File("./temp")
            factory setRepository file
            val upload: ServletFileUpload = new ServletFileUpload(factory)

            val ds = new OracleDataSource

            //ds.setURL("jdbc:oracle:thin:@//orapg.simplesys.lan:1521/test")
            ds.setURL("jdbc:oracle:thin:@//localhost:1521/test")

            ds.setUser("eakd")
            ds.setPassword("eakd")

            val conn = Option(ds.getConnection.asInstanceOf[OracleConnection])

            val dcr = conn.map {
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

                import UploadServlet._

                val progressListener = new ProgressListener() {
                    private var megaBytes = -1L

                    override def update(pBytesRead: Long, pContentLength: Long, pItems: Int): Unit = {

                        val mBytes = pBytesRead / 1024 * 1024 * 100

                        if (megaBytes != mBytes) {
                            megaBytes = mBytes
                            //println(s"We are currently reading item $pItems")

                            if (pContentLength == -1L)
                                println(s"So far, $pBytesRead bytes have been read.")
                            else
                                println(s"So far, $pBytesRead of $pContentLength bytes have been read.")
                        }
                    }
                }


                //upload setProgressListener progressListener
                var body = <body></body>
                upload.parseRequest(request).asScala.foreach {
                    fi ⇒
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

                            conn.foreach {
                                conn ⇒
                                    val pstmt = conn prepareStatement sql

                                    println(s"before pstmt.setLong(1, 1L); elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")
                                    pstmt.setLong(1, 1L)
                                    println(s"after pstmt.setLong(1, 1L); elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                    println(s"before pstmt.setString(2, $fileName)")
                                    pstmt.setString(2, fileName)
                                    println(s"after pstmt.setString(2, $fileName) ; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                    println(s"before pstmt.setBlob(3, inputStream, ${fi.getSize})")
                                    //pstmt.setBinaryStream(3, inputStream)
                                    pstmt.setBlob(3, inputStream, fi.getSize)
                                    println(s"after pstmt.setBlob(3, inputStream, ${fi.getSize}) ; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                    println(s"before pstmt.executeUpdate")
                                    pstmt.execute()
                                    println(s"post pstmt.executeUpdate; elapsedTime: ${DT(System.currentTimeMillis() - startTime)}")

                                    conn.commit()

                                    val elapsedTime = System.currentTimeMillis() - startTime
                                    println(s"after conn.commit() elapsedTime for $fileName : ${DT(elapsedTime).toString}")

                                    //@formatter:off
                                    body = body addChild <h2>{s"Uploaded File : $fileName" + s"; elapsedTime for $fileName : ${DT(elapsedTime).toString}"}</h2>
                                    //@formatter:on
                            }
                        }
                }

                out = out addChild body
                out
            } match {
                case Success(out) ⇒
                    file.delete()
                    conn.foreach {
                        conn ⇒
                            dcr.foreach(conn unregisterDatabaseChangeNotification _)
                            conn.close()
                    }
                    response Print out

                case Failure(e) ⇒
                    file.delete()
                    conn.foreach(_.close())
                    response.makePageMessage(e.getMessage)
            }
        }
    }
}
