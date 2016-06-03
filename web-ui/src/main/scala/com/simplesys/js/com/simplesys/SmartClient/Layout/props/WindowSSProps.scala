package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.{WindowSS, Window}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class WindowSSProps extends WindowProps {
    type classHandler <: WindowSS
    var headerIconPath: ScOption[String] = ScNone
    var autoPosition: ScOption[Boolean] = ScNone
}
