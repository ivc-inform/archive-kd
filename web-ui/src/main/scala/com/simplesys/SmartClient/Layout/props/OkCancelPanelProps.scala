package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.OkCancelPanel
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction1}

class OkCancelPanelProps extends HPanelSSProps {
    type classHandler <: OkCancelPanel

    var okFunction: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var cancelFunction: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var okCaption: ScOption[String] = ScNone
    var cancelCaption: ScOption[String] = ScNone
    var ownerDestroy: ScOption[Boolean] = ScNone
    var ownerHide: ScOption[Boolean] = ScNone
    var timeoutClose: ScOption[Int] = ScNone
    var owner: ScOption[Canvas] = ScNone
}
