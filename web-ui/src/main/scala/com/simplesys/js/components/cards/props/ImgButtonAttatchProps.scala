package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Control.props.ImgButtonProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.URL
import com.simplesys.function._
import com.simplesys.js.components.cards.ImgButtonAttatch
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js.UndefOr

class ImgButtonAttatchProps extends ImgButtonProps {
    type classHandler <: ImgButtonAttatch

    var channelMessageEndUpload: ScOption[String] = ScNone
    var channelMessageNextStep: ScOption[String] = ScNone
    var channelMessageMaxValue: ScOption[String] = ScNone
    var channelMessageRecordInBase: ScOption[String] = ScNone
    var channelMessageError: ScOption[String] = ScNone

    var progressBar: ScOption[Progressbar] = ScNone
    var record: ScOption[AttatchDataRecordExt] = ScNone
    var actionURL: ScOption[URL] = ScNone

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.Super("initWidget", args)

            def getAction(): UndefOr[String] = thisTop.record.map(item ⇒ item.id.map(id ⇒ s"logic/arx_attatch/Upload?p1=p1_${id}&")).flatten

            thisTop.record.map {
                _.id.map {
                    id ⇒
                        var i = 1
                        val params = new StringBuilder
                        params append "id"
                        params append "="
                        params append id
                        params append "&"

                        def addParam(end: String = "&") {
                            params append getNo
                            params append "="
                            params append getNo1
                            params append end
                        }

                        def getParams: String = params.toString()

                        def getNo = s"p${i}"
                        def getNo1 = s"${getNo}_${id}"

                        thisTop.channelMessageEndUpload = getNo1
                        addParam()

                        i += 1
                        thisTop.channelMessageNextStep = getNo1
                        addParam()

                        i += 1
                        thisTop.channelMessageMaxValue = getNo1
                        addParam()

                        i += 1
                        thisTop.channelMessageRecordInBase = getNo1
                        addParam()

                        i += 1
                        thisTop.channelMessageError = getNo1
                        addParam("")

                        thisTop.actionURL = s"/logic/arx_attatch/Upload?${getParams}"


                }
            }


    }.toThisFunc.opt


}
