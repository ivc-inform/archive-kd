package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.isc
import com.simplesys.SmartClient.sse.Sse.SseCallBack
import com.simplesys.System.Types.URL
import com.simplesys.System.{JSDynamic, JSObject, JSUndefined}
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.raw.EventSource

import scala.scalajs.js

trait ChannelObject extends JSObject {
    val channel: String
    val listener: SseCallBack
    val `type`: String
    val isChannel: Boolean
}

trait ConfigurationEventSourceSS extends JSObject {
    val withCredentials: JSUndefined[Boolean] = js.undefined
}

class EventSourceSS(val channelObject: ChannelObject,
                    val messagingSubscribeURL: URL,
                    val configuration: Option[ConfigurationEventSourceSS] = None,
                    val useCapture: Option[Boolean] = None) extends JSObject {


    println(s"messagingSubscribeURL: $messagingSubscribeURL")

    private val urlBuilder = isc.URIBuilder.create(isc.Page.getURL(messagingSubscribeURL))
    println(s"urlBuilder.uri: ${urlBuilder}")


    val json = isc.JSON.encode(channelObject)
    println(s"json: $json")

    urlBuilder.setQueryParam("subscribedChannels", json)
    urlBuilder.setQueryParam("eventStream", true)

    val uri = urlBuilder.uri
    println(s"urlBuilder.uri: $uri")
    private val eventSource = if (configuration.isDefined) new EventSource(uri, configuration.get.asInstanceOf[JSDynamic]) else new EventSource(uri)

    eventSource.onmessage = (message: MessageEvent) ⇒ isc.Log.logWarn(s"Medssage on error: ${message.data}")

    useCapture.foreach(eventSource.addEventListener(channelObject.`type`, channelObject.listener, _))

    def close() = eventSource.close()

}
