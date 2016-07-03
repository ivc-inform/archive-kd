package com.simplesys.SmartClient.App.formItems

import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait LookupTreeGridEditorItem extends CanvasItem{
    val buttonIcon: JSUndefined[SCImgURL]
    var textItem: TextItem

}
