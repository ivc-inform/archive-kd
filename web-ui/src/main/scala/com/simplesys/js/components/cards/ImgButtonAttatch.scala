package com.simplesys.js.components.cards

import com.simplesys.SmartClient.Control.{AbstractImgButtonCompanion, ImgButton, Progressbar}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.void
import com.simplesys.js.components.cards.props.AttatchDataRecordExt

import scala.scalajs.js

@js.native
trait ImgButtonAttatch extends ImgButton {
    var channelMessageEndUpload: JSUndefined[String]
    var channelMessageNextStep: JSUndefined[String]
    var channelMessageMaxValue: JSUndefined[String]
    var channelMessageRecordInBase: JSUndefined[String]
    var channelMessageError: JSUndefined[String]

    val progressBar: JSUndefined[Progressbar]
    val record: JSUndefined[AttatchDataRecordExt]
    var actionURL: JSUndefined[String]

    def subscribeFunction():void
}

@js.native
abstract trait AbstractImgButtonAttatchCompanion extends AbstractImgButtonCompanion {
}

