package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.Drawing.{DrawCurveDefaults, DrawKnob}
import com.simplesys.SmartClient.System.Point
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class DrawCurveProps extends DrawItemProps {
    var c1Knob: ScOption[DrawKnob] = ScNone
    var c2Knob: ScOption[DrawKnob] = ScNone
    var controlPoint1: ScOption[Point] = ScNone
    var controlPoint2: ScOption[Point] = ScNone
    var endPoint: ScOption[Point] = ScNone
    var startPoint: ScOption[Point] = ScNone
    var defaults: ScOption[DrawCurveDefaults] = ScNone
    override val `type`: ScOption[String] = "DrawCurve".opt
}
