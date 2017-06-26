package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.http.StartPage
import com.simplesys.common._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletActor, ServletContext}

//http://localhost:8083/archive-kd/StartPage
@RSTransfer(urlPattern = "/StartPage")
class StartPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val textHTML = new StartPage(scalatags.Text)

            val html = "<!DOCTYPE html>" +
              textHTML.bodyHTML(
                  "CreateSimpleTypes();" +
                    "CreateSmartClientJS();" +
                    "CreateAppJS();" +
                    "GetUIContent();",
                  false
              ).render.unEscape


            logger debug html
            Out(html)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}

//http://localhost:8083/archive-kd/StartUploadPage
@RSTransfer(urlPattern = "/StartUploadPage")
class StartUploadPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val textHTML = new StartPage(scalatags.Text)

            val html = "<!DOCTYPE html>" +
              textHTML.bodyHTML(
                  "CreateSimpleTypes();" +
                    "CreateSmartClientJS();" +
                    "CreateAppJS();" +
                    "GetUploadExampleUIContent();",
                  false
              ).render.unEscape


            logger debug html
            Out(html)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
