package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.System.Point
import com.simplesys.System.JSObject
import com.simplesys.System.Types.CSSColor

import scala.scalajs.js

@js.native
trait Shadow extends JSObject {
    val blur: Int
    val color: CSSColor
    val offset: Point
}

