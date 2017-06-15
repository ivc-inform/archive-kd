package com.simplesys.container.upload

import java.io.File
import javax.servlet.annotation.WebServlet

import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.HTMLContent
import com.simplesys.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
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
        val isMultipart = ServletFileUpload.isMultipartContent(request)
        response.ContentType = HTMLContent

        import UploadServlet._

        if (!isMultipart) {
            response.makePageMessage("No file uploaded")
        } else {

            val factory = new DiskFileItemFactory()

            factory setRepository new File("./temp")
            val upload = new ServletFileUpload(factory)

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

                upload setProgressListener progressListener

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

                            val file = new File(filePath + "//" + fileName)
                            fi write file

                            println(s"write file: ${file.getAbsolutePath}")

                            //@formatter:off
                            body = body addChild <h2>{s"Uploaded File: ${file.getAbsolutePath}"}</h2>
                            //@formatter:on
                        }
                }

                out = out addChild body
                out
            } match {
                case Success(out) ⇒
                    response Print out

                case Failure(e) ⇒
                    response.makePageMessage(e.getMessage)
            }
        }
    }
}
