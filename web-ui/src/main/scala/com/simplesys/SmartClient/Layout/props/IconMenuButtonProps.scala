package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.Menu
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js

class IconMenuButtonProps extends IconButtonProps {
    var menu: ScOption[Menu] = ScNone
    var menuAnimationEffect: ScOption[String] = ScNone
    var showMenu: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
}
