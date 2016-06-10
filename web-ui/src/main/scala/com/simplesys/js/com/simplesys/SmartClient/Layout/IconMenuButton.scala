package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.Menu

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait IconMenuButton extends IconButton {
    var menu: Menu
    var menuAnimationEffect: String
    def showMenu(): Boolean
}



