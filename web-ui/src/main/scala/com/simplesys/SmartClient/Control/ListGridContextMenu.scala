package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait ListGridContextMenu extends MenuSS {
    val customMenu: JSUndefined[Seq[MenuSSItem]]
}

//@js.native
//abstract trait AbstractListGridContextMenuCompanion extends AbstractMenuSSCompanion {
//}


