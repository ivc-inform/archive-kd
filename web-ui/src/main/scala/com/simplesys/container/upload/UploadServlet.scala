package com.simplesys.container.upload

import java.io.File

import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.HTMLContent
import com.simplesys.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}
import scala.xml.{Elem, Node}

object UploadServlet {
    implicit class NodeOpt(node: Node) {
        def addChild(newChild: Node) = node match {
            case Elem(prefix, label, attribs, scope, child@_*) =>
                Elem(prefix, label, attribs, scope, false, child ++ newChild: _*)
            case _ =>
                new RuntimeException("Can only add children to elements!")
        }
    }
}

class UploadServlet extends HttpServlet {
    private val filePath: String = "web-ui/target/upload"
    private val maxMemSize = 4 * 1024
    private val maxFileSize = 50 * 1024


    override protected def DoPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
        val isMultipart = ServletFileUpload.isMultipartContent(request)
        response.ContentType = HTMLContent

        if (!isMultipart) {
            response.Print(
                <html>
                    <head>
                        <title>Servlet upload</title>
                    </head>
                    <body>
                        <p>No file uploaded</p>
                    </body>
                </html>
            )
        } else {

            val factory = new DiskFileItemFactory()

            factory.setSizeThreshold(maxMemSize)
            factory.setRepository(new File("./temp"))

            val upload = new ServletFileUpload(factory)
            upload setSizeMax maxFileSize

            Try {
                val out: Node =
                    <html>
                        <head>
                            <title>Servlet upload</title>
                        </head>
                    </html>

                import UploadServlet._

                val body: Node = <body></body>
                upload.parseRequest(request).asScala.foreach {
                    fi ⇒
                        if (!fi.isFormField) {
                            val fieldName = fi.getFieldName
                            val fileName = fi.getName
                            val contentType = fi.getContentType
                            val isInMemory = fi.isInMemory
                            val sizeInBytes = fi.getSize

                            fi write (if (fileName.lastIndexOf("\\") >= 0)
                                new File(filePath + fileName.substring(fileName.lastIndexOf("\\")))
                            else
                                new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1))
                              )

                            //@formatter:off
                            body addChild <br>{s"Uploaded File: $fileName"}</br>
                            //@formatter:on
                        }
                }
                out addChild body
            } match {
                case Success(_) ⇒
                case Failure(e) ⇒
            }
        }
    }
}
