package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.ProgressbarProps
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.formsItems.UploadItem
import com.simplesys.SmartClient.Forms.formsItems.props.UploadItemProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.IframeProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.System.Types.{Encoding, ListGridFieldType}
import com.simplesys.js.components.cards.Attach
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import com.simplesys.System._
import com.simplesys.SmartClient.System._
import ru.simplesys.defs.app.scala.container.arx.AttatchDataRecord
import com.simplesys.function._

import scala.scalajs.js.UndefOr._

trait AttatchDataRecordExt extends AttatchDataRecord {
  val fileName: JSUndefined[String]
  val uploadFile: JSUndefined[String]
  val vname: JSUndefined[String]
  val viztname: JSUndefined[String]
}

class AttachProps extends CommonListGridEditorComponentProps {
    type classHandler <: Attach

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


    val fileNameField = "fileName".nameStrong
    val uploadFileField = "uploadFile".nameStrong

    fields = (ListGridFiledsJS.arx_attatch_FLDS ++ Seq(
        new ListGridFieldProps {
            nameStrong = uploadFileField.opt
            title = "Файл ревизии".ellipsis.opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
        },
        new ListGridFieldProps {
            nameStrong = fileNameField.opt
            title = "Файл ревизии".opt
            `type` = ListGridFieldType.sCode_SimpleType.opt
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
                    any2undefOrA(Progressbar.create(
                        new ProgressbarProps {
                            height = 20
                            width = "100%"
                            title = record.fileName.opt
                            showTitle = true.opt
                        }
                    ))

                case uploadFileField.name ⇒
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
                                                   isc.confirm(s"Выбран файл: ${value.asInstanceOf[String].replace("C:\\fakepath\\", "")}, выгружать?" , {
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
                    )
                case _ ⇒
                    jSUndefined

            }
    }.toThisFunc.opt
}
