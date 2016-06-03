package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.System.Types.{SCClassName, SCImgURL}
import com.simplesys.System.{JSDictionaryAny, JSObject, JSUndefined}
import com.simplesys.SmartClient.System.Class

import scala.scalajs.js

@js.native
trait PaletteNode extends JSObject {
    val defaults: JSObject
    val editNodeProperties: EditNode
    val editProxyProperties: JSUndefined[EditProxy]
    val contextMenu: JSUndefined[MenuSS]
    val icon: SCImgURL
    val idPrefix: String
    val liveObject: Class
    val title: String
    val `type`: SCClassName
}
