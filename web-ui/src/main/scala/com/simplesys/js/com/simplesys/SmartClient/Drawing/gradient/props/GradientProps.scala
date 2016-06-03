package com.simplesys.SmartClient.Drawing.gradient.props

import com.simplesys.SmartClient.Drawing.gradient.ColorStop
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class GradientProps extends AbstractClassProps {
    var colorStops: ScOption[Seq[ColorStopProps]] = ScNone
    var endColor: ScOption[CSSColor] = ScNone
    var id: ScOption[ID] = ScNone
    var startColor: ScOption[CSSColor] = ScNone
}
