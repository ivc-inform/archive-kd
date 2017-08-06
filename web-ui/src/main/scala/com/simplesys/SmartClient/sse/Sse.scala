package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.{IscArray, isc, simpleSyS}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Callback
import org.scalajs.dom.raw.MessageEvent
import org.scalajs.dom.window

import scala.scalajs.js


class Sse(val simpleSysContextPath: Option[String] = None) extends JSObject {
    type SseCallBack = js.Function1[MessageEvent, _]

    private val eventSources = IscArray.empty[EventSourceSS]

    def getEventSource(channel: String): IscArray[EventSourceSS] = IscArray(eventSources.filter(_.channelObject.channel == channel): _*)


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

    def subscribe(_channel: String, _listener: SseCallBack, subscribeCallback: Option[Callback] = None, _type: String = "message", _reconnect: Boolean = true): Boolean = {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            unsubscribe(_channel)
            eventSources.push(new EventSourceSS(new ChannelObject {
                override val isChannel: Boolean = true
                override val channel: String = _channel
                override val listener: SseCallBack = _listener
                override val `type`: String = _type
            }, messagingSubscribeURL()))

            subscribeCallback.foreach(isc.Class.fireCallback(_))
            true
        } else
            false
    }

    def unsubscribe(channel: String, unsubscribeCallback: Option[Callback] = None): Boolean = {
        if (checkExistsSSE() && checkSimpleSysContextPath()) {
            getEventSource(channel).map {
                eventSource ⇒
                    eventSource.close()
                    eventSources.removeAt(eventSources.map(_.channelObject.channel).indexOf(channel))
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


