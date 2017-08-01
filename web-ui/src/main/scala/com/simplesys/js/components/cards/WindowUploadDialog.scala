package com.simplesys.js.components.cards

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Layout.{AbstractWindowSSDialogCompanion, WindowSSDialog}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.URL
import com.simplesys.js.components.cards.props.AttatchDataRecordExt
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

@js.native
trait WindowUploadDialog extends WindowSSDialog {

    var action: JSUndefined[URL]
}

@js.native
abstract trait AbstractWindowUploadDialogCompanion extends AbstractWindowSSDialogCompanion {
}

