package com.simplesys.SmartClient.App.formItems

import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait LookupEditorItem extends CanvasItem{
    val buttonIcon: JSUndefined[SCImgURL]
    val captionLookupFieldName: JSUndefined[String]
    var textItem: JSUndefined[TextItem]
}
