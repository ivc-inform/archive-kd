package com.simplesys.SmartClient.Drawing.drawItem.props

import com.simplesys.SmartClient.Drawing.drawItem.DrawPath
import com.simplesys.SmartClient.Drawing.{DrawItem, DrawPathDefaults}
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.System.{IscArray, Point}
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js.ThisFunction2

class DrawPathProps extends DrawItemProps {
    type classHandler <: DrawPath

    var points: ScOption[Seq[Point]] = ScNone
    var defaults: ScOption[DrawPathDefaults] = ScNone
    override val `type`: ScOption[String]  = "DrawPath".opt
}
