package com.simplesys.container

import javax.servlet.annotation.WebServlet

import com.simplesys.annotation.RSTransfer
import com.simplesys.common.Strings._
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn}
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.servlet._
import com.simplesys.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import com.simplesys.xml.factory.XMLLoader

object LoaderContainer {
    @RSTransfer(urlPattern = "/isomorphic/LoadSchemas")
    class LoaderSchemas(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends ServletActorDyn with XMLLoader {
        val requestData = new DSRequestDyn(request)

        def receive = {
            case GetData => {
                val data = requestData.Data

                logger debug s"data: ${newLine + data.toPrettyString}"

                servletContext.Attribute("schemaList") match {
                    case Some(schemaList: DSResponseDyn) =>
                        Out(schemaList)

                    case Some(_) =>
                        Out("")

                    case None =>
                        Out("")
                }

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    }
}

@WebServlet(urlPatterns = Array("/isomorphic/LoadSchemas1"))
class FirstServlet extends HttpServlet {

    override def DoPost(request: HttpServletRequest, response: HttpServletResponse) {
        response PrintAndFlush "123456"
    }
}



