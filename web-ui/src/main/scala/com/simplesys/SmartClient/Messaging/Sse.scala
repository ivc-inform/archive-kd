package com.simplesys.SmartClient.Messaging

import com.simplesys.SmartClient.System.{AbstractClassCompanion, isc}
import com.simplesys.System.JSObject
import org.scalajs.dom.window

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
class Sse extends JSObject {
    def checkExistsSSE(): Boolean = {
        if (!window.hasOwnProperty("EventSource")) {
            isc error ("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)")
            false
        } else
            true
    }
}





