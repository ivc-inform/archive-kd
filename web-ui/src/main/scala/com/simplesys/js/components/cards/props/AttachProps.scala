package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.{ImgButtonProps, ProgressbarProps}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, WindowUploadDialogProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Alignment, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.arx.AttatchDataRecord

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
            nameStrong = mimeType.opt
            title = "Тип файла".opt
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
        (thiz: classHandler, record: AttatchDataRecordExt, colNum: Int) ⇒
            thiz.getFieldName(colNum) match {
                case fileNameField.name ⇒
                    any2undefOrA(
                        HLayoutSS.create(
                            new HLayoutSSProps {
                                height = 20
                                width = "100%"
                                members = Seq(
                                    Progressbar.create(
                                        new ProgressbarProps {
                                            //height = 20
                                            length = "*"
                                            title = record.fileName.opt
                                            showTitle = true.opt
                                        }
                                    ),
                                    ImgButton.create(
                                        new ImgButtonProps {
                                            showDown = false.opt
                                            showRollOver = false.opt
                                            layoutAlign = Alignment.center
                                            prompt = "Изменить файл".ellipsis.opt
                                            height = 18
                                            width = 18
                                            src = Common.attach.opt
                                            click = {
                                                (thiz: classHandler) ⇒
                                                    WindowUploadDialog.create(
                                                        new WindowUploadDialogProps{

                                                        }
                                                    )
                                                    false
                                            }.toThisFunc.opt
                                        }
                                    )
                                ).opt
                            }
                        ))

                /*case uploadFileField.name ⇒
                    any2undefOrA(
                        DynamicFormSS.create(
                            new DynamicFormSSProps {
                                width = "100%"
                                /*action = s"UploadServlet?channelMessageEndUpload=${thiz.channelMessageEndUpload.get}&channelMessageNextStep=${thiz.channelMessageNextStep.get}&channelMessageMaxValue=${thiz.channelMessageMaxValue.get}&channelMessageRecordInBase=${thiz.channelMessageRecordInBase.get}&channelMessageError=${thiz.channelMessageError.get}".opt*/
                                target = Iframe.create(
                                    new IframeProps
                                ).ID.opt
                                encoding = Encoding.multipart.opt
                                canSubmit = true.opt
                                items = Seq(
                                    UploadItem(
                                        new UploadItemProps {
                                            showTitle = false.opt
                                            changed = {
                                                (form: DynamicFormSS, item: UploadItem, value: JSUndefined[JSAny]) ⇒
                                                    isc.confirm(s"Выбран файл: ${value.asInstanceOf[String].replace("C:\\fakepath\\", "")}, выгружать?", {
                                                        (value: Boolean) ⇒
                                                            if (value)
                                                                isc.ok(value.toString)
                                                        //form.submitForm()
                                                    }.toFunc)

                                            }.toFunc.opt
                                        }
                                    )
                                ).opt
                            }
                        )
                    )*/
                case _ ⇒
                    jSUndefined

            }
    }.toThisFunc.opt
}
