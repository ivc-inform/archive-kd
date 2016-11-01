package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait DynamicFormSS extends DynamicForm {

    import com.simplesys.System._

    def addField(field: FormItem, pos: Int): void
    def setPropertyOnSelection[T](property: String, value: JSUndefined[T])
}

@js.native
abstract class AbstractDynamicFormSSCompanion extends AbstractDynamicFormCompanion

