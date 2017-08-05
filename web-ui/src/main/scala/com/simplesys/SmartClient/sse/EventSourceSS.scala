package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSDynamic, JSObject, JSUndefined, jSUndefined}
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.raw.{Event, EventSource}

import scala.scalajs.js

trait ConfigurationEventSourceSS extends JSObject {
    val withCredentials: JSUndefined[Boolean] = js.undefined
}

class EventSourceSS(val url: String, val channels: IscArray[ChannelStruct], val configuration: ConfigurationEventSourceSS = null, val sse: Sse) extends JSObject {
    /*def this(url: String, channel: ChannelStruct, configuration: ConfigurationEventSourceSS = null) {
        this(url, IscArray(channel), configuration)
    }*/

    private val eventSource = new EventSource(url, configuration.asInstanceOf[JSDynamic])

    def close() = eventSource.close()

    def addEventListener(`type`: String, listener: js.Function1[MessageEvent, _], useCapture: Boolean = false): Unit = eventSource.addEventListener(`type`, listener, useCapture)

    def removeEventListener(`type`: String, listener: js.Function1[MessageEvent, _], useCapture: Boolean = false): Unit = eventSource.removeEventListener(`type`, listener, useCapture)
}
