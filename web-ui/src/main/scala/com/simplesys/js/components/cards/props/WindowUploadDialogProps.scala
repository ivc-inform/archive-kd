package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.UploadItem
import com.simplesys.SmartClient.Forms.formsItems.props.UploadItemProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.props.IframeProps
import com.simplesys.SmartClient.Layout.props.WindowSSDialogProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Encoding
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.cards.WindowUploadDialog
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.UndefOr

class WindowUploadDialogProps extends WindowSSDialogProps {
    type classHandler <: WindowUploadDialog

    var progressBar: ScOption[Progressbar] = ScNone
    var attatchButton: ScOption[ImgButton] = ScNone
    var record: ScOption[AttatchDataRecordExt] = ScNone

    title = "Загрузить файл".ellipsis.opt
    headerIconPath = Common.attach.opt
    identifier = "FD82E8FF-4045-9803-9CE3-B94E96FC5D56".opt
    height = 100
    isModal = true.opt
    //autoSize = true.opt

    var channelMessageEndUpload: ScOption[String] = ScNone
    var channelMessageNextStep: ScOption[String] = ScNone
    var channelMessageMaxValue: ScOption[String] = ScNone
    var channelMessageRecordInBase: ScOption[String] = ScNone
    var channelMessageError: ScOption[String] = ScNone

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒

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

                        val _action = s"/logic/arx_attatch/Upload?${getParams}"

                        thisTop addItem
                          DynamicFormSS.create(
                              new DynamicFormSSProps {
                                  width = "100%"
                                  action = _action.opt
                                  target = Iframe.create(
                                      new IframeProps
                                  ).ID.opt
                                  encoding = Encoding.multipart.opt
                                  canSubmit = true.opt
                                  colWidths = Seq[JSAny]("30%", "*").opt
                                  items = Seq(
                                      UploadItem(
                                          new UploadItemProps {
                                              showTitle = false.opt
                                              height = 30
                                              width = "100%"
                                              multiple = false.opt
                                              changed = {
                                                  (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                                      value.foreach(_ ⇒ thisTop.okCancelPanel.foreach(_.okBtn.enable()))

                                              }.toFunc.opt
                                          }
                                      )
                                  ).opt
                              }
                          )

                }
            }


            thisTop.Super("initWidget", args)
            thisTop.okCancelPanel.foreach {
                item ⇒
                    item setHeight "*"
                    item.okBtn.disable()
                    item.okBtn.showDisabledIcon = false
            }

    }.toThisFunc.opt

    okFunction = {
        (thiz: classHandler) ⇒
            isc ok(message = "okFunction", callback = { () ⇒ thiz.hide() }.toFunc)
        //            thiz.channelMessageRecordInBase.foreach(isc.MessagingSS.subscribe(_, (e: MessageJS) ⇒ thiz.progressBar.foreach(_ setTitle "Запись в БД".ellipsis)))
        //            thiz.channelMessageNextStep.foreach(isc.MessagingSS.subscribe(_, (e: MessageJS) ⇒ thiz.progressBar.foreach(_.nextStep())))
        //            thiz.channelMessageMaxValue.foreach(isc.MessagingSS.subscribe(_,
        //                (e: MessageJS) ⇒
        //                    e.data.foreach {
        //                        data ⇒
        //                            thiz.progressBar.foreach {
        //                                progressBar ⇒
        //                                    progressBar setPercentDone 0.0
        //                                    progressBar.maxValue = data.asInstanceOf[UploadTestData].maxValue.getOrElse(0)
        //                            }
        //                    }
        //            ))
        //
        //            def unsubscribe(): Unit = {
        //                //                isc.MessagingSS.unsubscribe(IscArray(channelMessageEndUpload, channelMessageError, channelMessageNextStep, channelMessageMaxValue, channelMessageRecordInBase))
        //                //                            val file = form getItem "file"
        //                //                            file.enable()
        //            }
        //
        //            thiz.channelMessageEndUpload.foreach(isc.MessagingSS.subscribe(_, { (e: MessageJS) ⇒
        //                progressBar.foreach(_ setPercentDone 0.0)
        //                unsubscribe()
        //            }))
        //
        //            thiz.channelMessageError.foreach(isc.MessagingSS.subscribe(_, { (e: MessageJS) ⇒
        //                progressBar.foreach(_ setPercentDone 0.0)
        //
        //                val error = e.data.asInstanceOf[ErrorStr]
        //                isc errorDetail(error.message.getOrElse(""), error.stack.getOrElse(""), "33BB2A90-9641-359E-8DD9-8159B35814B9", "33BB2A90-9641-359E-8DD9-8159B3581219")
        //                unsubscribe()
        //            }))


    }.toThisFunc.opt

}
