package com.simplesys.container.upload.servlet

import com.simplesys.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import org.apache.commons.fileupload.servlet.ServletFileUpload

class UploadServlet extends HttpServlet{
    private val filePath: String = "web-ui/target/upload"


    override protected def DoPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
      val  isMultipart = ServletFileUpload.isMultipartContent(request.proxy)
    }
}
