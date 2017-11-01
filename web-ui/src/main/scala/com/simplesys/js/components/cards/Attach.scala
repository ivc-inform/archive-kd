package com.simplesys.js.components.cards

import com.simplesys.SmartClient.App.{AbstractCommonListGridEditorComponentCompanion, CommonListGridEditorComponent}
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait Attach extends CommonListGridEditorComponent {
    var channelSubscribeToChannel: JSUndefined[String]
}

@js.native
abstract trait AbstractAttachmentsCompanion extends AbstractCommonListGridEditorComponentCompanion {
}

