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
    val _channel: String
    val _listener: SseCallBack
    val _type: String
}

class Sse extends JSObject {
    type SseCallBack = js.Function1[MessageEvent, _]

    private val eventSources = IscArray.empty[EventSourceSS]
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


    def subscribe(channels: IscArray[String], listener: SseCallBack, subscribeCallback: Option[Callback] = None, `type`: String = "message", _reconnect: Boolean = true): Unit = {
        val results: IscArray[Boolean] = IscArray(channels.map(channel ⇒ subscribe(channel, listener, subscribeCallback, `type`, false)): _*)
    }

    def subscribe(channel: String, listener: SseCallBack, subscribeCallback: Option[Callback] = None, `type`: String = "message", _reconnect: Boolean = true): Boolean = {
        if (checkExistsSSE()) {
            channels(channel) = new Channel {
                override val _channel: String = _channel
                override val _listener: SseCallBack = _listener
                override val _type: String = _type
            }

            if (_reconnect)
                reconnect()
        }
        true
    }
}

object Sse {
    type SseCallBack = js.Function1[MessageEvent, _]
}


