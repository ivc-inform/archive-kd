package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Control.Progressbar
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ProgressbarItem extends CanvasItem {
    var progressBar: Progressbar
    var minValue: Double
    var maxValue: Double
    var oneStep: Double

    def nextStep(): void
}

@js.native
abstract trait AbstractProgressbarItemCompanion extends AbstractCanvasItemCompanion {
}

