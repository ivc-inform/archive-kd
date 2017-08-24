package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.ProgressbarProps
import com.simplesys.SmartClient.DataBinding.props.JSONEncoderProps
import com.simplesys.SmartClient.DataBinding.{JSON, JSONEncoder}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.HLayoutSS
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.RPC.props.{RPCRequestProps, RPCResponseProps}
import com.simplesys.SmartClient.System.{HLayoutSS, _}
import com.simplesys.SmartClient.sse.Sse
import com.simplesys.System.Types.ListGridEditEvent.ListGridEditEvent
import com.simplesys.System.Types.RecordComponentPoolingMode.RecordComponentPoolingMode
import com.simplesys.System.Types.{Alignment, ListGridEditEvent, ListGridFieldType, RecordComponentPoolingMode}
import com.simplesys.System._
import com.simplesys.app.{AttachRowComponent, ImgButtonAttatch, WindowUploadDialog}
import com.simplesys.container.upload.{ErrorStr, UploadData}
import com.simplesys.function._
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.arx.AttatchDataRecord

import scala.scalajs.js
import scala.scalajs.js.UndefOr._

trait AttatchDataRecordExt extends AttatchDataRecord {
    var fileName: JSUndefined[String]
    var contentLength: JSUndefined[String]
    var vname: JSUndefined[String]
    var viztname: JSUndefined[String]
}

object AttachProps extends JSObject {
    val fileNameField = "fileName".nameStrong
    val contentLength = "contentLength".nameStrong
    val mimeType = "mimeType".nameStrong

    def getSize(size: BigDecimal): String = s"${(size / 1024 / 1024).setScale(4, BigDecimal.RoundingMode.HALF_UP)} MB"
}

class AttachProps extends CommonListGridEditorComponentProps {
    type classHandler <: Attach

    import AttachProps._

    simpleTable = false.opt
    //autoFetchData = false.opt
    recordComponentPoolingMode = RecordComponentPoolingMode.viewport.opt

    itemsType = Seq(miNew(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    dataSource = DataSourcesJS.arx_attatch_DS.opt
    identifier = "12DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.arx_attatch_FRMITM.opt

    showRecordComponents = true.opt
    showRecordComponentsByCell = true.opt

    var channelSubscribeToChannel: ScOption[String] = "12DC1876-F489-3172-1297-729FFB73B575".opt

    editEvent = ListGridEditEvent.none.opt

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

    initWidget = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            thisTop.Super("initWidget", args)

            thisTop.channelSubscribeToChannel.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                (e: MessageJS) ⇒
                    e.data.foreach {
                        dt ⇒
                            val data = dt.asInstanceOf[UploadData]
                            val record = thisTop.findByKey(data.idAttatch)
                            val component = record.map(record ⇒ thisTop.getLiveRecordComponent(record, fileNameField.name))
                            println(component)
                    }
            ))
    }.toThisFunc.opt

    createRecordComponent = {
        (thisTop: classHandler, _record: AttatchDataRecordExt, colNum: Int) ⇒
            thisTop.getFieldName(colNum) match {
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

                        if (_record.status.getOrElse(0) == 2) {
                            _progressBar setPercentDone 100
                            _progressBar setTitle "Запись в БД".ellipsis
                        }

                        AttachRowComponent.create(
                            new AttachRowComponentProps {
                                height = 20
                                width = "100%"
                                progressBar = _progressBar.opt
                                imgButtonAttatch = ImgButtonAttatch.create(
                                    new ImgButtonAttatchProps {
                                        identifier = "imageButton".opt
                                        showDown = false.opt
                                        showRollOver = false.opt
                                        layoutAlign = Alignment.center
                                        prompt = "Прикрепить файл".ellipsis.opt
                                        height = 18
                                        width = 18
                                        src = (if (_record.status.getOrElse(0) == 2) Common.cancel else Common.attach).opt
                                        progressBar = _progressBar.opt
                                        record = _record.opt
                                        showDisabledIcon = false.opt
                                        subscribeFunction = {
                                            (thiz: classHandler) ⇒
                                                thiz.channelMessageRecordInBase.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                                                    (e: MessageJS) ⇒
                                                        thiz.progressBar.foreach {
                                                            progressBar ⇒
                                                                if (!progressBar.destroyed.getOrElse(false))
                                                                    progressBar setPercentDone 100
                                                                progressBar setTitle "Запись в БД".ellipsis
                                                        }
                                                ))
                                                thiz.channelMessageNextStep.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                                                    (e: MessageJS) ⇒ thiz.progressBar.foreach { progressBar ⇒
                                                        if (!progressBar.destroyed.getOrElse(false))
                                                            progressBar.nextStep()
                                                    }
                                                ))

                                                def unsubscribe(): Unit = {
                                                    thiz.channelMessageEndUpload.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.channelMessageError.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.channelMessageNextStep.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.channelMessageRecordInBase.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.enable()
                                                }

                                                thiz.channelMessageEndUpload.foreach(channel ⇒ isc.MessagingSS.subscribe(channel, { (e: MessageJS) ⇒
                                                    e.data.foreach {
                                                        data ⇒
                                                            val _data = data.asInstanceOf[UploadData]
                                                            progressBar.foreach { progressBar ⇒
                                                                if (!progressBar.destroyed.getOrElse(false)) {
                                                                    progressBar setPercentDone 0.0
                                                                    _data.fileName.foreach(progressBar setTitle _)
                                                                }

                                                                _record.contentLength = AttachProps.getSize(_data.fileSize.getOrElse(0.0): Double)
                                                                thisTop.listGrid.refreshRow(thisTop.getRowNum(_record))
                                                            }
                                                    }
                                                    unsubscribe()
                                                }))

                                                thiz.channelMessageError.foreach(channel ⇒ isc.MessagingSS.subscribe(channel, { (e: MessageJS) ⇒
                                                    progressBar.foreach(_ setPercentDone 0.0)

                                                    val error = e.data.asInstanceOf[ErrorStr]
                                                    isc errorDetail(error.message.getOrElse(""), error.stack.getOrElse(""), "33BB2A90-9641-359E-8DD9-8159B35814B9", "33BB2A90-9641-359E-8DD9-8159B3581219")
                                                    unsubscribe()
                                                }))


                                        }.toThisFunc.opt
                                        click = {
                                            (thizTop: classHandler) ⇒
                                                thizTop.record.foreach {
                                                    record ⇒
                                                        record.status.getOrElse(0) match {
                                                            case 0 ⇒
                                                                val url = thizTop.actionURL

                                                                WindowUploadDialog.create(
                                                                    new WindowUploadDialogProps {
                                                                        action = url.opt
                                                                        okFunction = {
                                                                            (thiz: classHandler) ⇒
                                                                                thizTop.disable()
                                                                                thiz.form.foreach(_.submitForm())
                                                                                thiz.markForDestroy()
                                                                        }.toThisFunc.opt
                                                                    }
                                                                )
                                                            //todo 3 заменить на константу
                                                            case 3 ⇒
                                                                isc info "Нет реализации..."
                                                            /*RPCManagerSS.sendRequest(
                                                                RPCRequest(
                                                                    new RPCRequestProps {
                                                                        actionURL = "logic/arx_attatch/StopUpload".opt
                                                                        data = js.Dictionary("record.status" → record.status).opt
                                                                        callback = {
                                                                            (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                                                                                thizTop.progressBar.foreach(_ setTitle record.fileName.getOrElse("unknown"))
                                                                                thizTop setSrc Common.attach

                                                                        }.toFunc.opt
                                                                    }
                                                                )
                                                            )*/
                                                        }
                                                }

                                                false
                                        }.toThisFunc.opt
                                    }
                                ).opt
                            }
                        )
                    }
                case _ ⇒
                    jSUndefined

            }
    }.toThisFunc.opt
}
