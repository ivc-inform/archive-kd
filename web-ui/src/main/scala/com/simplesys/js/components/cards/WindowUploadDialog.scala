package com.simplesys.js.components.cards

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Layout.{AbstractWindowSSDialogCompanion, WindowSSDialog}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.URL

import scala.scalajs.js

@js.native
trait WindowUploadDialog extends WindowSSDialog {
    var action: JSUndefined[URL]
    var form: JSUndefined[DynamicFormSS]
}

@js.native
abstract trait AbstractWindowUploadDialogCompanion extends AbstractWindowSSDialogCompanion {
}

