package com.simplesys.SmartClient.Forms.FormsItems

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Foundation.Canvas

import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.ReadOnlyDisplayAppearance.ReadOnlyDisplayAppearance
import com.simplesys.System.Types.void
import com.simplesys.System.{JSObject, JSAny}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait CanvasItem extends FormItem {
    var applyPromptToCanvas: Boolean
    var autoDestroy: Boolean
    var canEditChanged: js.Function1[Boolean, Boolean]
    var canvas: Canvas
    var canvasConstructor: String
    var canvasDefaults: JSObject
    var canvasProperties: JSObject
    var createCanvas: js.Function2[DynamicForm, CanvasItem, Canvas]
    val editCriteriaInInnerForm: Boolean
    def getCriterion(): Criterion
    var multiple: Boolean
    var overflow: Overflow
    def readOnlyDisplayChanged(appearance: ReadOnlyDisplayAppearance): Boolean
    def setCanvas(canvas: Canvas): void
    def shouldDisableCanvas(): void
    var showValue: js.Function3[JSAny, JSAny, DynamicForm, CanvasItem]
}


