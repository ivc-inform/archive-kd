package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.SmartClient.sse.Sse._
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Callback
import org.scalajs.dom.raw.MessageEvent
import org.scalajs.dom.window

import scala.scalajs.js
import scala.scalajs.js.Dictionary


trait Channel extends JSObject {
    val channel: String
    val callback: SseCallBack
    val event: Option[String]
}

class Sse extends JSObject {
    type SseCallBack = js.Function1[MessageEvent, _]

    private val channels = Dictionary.empty[Channel]

    private def checkExistsSSE(): Boolean = {
        if (!window.hasOwnProperty("EventSource")) {
            isc error ("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)")
            false
        } else
            true
    }

    private def reconnect(): Unit = {

    }


    def subscribe(channels: IscArray[String], _callback: SseCallBack, subscribeCallback: Option[Callback] = None, _event: Option[String] = None, _reconnect: Boolean = true): Unit = {
        val results: IscArray[Boolean] = IscArray(channels.map(channel ⇒ subscribe(channel, _callback, subscribeCallback, _event, false)): _*)
    }

    def subscribe(channel: String, _callback: SseCallBack, subscribeCallback: Option[Callback] = None, _event: Option[String] = None, _reconnect: Boolean = true): Boolean = {
        channels(channel) = new Channel {
            override val callback: SseCallBack = _callback
            override val event: Option[String] = _event
        }

        if (_reconnect)
            reconnect()
    }
}

object Sse {
    type SseCallBack = js.Function1[MessageEvent, _]
}


