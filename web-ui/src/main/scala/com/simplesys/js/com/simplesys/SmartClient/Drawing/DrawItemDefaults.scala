package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.AdvancedCriteria
import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.SmartClient.Drawing.gradient.SimpleGradient
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System.Types.ArrowStyle.ArrowStyle
import com.simplesys.System.Types.LinePattern.LinePattern
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{ThisFunction0, ThisFunction2, |}

@js.native
trait DrawItemDefaults extends JSObject {
    var title: String
    var keepInParentRect: Boolean
    var lineWidth: Int
    var titleLabelProperties: DrawLabel
    var contextMenu: MenuSS
    var criteria: AdvancedCriteria
    var linePattern: LinePattern
    var onDragStop: ThisFunction2[DrawItem, Int, Int, _]
    var resized: ThisFunction0[DrawItem, _]
    var enable4Connect: Boolean
    var canDrag: JSUndefined[Boolean]
}

@js.native
trait DrawGroupDefaults extends DrawItemDefaults {
    var top: Int
    var left: Int
    var width: Int
    var height: Int
}

@js.native
trait DrawRectDefaults extends DrawItemDefaults {
    var top: Int
    var left: Int
    var width: Int
    var height: Int
    var rounding: Double
    var fillGradient: SimpleGradient
    var shadow: Shadow
}

@js.native
trait DrawLineDefaults extends DrawItemDefaults {
    var startPoint: Point
    var endPoint: Point
    var endArrow: ArrowStyle
    var startArrow: ArrowStyle
    var points: IscArray[Point]
}

@js.native
trait DrawCurveDefaults extends DrawItemDefaults {
    var endPoint: Point
    var startPoint: Point
    var endArrow: ArrowStyle
    var startArrow: ArrowStyle
    var controlPoint1: Point
    var controlPoint2: Point
}

@js.native
trait DrawPathDefaults extends DrawItemDefaults {
    var endPoint: Point
    var startPoint: Point
    var endArrow: ArrowStyle
    var startArrow: ArrowStyle
    var controlPoint1: Point
    var controlPoint2: Point
}

@js.native
trait DrawLinePathDefaults extends DrawItemDefaults {
    var endPoint: Point
    var startPoint: Point
    var endArrow: ArrowStyle
    var startArrow: ArrowStyle
    var controlPoint1: Point
    var controlPoint2: Point
    var points: IscArray[Point]
}

@js.native
trait DrawOvalDefaults extends DrawItemDefaults {
    var top: Int
    var left: Int
    var width: Int
    var height: Int
    var fillGradient: SimpleGradient
    var shadow: Shadow
}

@js.native
trait DrawDiamondDefaults extends DrawItemDefaults {
    var top: Int
    var left: Int
    var width: Int
    var height: Int
    var fillGradient: SimpleGradient
    var shadow: Shadow
}

@js.native
trait DrawTriangleDefaults extends DrawItemDefaults {
    var points: IscArray[Point]
    var fillGradient: SimpleGradient
    var shadow: Shadow
}

@js.native
trait DrawLabelDefaults extends DrawItemDefaults {
    var top: Int
    var left: Int
    var contents: String
    var alignment: com.simplesys.System.Types.Alignment.Alignment
    var fillGradient: SimpleGradient
    var shadow: Shadow
}

@js.native
trait DrawPaneDefaults extends DrawItemDefaults {
    var canFocus: Boolean
    var width: Int | String
    var height: Int | String
    var gradients: IscArray[SimpleGradient]
}

@ScalaJSDefined
trait PropEditorLiveObject extends JSObject {
    var ID: JSUndefined[String]
    var canDrag: JSUndefined[Boolean]
    var cursor: JSUndefined[String]
    var endArrow: JSUndefined[String]
    var fillColor: JSUndefined[String]
    var fillGradient: JSUndefined[JSObject]
    var fillOpacity: JSUndefined[Double]
    val lineCap: JSUndefined[String]
    val lineColor: JSUndefined[String]
    val lineOpacity: JSUndefined[Double]
    val linePattern: JSUndefined[String]
    val lineWidth: JSUndefined[Int]
    val shadow: JSUndefined[JSObject]
    val criteria: JSUndefined[JSObject]
    val startArrow: JSUndefined[String]
    val title: JSUndefined[String]
    val rounding: JSUndefined[Double]
    val keepInParentRect: JSUndefined[Boolean]
    val multiline: JSUndefined[Boolean]
}
