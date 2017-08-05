package com.simplesys.SmartClient.Messaging

import com.simplesys.SmartClient.System.{AbstractClassCompanion, IscArray, isc}
import com.simplesys.System.JSObject
import org.scalajs.dom.raw.{EventSource, MessageEvent}
import org.scalajs.dom.window

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic
import scala.scalajs.js.{Dictionary, |}
import Sse._
import com.simplesys.System.Types.Callback


trait Channel extends JSObject {
    val callback: SseCallBack
}

class Sse extends JSObject

object Sse {
    type SseCallBack = js.Function1[MessageEvent, _]

    private val channels = Dictionary.empty[SseCallBack]

    @JSExportStatic
    def checkExistsSSE(): Boolean = {
        if (!window.hasOwnProperty("EventSource")) {
            isc error ("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)")
            false
        } else
            true
    }


    def subscribe(channel: String, callback: SseCallBack, subscribeCallback: Option[Callback] = None, event: Option[String] = None): Unit = {
        //        val eventSource = new EventSource("")
        //        eventSource.onmessage
    }
}


