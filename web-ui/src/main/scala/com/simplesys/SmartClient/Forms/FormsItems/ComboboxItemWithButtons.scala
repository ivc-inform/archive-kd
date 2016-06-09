package com.simplesys.SmartClient.Forms.FormsItems

import com.simplesys.SmartClient.Control.IButtonSS
import com.simplesys.SmartClient.System.IscArray

import scala.scalajs.js

@js.native
trait ComboboxItemWithButtons extends CanvasItem {
    val buttonsProperties:IscArray[IButtonSS]
}

