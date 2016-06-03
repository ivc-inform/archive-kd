package com.simplesys.SmartClient.Forms.FormsItems

import com.simplesys.SmartClient.Forms.FormsItems.radioGroupItem.RadioItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.{FormItemBaseStyle, SCClassName, void}

import scala.scalajs.js

@js.native
trait RadioGroupItem extends FormItem {
    val disabledValues: IscArray[String]
    val editProxyConstructor:SCClassName
    var fillHorizontalSpace:Boolean
    val itemProperties:RadioItem
    def  setTextBoxStyle (newTextBoxStyle:FormItemBaseStyle):void
    def setValueDisabled (value:JSAny, disabled:Boolean):void
    var vertical:Boolean
    var wrap:Boolean
}


