package com.simplesys.SmartClient.Drawing.gradient

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{CSSColor, ID}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Gradient extends JSObject {
    var colorStops: IscArray[ColorStop]
    var endColor: CSSColor
    var id: ID
    var startColor: CSSColor
}

