package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.isc
import com.simplesys.SmartClient.sse.Sse.SseCallBack
import com.simplesys.System.Types.URL
import com.simplesys.System.{JSDynamic, JSObject, JSUndefined}
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.raw.EventSource

import scala.scalajs.js

trait ChannelStruct extends JSObject {
    val channel: String
    val listener: SseCallBack
    val `type`: String
    val isChannel: Boolean
}

trait ConfigurationEventSourceSS extends JSObject {
    val withCredentials: JSUndefined[Boolean] = js.undefined
}

class EventSourceSS(val channelObject: ChannelStruct,
                    val messagingSubscribeURL: URL,
                    val configuration: Option[ConfigurationEventSourceSS] = None,
                    val useCapture: Option[Boolean] = None) extends JSObject {


    private val urlBuilder = isc.URIBuilder.create(isc.Page.getURL(messagingSubscribeURL))
    urlBuilder.setQueryParam("subscribedChannels", isc.JSON.encode(channelObject))
    urlBuilder.setQueryParam("eventStream", true)

    private val eventSource = new EventSource(urlBuilder.uri, configuration.asInstanceOf[JSDynamic])
    eventSource.onmessage = (message: MessageEvent) ⇒ isc.Log.logWarn(s"Medssage on error: ${message.data}")
    useCapture.foreach(eventSource.addEventListener(channelObject.`type`, channelObject.listener, _))

    def close() = eventSource.close()

    def removeEventListener(`type`: String, listener: js.Function1[MessageEvent, _], useCapture: Boolean = false): Unit = eventSource.removeEventListener(`type`, listener, useCapture)
}
