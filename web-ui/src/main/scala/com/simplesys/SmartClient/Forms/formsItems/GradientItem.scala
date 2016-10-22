package com.simplesys.SmartClient.Forms.formsItems

import scala.scalajs.js

@js.native
trait GradientItem extends FormItemWithButtons {

    import com.simplesys.System.JSUndefined
    import com.simplesys.System.Types._
    import com.simplesys.option.{ScNone, ScOption}

    var startColor: JSUndefined[CSSColor]
    var endColor: JSUndefined[CSSColor]
    var direction: JSUndefined[Double]
}

@js.native
abstract trait AbstractGradientItemCompanion extends AbstractFormItemWithButtonsCompanion {
}


