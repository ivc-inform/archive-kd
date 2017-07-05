package com.simplesys.container.upload

import com.simplesys.SmartClient.Layout.{AbstractHLayoutCompanion, HLayout}
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait UploadTestTab extends HLayout {
    var channelMessageEndUpload: JSUndefined[String]
    var channelMessageNextStep: JSUndefined[String]
    var channelMessageMaxValue: JSUndefined[String]
    var channelMessageRecordInBase: JSUndefined[String]
}

@js.native
abstract trait AbstractUploadTestTabCompanion extends AbstractHLayoutCompanion {
}

