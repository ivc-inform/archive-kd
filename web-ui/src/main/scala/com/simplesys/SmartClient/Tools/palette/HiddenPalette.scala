package com.simplesys.SmartClient.Tools.palette

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.{Palette, PaletteNode}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
trait HiddenPalette extends Palette {
    val data: IscArray[PaletteNode]
}


