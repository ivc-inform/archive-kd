package com.simplesys.SmartClient.System

import com.simplesys.System.Types.void
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js

@js.native
object window extends JSObject {
    def setTimeout(function: js.Function, delay: Long, args: JSAny*): Int = js.native
    def clearTimeout(timerId: Int): void = js.native

    def setInterval(function: js.Function, delay: Long, args: JSAny*): Int = js.native
    def clearInterval(timerId: Int): void = js.native
}
