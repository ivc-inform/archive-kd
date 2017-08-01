package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.Control.{ImgButton, Progressbar}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.UploadItem
import com.simplesys.SmartClient.Forms.formsItems.props.UploadItemProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.props.IframeProps
import com.simplesys.SmartClient.Layout.props.WindowSSDialogProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Encoding, URL}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.cards.WindowUploadDialog
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

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

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            //val channelMessageEndUpload =

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

                        val channelMessageEndUpload = getNo1
                        addParam()

                        i += 1 
                        val channelMessageNextStep = getNo1
                        addParam()

                        i += 1
                        val channelMessageMaxValue = getNo1
                        addParam()

                        i += 1
                        val channelMessageRecordInBase = getNo1
                        addParam()

                        i += 1
                        val channelMessageError = getNo1
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
                                                  //                                                                  isc.confirm(s"Выбран файл: ${value.asInstanceOf[String].replace("C:\\fakepath\\", "")}, выгружать?", {
                                                  //                                                                      (value: Boolean) ⇒
                                                  //                                                                          if (value)
                                                  //                                                                              isc.ok(value.toString)
                                                  //                                                                      //form.submitForm()
                                                  //                                                                  }.toFunc)

                                              }.toFunc.opt
                                          }
                                      )
                                  ).opt
                              }
                          )

                }
            }


            thisTop.Super("initWidget", args)
            thisTop.okCancelPanel.foreach(_ setHeight "*")

    }.toThisFunc.opt

}
