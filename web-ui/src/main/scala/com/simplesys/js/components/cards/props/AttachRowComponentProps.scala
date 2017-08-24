package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.Control.Progressbar
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.app.ImgButtonAttatch
import com.simplesys.function._
import com.simplesys.js.components.cards.{AttachRowComponent, ImgButtonAttatch}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class AttachRowComponentProps extends HLayoutSSProps {
    type classHandler <: AttachRowComponent

    var progressBar: ScOption[Progressbar] = ScNone
    var imgButtonAttatch: ScOption[ImgButtonAttatch] = ScNone

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            thisTop.Super("initWidget", args)

            thisTop.progressBar.foreach(thisTop addMember _)
            thisTop.imgButtonAttatch.foreach(thisTop addMember _)

    }.toThisFunc.opt
}
