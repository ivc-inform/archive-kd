package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ProgressbarProps}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Alignment, ListGridEditEvent, ListGridFieldType, RecordComponentPoolingMode}
import com.simplesys.System._
import com.simplesys.app.{AttachRowComponent, ImgButtonAttatch, WindowUploadDialog}
import com.simplesys.container.upload.{ErrorStr, UploadData}
import com.simplesys.function._
import com.simplesys.js.components.cards.{Attach, AttachRowComponent}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.arx.AttatchDataRecord

import scala.scalajs.js
import scala.scalajs.js.UndefOr._

trait AttatchDataRecordExt extends AttatchDataRecord {
    var fileName: JSUndefined[String]
    var percentsDone: JSUndefined[Double]
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

            val funcMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thisTop.opt
                    itemsType = Seq(miNew(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt
                }
            )

            thisTop setFuncMenu funcMenu
            thisTop setContextMenu funcMenu

            thisTop.channelSubscribeToChannel.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                (e: MessageJS) ⇒
                    e.data.foreach {
                        dt ⇒
                            val data = dt.asInstanceOf[UploadData]
                            val record = thisTop.findByKey(data.idAttatch)
                            val component = record.map(record ⇒ thisTop.getLiveRecordComponent[AttachRowComponent](record, fileNameField.name)).flatten
                            component.foreach(_.imgButtonAttatch.foreach(_.subscribeFunction()))
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

                        val component = AttachRowComponent.create(
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
                                        prompt = {
                                            _record.status.getOrElse(0) match {
                                                case 0 ⇒ "Прикрепить файл".ellipsis
                                                case 1 ⇒ "Заблокирован"
                                                case 2 ⇒ "Заблокирован"
                                                case 3 ⇒ "Снять зависшую блокировку"
                                                case _ ⇒ "Неизвестное состояние"
                                            }
                                        }.opt
                                        disabled = {
                                            _record.status.getOrElse(0) match {
                                                case 0 ⇒ false
                                                case 1 ⇒ true
                                                case 2 ⇒ true
                                                case 3 ⇒ false
                                                case _ ⇒ true
                                            }
                                        }.opt
                                        height = 18
                                        width = 18
                                        src = {
                                            _record.status.getOrElse(0) match {
                                                case 0 ⇒ Common.attach
                                                case 1 ⇒ ""
                                                case 2 ⇒ ""
                                                case 3 ⇒ Common.cancel
                                                case _ ⇒ ""
                                            }
                                        }.opt
                                        progressBar = _progressBar.opt
                                        record = _record.opt
                                        showDisabledIcon = false.opt
                                        subscribeFunction = {
                                            (thiz: classHandler) ⇒
                                                thiz.disable()
                                                thiz.channelMessageRecordInBase.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                                                    (e: MessageJS) ⇒
                                                        thiz.progressBar.foreach {
                                                            progressBar ⇒
                                                                if (!progressBar.destroyed.getOrElse(false))
                                                                    progressBar setPercentDone 100
                                                                progressBar setTitle "Запись в БД".ellipsis
                                                        }
                                                ))
                                                thiz.channelMessageUploadPercent.foreach(channel ⇒ isc.MessagingSS.subscribe(channel,
                                                    (e: MessageJS) ⇒ thiz.progressBar.foreach { progressBar ⇒
                                                        e.data.foreach {
                                                            data ⇒
                                                                val _data = data.asInstanceOf[UploadData]
                                                                if (!progressBar.destroyed.getOrElse(false))
                                                                    _data.percentsDone.foreach {
                                                                        percentsDone ⇒
                                                                            progressBar setPercentDone percentsDone
                                                                            progressBar setTitle s"Перенос данных: $percentsDone %"
                                                                    }
                                                        }
                                                    }
                                                ))

                                                def unsubscribe(): Unit = {
                                                    thiz.channelMessageEndUpload.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.channelMessageError.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
                                                    thiz.channelMessageUploadPercent.foreach(channel ⇒ isc.MessagingSS.unsubscribe(channel))
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
                                                                thiz setSrc Common.attach
                                                                thiz.enable()
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
                                                        val status = record.status.getOrElse(0)
                                                        status match {
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
                                                                thizTop.record.foreach {
                                                                    record ⇒
                                                                        RPCManagerSS.sendRequest(
                                                                            RPCRequest(
                                                                                new RPCRequestProps {
                                                                                    actionURL = "logic/arx_attatch/StopUpload".opt
                                                                                    data = js.Dictionary("id" → record.id, "status" → 0).opt
                                                                                    timeout = 60000.opt
                                                                                    sendNoQueue = true.opt
                                                                                    callback = {
                                                                                        (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                                                                                            if (resp.httpResponseCode == 200) {
                                                                                                thizTop setSrc Common.attach
                                                                                                thizTop.record.asInstanceOf[JSDynamic].updateDynamic("status")(0)
                                                                                            }

                                                                                    }.toFunc.opt
                                                                                }
                                                                            )
                                                                        )
                                                                }
                                                        }
                                                }

                                                false
                                        }.toThisFunc.opt
                                    }
                                ).opt
                            }
                        )

                        val status = _record.status.getOrElse(0)
                        status match {
                            case 0 ⇒
                            case 1 ⇒
                                component.imgButtonAttatch.foreach(_.subscribeFunction())
                                _progressBar setPercentDone 100
                                _progressBar setTitle "Запись в БД".ellipsis
                            case 2 ⇒
                                component.imgButtonAttatch.foreach(_.subscribeFunction())
                                _progressBar setPercentDone 100
                                _progressBar setTitle "Запись в БД".ellipsis
                            case any ⇒
                        }

                        component
                    }
                case _ ⇒
                    jSUndefined

            }
    }.toThisFunc.opt
}
