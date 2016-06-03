package com.simplesys.filter

import akka.actor.ActorSystem
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn._
import com.simplesys.isc.templates.logJSActor
import com.simplesys.json.JsonObject
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}

object SuccesAuthentication {
    def apply(request: HttpServletRequest, response: HttpServletResponse, login: String, id: Long,captionUser: String, codeGroup:String)(implicit system: ActorSystem) {
        if (!response.IsCommitted)
            response.FlushBuffer()

        val result = new RPCResponseDyn {
            Data = JsonObject(
                "status" -> statusSuccess,
                "login" -> login,
                "userId" -> id,
                "captionUser" -> captionUser,
                "codeGroup" -> codeGroup,
                "simpleSysContextPath" -> request.ContextPath
            )
        }

        response Print (logJSActor(result))
    }
}

object FailureAuthentication {
    def apply(request: HttpServletRequest, response: HttpServletResponse, errorMessage: String)(implicit system: ActorSystem) {
        if (!response.IsCommitted)
            response.FlushBuffer()

        val result = new RPCResponseDyn {
            Data = JsonObject(
                "status" -> statusLoginIncorrect,
                "errorMessage" -> errorMessage
            )
        }

        response Print (logJSActor(result))
    }
}

object LoginRequiredResponse {
    def apply(request: HttpServletRequest, response: HttpServletResponse )(implicit system: ActorSystem) {
        if (!response.IsCommitted)
            response.FlushBuffer()

        val result = new RPCResponseDyn {
            Data = JsonObject(
                "status" -> statusLoginRequired,
                "errorMessage" -> "Требуется аутентификация !"
            )
        }

        response Print (logJSActor(result))
    }
}
