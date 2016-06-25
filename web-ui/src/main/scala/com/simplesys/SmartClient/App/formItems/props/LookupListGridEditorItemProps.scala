package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.LookupListGridEditorItem
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, OkCancelPanelProps, WindowSSProps}
import com.simplesys.SmartClient.System.{Common, HLayoutSS, IButtonSS, _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js


class LookupListGridEditorItemProps extends CanvasItemProps {
    type classHandler <: LookupListGridEditorItem

    var buttonIcon: ScOption[SCImgURL] = ScNone
    var listGridEditor: ScOption[ListGridEditor] = ScNone

    align = Alignment.center.opt

    shouldSaveValue = true.opt

    setValue = {
        (thiz: classHandler, value: JSAny) =>
            thiz.textItem setValue value

            thiz.listGridEditor.foreach {
                editor =>
                    thiz.record.foreach {
                        record =>
                            if (thiz.form.isEmpty)
                                isc.error("Не определено свойство 'thiz.form' error #42")
                            else
                                thiz.form.foreach {
                                    form =>
                                        if (form.dataSource.isEmpty)
                                            isc.error("Не определено свойство 'form.dataSource' error #47")
                                        else
                                            form.dataSource.foreach {
                                                dataSource =>
                                                    val foreignIdField = dataSource.getField(thiz.foreignField.get).get
                                                    val idFieldName = foreignIdField.foreignKey.substring(foreignIdField.foreignKey.lastIndexOf(".") + 1)
                                                    val idFieldName1 = foreignIdField.name

                                                    val id = record.asInstanceOf[JSDynamic].selectDynamic(idFieldName1)

                                                    val keyValues = js.Object()
                                                    keyValues.asInstanceOf[JSDynamic].updateDynamic(idFieldName)(id)
                                                    //isc debugTrap editor
                                                    editor.deselectAllRecords()
                                                    editor selectRecordsByKey keyValues
                                            }
                                }
                    }
            }

            thiz.Super("setValue", IscArray(value))
    }.toThisFunc.opt

    createCanvas = {
        (thiz: classHandler, form: DynamicFormSS, item: CanvasItem) =>

            val formItem = thiz

            val df = DynamicFormSS.create(
                new DynamicFormSSProps {
                    cellPadding = 0.opt
                    width = "*"
                    minColWidth = 0.opt
                    colWidths = Seq[JSAny](0, "*").opt
                    items = Seq(
                        TextItem(
                            new TextItemProps {
                                colSpan = 2.opt
                                name = s"${item.name}_inner".opt
                                width = "*"
                                showTitle = false.opt
                                value = item.value.opt
                            }
                        )
                    ).opt
                }
            )

            thiz.textItem = df.getItem(0).asInstanceOf[TextItem]

            val button = IButtonSS.create(
                new IButtonSSProps {
                    iconAlign = "center".opt
                    icon = thiz.buttonIcon.getOrElse(Common.ellipsis).opt
                    width = 22
                    click = {
                        (thiz: classHandler) =>
                            if (formItem.listGridEditor.isEmpty)
                                isc.error("Отсутствует редактор.")
                            else {
                                formItem.listGridEditor.foreach {
                                    editor =>
                                        //isc debugTrap editor

                                        if (!formItem.lookup.getOrElse(false))
                                            isc.error("Поле не является полем lookup")
                                        else if (formItem.foreignField.isEmpty)
                                            isc.error("Нет значения для foreignField.")
                                        else {
                                            val window = WindowSS.create(
                                                new WindowSSProps {
                                                    height = 400
                                                    width = 300
                                                    isModal = true.opt
                                                    autoPosition = true.opt
                                                    showMaximizeButton = false.opt
                                                    showMinimizeButton = false.opt
                                                    identifier = s"${form.identifier}_lookup_${item.name}".opt
                                                    title = s"${formItem.captionClassLookup.getOrElse("Неизвестное поле captionClassLookup.")}".ellipsis.opt
                                                    headerIconPath = Common.iconEdit.opt
                                                }
                                            )

                                            if (form.dataSource.isEmpty)
                                                isc.error("Не определено свойство 'form.dataSource' error #132")
                                            else
                                                form.dataSource.foreach {
                                                    dataSource =>
                                                        val foreignIdField = dataSource.getField(formItem.foreignField.get).get
                                                        val idFieldName = foreignIdField.foreignKey.substring(foreignIdField.foreignKey.lastIndexOf(".") + 1)
                                                        val idField = form.getItem(formItem.foreignField.get)

                                                        if (idField == null && formItem.record.isEmpty)
                                                            isc.error(s"Нет поля ${formItem.foreignField.get}")
                                                        else
                                                            window.addItems(
                                                                IscArray[Canvas](
                                                                    editor,
                                                                    OkCancelPanel.create(
                                                                        new OkCancelPanelProps {
                                                                            owner = window.opt
                                                                            padding = 5.opt
                                                                            okCaption = "Выбрать".opt
                                                                            ownerDestroy = false.opt
                                                                            ownerHide = true.opt
                                                                            owner = window.opt
                                                                            okFunction = {
                                                                                (thiz: classHandler) =>

                                                                                    if (editor.getSelectedRecords().length != 1)
                                                                                        isc.error("Не возможно выделить значение для ввода.")
                                                                                    else {
                                                                                        val valueId = editor.getSelectedRecord().asInstanceOf[JSDynamic].selectDynamic(idFieldName)
                                                                                        //isc debugTrap(formItem.foreignField.get, item.name, valueId, formItem.record)

                                                                                        if (formItem.record.isEmpty)
                                                                                            idField.setValue(valueId)
                                                                                        else
                                                                                            formItem.record.foreach(_.asInstanceOf[JSDynamic].updateDynamic(formItem.foreignField.get)(valueId))

                                                                                        val record = editor.getSelectedRecord()

                                                                                        val recordFields = js.Object.keys(record)
                                                                                        recordFields.foreach {
                                                                                            field =>
                                                                                                if (editor.dataSource.getField(field).isDefined)
                                                                                                    if (!editor.dataSource.getField(field).get.primaryKey.getOrElse(false)) {
                                                                                                        //isc debugTrap (field, editor.getSelectedRecord().asInstanceOf[JSDynamic].selectDynamic(field))
                                                                                                        form.setValue(field, editor.getSelectedRecord().asInstanceOf[JSDynamic].selectDynamic(field))
                                                                                                    }
                                                                                        }
                                                                                    }
                                                                            }.toThisFunc.opt
                                                                        }
                                                                    )
                                                                )
                                                            )
                                                }
                                        }
                                }
                            }

                            false
                    }.toThisFunc.opt
                }
            )
            HLayoutSS.create(
                new HLayoutSSProps {
                    height = 20
                    members = Seq(
                        df,
                        button
                    ).opt
                }
            )
    }.toThisFunc.opt

    `type` = FormItemComponentType.LookupListGridEditorItem
}
