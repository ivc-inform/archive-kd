package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.{ImgButtonProps, ProgressbarProps}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Alignment, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.app.{ImgButtonAttatch, WindowUploadDialog}
import com.simplesys.container.upload.{ErrorStr, UploadTestData}
import com.simplesys.function._
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.arx.AttatchDataRecord

import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr._

trait AttatchDataRecordExt extends AttatchDataRecord {
    val fileName: JSUndefined[String]
    val uploadFile: JSUndefined[String]
    val vname: JSUndefined[String]
    val viztname: JSUndefined[String]
}

object AttachProps extends JSObject {
    val fileNameField = "fileName".nameStrong
    val contentLength = "contentLength".nameStrong
    val mimeType = "mimeType".nameStrong
}

class AttachProps extends CommonListGridEditorComponentProps {
    type classHandler <: Attach

    import AttachProps._

    simpleTable = false.opt
    //autoFetchData = false.opt

    itemsType = Seq(miNew(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    dataSource = DataSourcesJS.arx_attatch_DS.opt
    identifier = "12DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.arx_attatch_FRMITM.opt

    showRecordComponents = true.opt
    showRecordComponentsByCell = true.opt

    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = arx_attatch_vcrcode_NameStrong.opt
            hidden = true.opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        }).opt


    fields = (ListGridFiledsJS.arx_attatch_FLDS ++ Seq(
        new ListGridFieldProps {
            nameStrong = fileNameField.opt
            title = "Файл ревизии".opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        },
        new ListGridFieldProps {
            nameStrong = contentLength.opt
            title = "Размер файла".opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
        },
        new ListGridFieldProps {
            nameStrong = arx_docizvstat_vname_NameStrong.opt
            title = "Статус извещения".opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
        },
        new ListGridFieldProps {
            title = "Тип извещения".opt
            nameStrong = arx_docizvtype_viztname_NameStrong.opt
            `type` = ListGridFieldType.sCaption_SimpleType.opt
        }
    )).opt

    height = 250

    createRecordComponent = {
        (thiz: classHandler, _record: AttatchDataRecordExt, colNum: Int) ⇒
            thiz.getFieldName(colNum) match {
                case fileNameField.name ⇒
                    any2undefOrA {
                        val _progressBar = Progressbar.create(
                            new ProgressbarProps {
                                //height = 20
                                length = "*"
                                title = _record.fileName.opt
                                showTitle = true.opt
                            }
                        )

                        HLayoutSS.create(
                            new HLayoutSSProps {
                                height = 20
                                width = "100%"
                                members = Seq(
                                    _progressBar,
                                    ImgButtonAttatch.create(
                                        new ImgButtonAttatchProps {
                                            showDown = false.opt
                                            showRollOver = false.opt
                                            layoutAlign = Alignment.center
                                            prompt = "Изменить файл".ellipsis.opt
                                            height = 18
                                            width = 18
                                            src = Common.attach.opt
                                            progressBar = _progressBar.opt
                                            record = _record.opt
                                            okFunction = {
                                                (thiz: classHandler) ⇒
                                                    thiz.channelMessageRecordInBase.foreach(isc.MessagingSS.subscribe(_, (e: MessageJS) ⇒ thiz.progressBar.foreach(_ setTitle "Запись в БД".ellipsis)))
                                                    thiz.channelMessageNextStep.foreach(isc.MessagingSS.subscribe(_, (e: MessageJS) ⇒ thiz.progressBar.foreach(_.nextStep())))
                                                    thiz.channelMessageMaxValue.foreach(isc.MessagingSS.subscribe(_,
                                                        (e: MessageJS) ⇒
                                                            e.data.foreach {
                                                                data ⇒
                                                                    thiz.progressBar.foreach {
                                                                        progressBar ⇒
                                                                            progressBar setPercentDone 0.0
                                                                            progressBar.maxValue = data.asInstanceOf[UploadTestData].maxValue.getOrElse(0)
                                                                    }
                                                            }
                                                    ))

                                                    def unsubscribe(): Unit = {
                                                        isc.MessagingSS.unsubscribe(IscArray(thiz.channelMessageEndUpload, thiz.channelMessageError, thiz.channelMessageNextStep, thiz.channelMessageMaxValue, thiz.channelMessageRecordInBase))
                                                        thiz.enable()
                                                    }

                                                    thiz.channelMessageEndUpload.foreach(isc.MessagingSS.subscribe(_, { (e: MessageJS) ⇒
                                                        progressBar.foreach(_ setPercentDone 0.0)
                                                        unsubscribe()
                                                    }))

                                                    thiz.channelMessageError.foreach(isc.MessagingSS.subscribe(_, { (e: MessageJS) ⇒
                                                        progressBar.foreach(_ setPercentDone 0.0)

                                                        val error = e.data.asInstanceOf[ErrorStr]
                                                        isc errorDetail(error.message.getOrElse(""), error.stack.getOrElse(""), "33BB2A90-9641-359E-8DD9-8159B35814B9", "33BB2A90-9641-359E-8DD9-8159B3581219")
                                                        unsubscribe()
                                                    }))


                                            }.toThisFunc.opt
                                            click = {
                                                (thizTop: classHandler) ⇒
                                                    WindowUploadDialog.create(
                                                        new WindowUploadDialogProps {
                                                            action = thizTop.actionURL.opt
                                                            okFunction = {
                                                                (thiz: classHandler) ⇒
                                                                    thizTop.disable()
                                                                    thizTop.okFunction()
                                                                    thiz.form.foreach(_.submitForm())
                                                            }.toThisFunc.opt
                                                        }
                                                    )
                                                    false
                                            }.toThisFunc.opt
                                        }
                                    )
                                ).opt
                            }
                        )
                    }
                case _ ⇒
                    jSUndefined

            }
    }.toThisFunc.opt
}
