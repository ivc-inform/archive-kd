package com.simplesys.js.com.simplesys.SmartClient.Forms.FormsItems

import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.SCImgURL
import com.simplesys.js.com.simplesys.SmartClient.Control.IButtonSS
import com.simplesys.js.com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, |}

@js.native
trait FormItemWithButton extends ComboboxItemWithButtons {
    var button: JSUndefined[IButtonSS]
    var iconButton: JSUndefined[SCImgURL]
    var clickButton: JSUndefined[ThisFunction0[IButtonSS, Boolean]]
}

