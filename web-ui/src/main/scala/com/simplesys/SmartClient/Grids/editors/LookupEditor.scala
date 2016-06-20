package com.simplesys.SmartClient.Grids.editors

import com.simplesys.SmartClient.Forms.FormsItems.TextItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.HLayoutSS
import com.simplesys.System.Types._
import com.simplesys.System.{JSAny, JSUndefined}

import scala.scalajs.js

@js.native
trait LookupEditor extends HLayoutSS {
    val buttonIcon: JSUndefined[SCImgURL]
    val titleEditor: JSUndefined[String]
    val editor: JSUndefined[Canvas]
    val record : JSUndefined[Record]
    var textItem: JSUndefined[TextItem]
    val editedFieldName: JSUndefined[String]
    def setValue(value: JSAny): void
    def setValueFromRecord(value: Record): void
}
