package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.LookupEditorItem
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.{Grid, ListGrid, ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, OkCancelPanelProps, WindowSSProps}
import com.simplesys.SmartClient.System.{Common, HLayoutSS, IButtonSS, _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js


class LookupEditorItemProps extends CanvasItemProps {
    type classHandler <: LookupEditorItem

    var buttonIcon: ScOption[SCImgURL] = ScNone
    var editor: ScOption[Canvas] = ScNone
    align = Alignment.center.opt

    shouldSaveValue = true.opt

    setValue = {
        (thiz: classHandler, value: JSAny) =>
            thiz.textItem setValue value
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
                            if (formItem.editor.isEmpty)
                                isc.error("Отсутствует редактор.")
                            else {
                                formItem.editor.foreach {
                                    editor =>

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

                                            val foreignIdField = form.dataSource.getField(formItem.foreignField.get).get
                                            val idFieldName = foreignIdField.foreignKey.substring(foreignIdField.foreignKey.lastIndexOf(".") + 1)
                                            val idField = form.getItem(formItem.foreignField.get)

                                            if (idField == null && formItem.record.isEmpty)
                                                isc.error(s"Нет поля ${formItem.foreignField.get}")
                                            else {
                                                window.addItems(
                                                    IscArray(
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

                                                                        var editorSelectedRecord: JSUndefined[Record] = jSUndefined
                                                                        var editorDataSource: JSUndefined[DataSource] = jSUndefined

                                                                        if (isc.isA.ListGrid(editor)) {
                                                                            val _editor = editor.asInstanceOf[ListGrid]
                                                                            editorDataSource = _editor.dataSource
                                                                            editorSelectedRecord = _editor.getSelectedRecord()
                                                                        }
                                                                        else if (isc.isA.ListGridEditor(editor)) {
                                                                            val _editor = editor.asInstanceOf[ListGridEditor]
                                                                            editorDataSource = _editor.dataSource
                                                                            editorSelectedRecord = _editor.getSelectedRecord()
                                                                        } else if (isc.isA.TreeGridEditor(editor)) {
                                                                            val _editor = editor.asInstanceOf[TreeGridEditor]
                                                                            editorDataSource = _editor.dataSource
                                                                            editorSelectedRecord = _editor.getSelectedRecord()
                                                                        }

                                                                        if (editorSelectedRecord.isEmpty)
                                                                            isc.error("Не возможно выделить значение для ввода.")
                                                                        else {
                                                                            val valueId = editorSelectedRecord.asInstanceOf[JSDynamic].selectDynamic(idFieldName)
                                                                            if (formItem.record.isEmpty)
                                                                                idField.setValue(valueId)
                                                                            else
                                                                                formItem.record.foreach(_.asInstanceOf[JSDynamic].updateDynamic(item.name)(valueId))

                                                                            editorSelectedRecord.foreach {
                                                                                record =>
                                                                                    val recordFields = js.Object.keys(record)
                                                                                    recordFields.foreach {
                                                                                        field =>
                                                                                            editorDataSource.foreach {
                                                                                                dataSource =>
                                                                                                    if (dataSource.getField(field).isDefined)
                                                                                                        if (!dataSource.getField(field).get.primaryKey.getOrElse(false))
                                                                                                            form.setValue(field, editorSelectedRecord.asInstanceOf[JSDynamic].selectDynamic(field))
                                                                                            }
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

    `type` = FormItemComponentType.LookupEditorItem
}
