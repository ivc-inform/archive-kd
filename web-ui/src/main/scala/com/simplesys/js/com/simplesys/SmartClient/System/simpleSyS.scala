package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.Control._
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{Record, void}

import scala.scalajs.js

@js.native
trait Config extends JSObject {
    val confirmDeleting: String
}

@js.native
object simpleSyS extends JSObject {
    def checkOwner(canvas: Canvas): Canvas = js.native
    def _enableDeleteFromTree(menu: MenuSSItem): Boolean = js.native
    def _openFolders(menu: MenuSS): void = js.native
    var config: Config = js.native
    val aboutData: IscArray[Record] = js.native
}
