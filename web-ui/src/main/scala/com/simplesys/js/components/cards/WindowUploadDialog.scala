package com.simplesys.js.components.cards

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Layout.{AbstractWindowSSDialogCompanion, WindowSSDialog}
import com.simplesys.System.JSUndefined
import com.simplesys.js.components.cards.props.AttatchDataRecordExt

import scala.scalajs.js

@js.native
trait WindowUploadDialog extends WindowSSDialog {
    val progressBar: JSUndefined[Progressbar]
    val attatchButton: JSUndefined[ImgButton]
    val record: JSUndefined[AttatchDataRecordExt]
}

@js.native
abstract trait AbstractWindowUploadDialogCompanion extends AbstractWindowSSDialogCompanion {
}

