package com.simplesys.container.upload.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{ProgressbarItem, UploadItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.props.IframeProps
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.container.upload.UploadTestTab
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

trait UploadTestData extends JSObject {
    val maxValue: Double
}

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF216088816AF".opt

    var channelMessageEndUpload: ScOption[String] = ScNone
    var channelMessageNextStep: ScOption[String] = ScNone
    var channelMessageMaxValue: ScOption[String] = ScNone

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            if (thiz.channelMessageEndUpload.isEmpty)
                thiz.channelMessageEndUpload = s"EndUpload_${thiz.ID}"

            isc.MessagingSS.subscribe(thiz.channelMessageEndUpload.get,
                (e: MessageJS) ⇒
                    isc ok("Upload is done", "33BB2A90-9641-359E-8DD9-8159B3C614B9")
            )

            if (thiz.channelMessageNextStep.isEmpty)
                thiz.channelMessageNextStep = s"NextStep_${thiz.ID}"


            if (thiz.channelMessageMaxValue.isEmpty)
                thiz.channelMessageMaxValue = s"MaxValue_${thiz.ID}"


            val form = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    action = s"UploadServlet?channelMessageEndUpload=${thiz.channelMessageEndUpload.get}&channelMessageNextStep=${thiz.channelMessageNextStep.get}&channelMessageMaxValue=${thiz.channelMessageMaxValue.get}".opt
                    target = Iframe.create(
                        new IframeProps
                    ).ID.opt
                    encoding = Encoding.multipart.opt
                    canSubmit = true.opt
                    items = Seq(
                        UploadItem(
                            new UploadItemProps {
                                multiple = false.opt
                                nameStrong = "file".nameStrongOpt
                                showTitle = false.opt
                                title = "Choose file".opt
                                changed = {
                                    (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                        val submit = form getItem "upload"
                                        //value.map(_.toString.replace("C:\\fakepath\\", "")).foreach(isc ok (_))
                                        if (value.isDefined) submit.enable() else submit.disable()

                                }.toFunc.opt
                            }
                        ),
                        SubmitItem(
                            new SubmitItemProps {
                                disabled = true.opt
                                //colSpan = 2
                                title = "Upload".ellipsis.opt
                                nameStrong = "upload".nameStrongOpt
                            }
                        ),
                        ProgressbarItem(
                            new ProgressbarItemProps {
                                nameStrong = "progressBar".nameStrongOpt
                                showTitle = false.opt
                                title = "Процесс выгрузки".ellipsis.opt
                            }
                        )
                    ).opt
                }
            )

            val progressBar = (form getItem "progressBar").asInstanceOf[ProgressbarItem]

            isc.MessagingSS.subscribe(thiz.channelMessageNextStep.get,
                (e: MessageJS) ⇒
                    progressBar.nextStep()
            )

            isc.MessagingSS.subscribe(thiz.channelMessageMaxValue.get,
                (e: MessageJS) ⇒
                    e.data.foreach {
                        data ⇒
                            progressBar.maxValue = data.asInstanceOf[UploadTestData].maxValue
                    }
            )

            thiz addMember form

    }.toThisFunc.opt
}
