package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.DataBinding.DataBoundComponent
import com.simplesys.System.JSObject
import com.simplesys.System.Types.SortDirection.SortDirection

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
object SortSpecifier extends JSObject {
    type SortSpecifier = SortSpecifier.type
    val context: DataBoundComponent = js.native
    val direction: SortDirection = js.native
    val normalizer: js.Function = js.native
    val property: String = js.native
}
