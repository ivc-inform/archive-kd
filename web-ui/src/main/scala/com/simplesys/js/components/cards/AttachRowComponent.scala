package com.simplesys.js.components.cards

import com.simplesys.SmartClient.Control.Progressbar
import com.simplesys.SmartClient.Layout.{AbstractHLayoutSSCompanion, HLayoutSS}
import com.simplesys.System.JSUndefined
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait AttachRowComponent extends HLayoutSS {
    val progressBar: JSUndefined[Progressbar]
    val imgButtonAttatch: JSUndefined[ImgButtonAttatch]
}

@js.native
abstract trait AbstractAttachRowwComponentCompanion extends AbstractHLayoutSSCompanion {
}

