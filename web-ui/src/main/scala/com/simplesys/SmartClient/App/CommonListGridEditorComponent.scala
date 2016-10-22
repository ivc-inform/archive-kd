package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Grids.{AbstractListGridEditorCompanion, ListGridEditor}
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait CommonListGridEditorComponent extends ListGridEditor {

    import com.simplesys.SmartClient.System.IscArray

    var customMenu: JSUndefined[IscArray[MenuSSItem]]

}

@js.native
abstract trait AbstractCommonListGridEditorComponentCompanion extends AbstractListGridEditorCompanion {
}


