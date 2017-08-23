package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Control.props.ImgButtonProps
import com.simplesys.SmartClient.System.{IscArray, isc, simpleSyS}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.URL
import com.simplesys.function._
import com.simplesys.js.components.cards.ImgButtonAttatch
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js.{ThisFunction0, UndefOr}

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

    var okFunction: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) ⇒
            isc info "Нет реализации."
    }.toThisFunc.opt

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

            thisTop.Super("initWidget", args)

            thisTop.record.map {
                _.id.map {
                    id ⇒
                        var i = 1
                        val params = new StringBuilder
                        params append "id"
                        params append "="
                        params append id
                        params append "&"

                        def addParam(end: String = "&", no1: String) {
                            params append getNo
                            params append "="
                            params append no1
                            params append end
                        }

                        def getParams: String = params.toString()

                        def getNo = s"p${i}"
                        def getNo1(id: Double) = s"${getNo}_$id"

                        record.foreach {
                            record ⇒
                                var no1 = getNo1(record.id.getOrElse(0))
                                thisTop.channelMessageEndUpload = no1
                                addParam(no1 = no1)

                                i += 1
                                no1 = getNo1(record.id.getOrElse(0))
                                thisTop.channelMessageError = no1
                                addParam(no1 = no1)

                                i += 1
                                no1 = getNo1(record.id.getOrElse(0))
                                thisTop.channelMessageNextStep = no1
                                addParam(no1 = no1)

                                i += 1
                                no1 = getNo1(record.id.getOrElse(0))
                                thisTop.channelMessageMaxValue = no1
                                addParam(no1 = no1)

                                i += 1
                                no1 = getNo1(record.id.getOrElse(0))
                                thisTop.channelMessageRecordInBase = no1
                                addParam("", no1)

                                thisTop.actionURL = s"logic/arx_attatch/Upload?${getParams}"
                        }
                }
            }


    }.toThisFunc.opt


}
