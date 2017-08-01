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
import com.simplesys.container.upload.{ErrorStr, UploadTestData, UploadTestTab}
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class UploadTestTabProps extends HLayoutProps {
    type classHandler <: UploadTestTab

    identifier = "69EC6EB4-E51F-B7A9-C1E0-CF216088816AF".opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val channelMessageEndUpload = s"EndUpload_${thiz.ID}_${simpleSyS.guid}"
            val channelMessageError = s"Error_${thiz.ID}_${simpleSyS.guid}"
            val channelMessageRecordInBase = s"RecordInBase_${thiz.ID}_${simpleSyS.guid}"
            val channelMessageNextStep = s"NextStep_${thiz.ID}_${simpleSyS.guid}"
            val channelMessageMaxValue = s"MaxValue_${thiz.ID}_${simpleSyS.guid}"

            var progressBar: JSUndefined[ProgressbarItem] = jSUndefined

            isc.MessagingSS.subscribe(channelMessageRecordInBase,
                (e: MessageJS) ⇒
                    progressBar.foreach(_ setTitle "Recording in base")
            )

            val form = DynamicFormSS.create(
                new DynamicFormSSProps {
                    width = "100%"
                    action = s"TestUploadServlet?channelMessageEndUpload=${channelMessageEndUpload}&channelMessageNextStep=${channelMessageNextStep}&channelMessageMaxValue=${channelMessageMaxValue}&channelMessageRecordInBase=${channelMessageRecordInBase}&channelMessageError=${channelMessageError}".opt
                    //action = "TestUploadServlet".opt
                    target = Iframe.create(
                        new IframeProps
                    ).ID.opt
                    encoding = Encoding.multipart.opt
                    canSubmit = true.opt
                    items = Seq(
                        UploadItem(
                            new UploadItemProps {
                                multiple = false.opt
                                //visible = false.opt
                                nameStrong = "file".nameStrongOpt
                                showTitle = false.opt
                                title = "Choose file".opt
                                changed = {
                                    (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                        val upload = form getItem "upload"
                                        if (value.isDefined) upload.enable() else upload.disable()

                                }.toFunc.opt
                            }
                        ),
                        ButtonItem(
                            new ButtonItemProps {
                                disabled = true.opt
                                title = "Upload".ellipsis.opt
                                nameStrong = "upload".nameStrongOpt
                                click = {
                                    (form: DynamicFormSS, item: ButtonItem) ⇒
                                        item.disable()
                                        val file = form getItem "file"
                                        form.submitForm()
                                        file.disable()
                                        false
                                }.toFunc.opt
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

            isc.MessagingSS.subscribe(channelMessageNextStep,
                (e: MessageJS) ⇒
                    progressBar.foreach(_.nextStep())
            )

            isc.MessagingSS.subscribe(channelMessageMaxValue,
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
//                isc.MessagingSS.unsubscribe(IscArray(channelMessageEndUpload, channelMessageError, channelMessageNextStep, channelMessageMaxValue, channelMessageRecordInBase))
                val file = form getItem "file"
                file.enable()
            }

            isc.MessagingSS.subscribe(channelMessageEndUpload, { (e: MessageJS) ⇒
                progressBar.foreach(_ setPercentDone 0.0)

                val elapsedTime = e.data.map(_.asInstanceOf[UploadTestData].elapsedTime.getOrElse("")).getOrElse("")
                val fileSize = progressBar.get.maxValue

                isc ok(s"Upload is done, fileSize: $fileSize, elapsedTime: $elapsedTime", "33BB2A90-9641-359E-8DD9-8159B3C614B9")
                unsubscribe()
            })

            isc.MessagingSS.subscribe(channelMessageError, { (e: MessageJS) ⇒
                progressBar.foreach(_ setPercentDone 0.0)

                val error = e.data.asInstanceOf[ErrorStr]
                isc errorDetail(error.message.getOrElse(""), error.stack.getOrElse(""), "33BB2A90-9641-359E-8DD9-8159B35814B9", "33BB2A90-9641-359E-8DD9-8159B3581219")
                unsubscribe()
            })

    }.toThisFunc.opt
}
