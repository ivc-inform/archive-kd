package com.simplesys.container.upload.props

import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{ButtonItem, ProgressbarItem, UploadItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.props.IframeProps
import com.simplesys.SmartClient.Layout.WindowSS
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
    val maxValue: JSUndefined[Double]
    val elapsedTime: JSUndefined[String]
}

trait Error extends JSObject {
    val message: JSUndefined[String]
    val stack: JSUndefined[String]
}

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF216088816AF".opt

    var channelMessageEndUpload: ScOption[String] = ScNone
    var channelMessageError: ScOption[String] = ScNone
    var channelMessageNextStep: ScOption[String] = ScNone
    var channelMessageMaxValue: ScOption[String] = ScNone
    var channelMessageRecordInBase: ScOption[String] = ScNone

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            if (thiz.channelMessageEndUpload.isEmpty)
                thiz.channelMessageEndUpload = s"EndUpload_${thiz.ID}_${simpleSyS.guid}"

            if (thiz.channelMessageError.isEmpty)
                thiz.channelMessageError = s"Error_${thiz.ID}_${simpleSyS.guid}"

            if (thiz.channelMessageRecordInBase.isEmpty)
                thiz.channelMessageRecordInBase = s"RecordInBase_${thiz.ID}_${simpleSyS.guid}"

            var progressBar: JSUndefined[ProgressbarItem] = jSUndefined

            isc.MessagingSS.subscribe(thiz.channelMessageRecordInBase.get,
                (e: MessageJS) ⇒
                    progressBar.foreach(_ setTitle "Recording in base")
            )


            if (thiz.channelMessageNextStep.isEmpty)
                thiz.channelMessageNextStep = s"NextStep_${thiz.ID}_${simpleSyS.guid}"


            if (thiz.channelMessageMaxValue.isEmpty)
                thiz.channelMessageMaxValue = s"MaxValue_${thiz.ID}_${simpleSyS.guid}"


            val form = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    action = s"TestUploadServlet?channelMessageEndUpload=${thiz.channelMessageEndUpload.get}&channelMessageNextStep=${thiz.channelMessageNextStep.get}&channelMessageMaxValue=${thiz.channelMessageMaxValue.get}&channelMessageRecordInBase=${thiz.channelMessageRecordInBase.get}&channelMessageError=${thiz.channelMessageError.get}".opt
                    target = Iframe.create(
                        new IframeProps
                    ).ID.opt
                    encoding = Encoding.multipart.opt
                    canSubmit = true.opt
                    items = Seq(
                        UploadItem(
                            new UploadItemProps {
                                //multiple = true.opt
                                //visible = false.opt
                                nameStrong = "file".nameStrongOpt
                                showTitle = false.opt
                                title = "Choose file".opt
                                changed = {
                                    (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                        val submit = form getItem "upload"
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

            progressBar = (form getItem "progressBar").asInstanceOf[ProgressbarItem]

            isc.MessagingSS.subscribe(thiz.channelMessageNextStep.get,
                (e: MessageJS) ⇒
                    progressBar.foreach(_.nextStep())
            )

            isc.MessagingSS.subscribe(thiz.channelMessageMaxValue.get,
                (e: MessageJS) ⇒
                    e.data.foreach {
                        data ⇒
                            progressBar.foreach {
                                progressBar ⇒
                                    progressBar setPercentDone 0.0
                                    progressBar.maxValue = data.asInstanceOf[UploadTestData].maxValue.getOrElse(0)
                            }
                    }
            )

            thiz addMember form

            def unsubscribe(): Unit = {
                //isc.MessagingSS.unsubscribe(IscArray(thiz.channelMessageEndUpload.get, thiz.channelMessageError.get, thiz.channelMessageNextStep.get, thiz.channelMessageMaxValue.get, thiz.channelMessageRecordInBase.get))
                val submit = form getItem "upload"
                submit.disable()
            }

            isc.MessagingSS.subscribe(thiz.channelMessageEndUpload.get, { (e: MessageJS) ⇒
                progressBar.foreach(_ setPercentDone 0.0)

                val elapsedTime = e.data.map(_.asInstanceOf[UploadTestData].elapsedTime.getOrElse("")).getOrElse("")
                val fileSize = progressBar.get.maxValue

                isc ok(s"Upload is done, fileSize: $fileSize, elapsedTime: $elapsedTime", "33BB2A90-9641-359E-8DD9-8159B3C614B9")
                unsubscribe()
            })

            isc.MessagingSS.subscribe(thiz.channelMessageError.get, { (e: MessageJS) ⇒
                progressBar.foreach(_ setPercentDone 0.0)

                val error = e.data.asInstanceOf[Error]
                isc errorDetail(error.message.getOrElse(""), error.stack.getOrElse(""), "33BB2A90-9641-359E-8DD9-8159B35814B9", "33BB2A90-9641-359E-8DD9-8159B3581219")
                unsubscribe()
            })

    }.toThisFunc.opt
}
