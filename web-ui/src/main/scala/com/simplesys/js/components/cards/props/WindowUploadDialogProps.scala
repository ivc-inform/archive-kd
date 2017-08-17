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
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, UndefOr}

class WindowUploadDialogProps extends WindowSSDialogProps {
    type classHandler <: WindowUploadDialog

    title = "Загрузить файл".ellipsis.opt
    headerIconPath = Common.attach.opt
    identifier = "FD82E8FF-4045-9803-9CE3-B94E96FC5D56".opt
    height = 100
    isModal = true.opt
    //autoSize = true.opt

    var action: ScOption[URL] = ScNone
    var form: ScOption[DynamicFormSS] = ScNone

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            thisTop.form = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    action = thisTop.action.opt
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

            thisTop addItem thisTop.form.get

            thisTop.Super("initWidget", args)
            thisTop.okCancelPanel.foreach {
                item ⇒
                    item setHeight "*"
                    item.okBtn.disable()
                    item.okBtn.showDisabledIcon = false
            }

    }.toThisFunc.opt


}
