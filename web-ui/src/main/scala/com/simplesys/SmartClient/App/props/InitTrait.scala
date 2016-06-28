package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System._

import scala.collection.mutable.ArrayBuffer

trait InitTrait {
    self: ListGridEditorProps =>

    def initFields(thiz: classHandler, arguments: IscArray[JSAny]): Unit = {

    }
}
