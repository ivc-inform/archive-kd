package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.{IscArray, isc, simpleSyS}
import com.simplesys.SmartClient.sse.Sse._
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Callback
import org.scalajs.dom.raw.MessageEvent
import org.scalajs.dom.window

import scala.scalajs.js


trait ChannelStruct extends JSObject {
    val _channel: String
    val _listener: SseCallBack
    val _type: String
}

class Sse(val simpleSysContextPath: Option[String] = None) extends JSObject {
    type SseCallBack = js.Function1[MessageEvent, _]

    private val eventSources = IscArray.empty[EventSourceSS]

    def getEventSource(channel: String): IscArray[EventSourceSS] = IscArray(eventSources.filter(eventSource ⇒ eventSource.channels.map(_._channel).contains(channel)): _*)


    private def checkExistsSSE(): Boolean = {
        if (!window.hasOwnProperty("EventSource")) {
            isc error ("Ваш браузер не поддерживает технологию SSE, что делает невозможным автоматическое получение сообщений от сервера. (Данная задача находится в доработке.)")
            false
        } else
            true
    }

    private def checkSimpleSysContextPath(): Boolean = {
        if (simpleSysContextPath.isEmpty && simpleSyS.simpleSysContextPath.isEmpty) {
            isc error ("simpleSysContextPath undefined")
            false
        } else
            true

    }

    private def messagingSubscribeURL() = s"${simpleSysContextPath.getOrElse(simpleSyS.simpleSysContextPath)}Message/Subscribe"

    private def messagingSendURL() = s"${simpleSysContextPath.getOrElse(simpleSyS.simpleSysContextPath)}Message/Send"

    /*def removeEventSource(channel: String): Unit = {
        getEventSource(channel).filter(_.channels.length == 1)
    }*/

    private def reconnect(): Unit = {

    }

    def subscribe(channel: IscArray[String], listener: SseCallBack, subscribeCallback: Option[Callback] = None, `type`: String = "message", _reconnect: Boolean = true): Boolean = {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            true
        } else
            false
    }

    def unsubscribe(channel: String, unsubscribeCallback: Option[Callback] = None, _reconnect: Boolean = true): Boolean = {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            getEventSource(channel).map {
                eventSource ⇒
                    if (eventSource.channels.length == 1) {
                        eventSource.close()
                        eventSources.removeAt(eventSources.indexOf(eventSource))
                        isc.Log.
                    } else {
                        IscArray(eventSource.channels.filter(_._channel == channel): _*).forEach(channel ⇒ eventSource.removeEventListener(channel._type, channel._listener, false))
                        eventSource.channels.removeAt(eventSource.channels.map(_._channel).indexOf(channel))
                    }
                    unsubscribeCallback.foreach(isc.Class.fireCallback(_))
                    true
            }.filter(_ == true).length > 0
        } else
            false
    }
}

object Sse {
    type SseCallBack = js.Function1[MessageEvent, _]
}


