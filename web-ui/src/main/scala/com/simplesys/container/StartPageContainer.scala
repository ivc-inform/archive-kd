package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.app.http.StartPage
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, ServletActor, ServletContext}
import com.simplesys.common._

@RSTransfer(urlPattern = "/StartPage")
class StartPageContainer(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActor {

    def receive = {
        case GetData => {
            val textHTML = new StartPage(scalatags.Text)

            val html = "<!DOCTYPE html>" + textHTML.bodyHTML.render.unEscape
            //println(html)

            Out(html)

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    }
}
