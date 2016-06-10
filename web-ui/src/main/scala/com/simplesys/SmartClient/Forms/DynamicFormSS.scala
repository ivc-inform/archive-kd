package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DynamicFormSS extends DynamicForm {
    def addField(field: FormItem, pos: Int): void
}

