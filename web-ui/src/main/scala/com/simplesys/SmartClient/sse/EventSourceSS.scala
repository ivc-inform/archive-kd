package com.simplesys.SmartClient.sse

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSDynamic, JSObject, JSUndefined, jSUndefined}
import org.scalajs.dom.raw.EventSource

trait ConfigurationEventSourceSS extends JSObject {
    val withCredentials: JSUndefined[Boolean] = jSUndefined
}

class EventSourceSS(val url: String, val channels: IscArray[Channel], val configuration: ConfigurationEventSourceSS = null) extends JSObject {
    def this(url: String, channel: Channel, configuration: ConfigurationEventSourceSS = null) = this(url, IscArray(channel), configuration)

    private val eventSource = new EventSource(url, configuration.asInstanceOf[JSDynamic])
}
