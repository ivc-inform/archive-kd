package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.LookupEditorItem
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, OkCancelPanelProps, WindowSSProps}
import com.simplesys.SmartClient.System.{Common, HLayoutSS, IButtonSS, _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}


class LookupEditorItemProps extends CanvasItemProps {
    type classHandler <: LookupEditorItem

    var buttonIcon: ScOption[SCImgURL] = ScNone
    var editor: ScOption[Canvas] = ScNone
    var captionLookupFieldName: ScOption[String] = ScNone
    shouldSaveValue = true.opt

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

            val textItem = df.getItem(0).asInstanceOf[TextItem]

            if (textItem != null)
                form.grid.foreach {
                    grid =>
                        isc debugTrap(grid, grid.getSelectedRecord())
                        val value = grid.getSelectedRecord().asInstanceOf[JSDynamic].selectDynamic(item.name)
                        textItem setValue value
                }

            val button = IButtonSS.create(
                new IButtonSSProps {
                    iconAlign = "center".opt
                    icon = thiz.buttonIcon.getOrElse(Common.ellipsis).opt
                    width = 22
                    click = {
                        (thiz: classHandler) =>
                            val textItem = df.getItem(0)

                            if (formItem.editor.isEmpty)
                                isc.error("Отсутствует редактор.")
                            else {
                                formItem.editor.foreach {
                                    editor =>

                                        if (!formItem.lookup.getOrElse(false))
                                            isc.error("Поле не является полем lookup")
                                        else if (formItem.foreignField.isEmpty)
                                            isc.error("Нет значения для foreignField.")
                                        else if (formItem.captionLookupFieldName.isEmpty)
                                            isc.error("Нет значения для captionLookupFieldName.")
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

                                            window.addItems(
                                                IscArray(
                                                    editor,
                                                    OkCancelPanel.create(
                                                        new OkCancelPanelProps {
                                                            owner = thiz.opt
                                                            padding = 5.opt
                                                            okCaption = "Выбрать".opt
                                                            ownerDestroy = false.opt
                                                            ownerHide = true.opt
                                                            owner = window.opt
                                                            okFunction = {
                                                                (thiz: classHandler) =>

                                                                    var selectedRecord: JSUndefined[Record] = jSUndefined
                                                                    var editorDataSource: JSUndefined[DataSource] = jSUndefined

                                                                    if (isc.isA.ListGrid(editor)) {
                                                                        val _editor = editor.asInstanceOf[ListGrid]
                                                                        editorDataSource = _editor.dataSource
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    }
                                                                    else if (isc.isA.ListGridEditor(editor)) {
                                                                        val _editor = editor.asInstanceOf[ListGridEditor]
                                                                        editorDataSource = _editor.dataSource
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    } else if (isc.isA.TreeGridEditor(editor)) {
                                                                        val _editor = editor.asInstanceOf[TreeGridEditor]
                                                                        editorDataSource = _editor.dataSource
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    }

                                                                    isc debugTrap selectedRecord

                                                                    if (selectedRecord.isEmpty)
                                                                        isc.error("Не возможно выделить значение для ввода.")
                                                                    else {
                                                                        //isc debugTrap formItem
                                                                        val foreignIdField = form.dataSource.getField(formItem.foreignField.get)
                                                                        //isc debugTrap foreignIdField
                                                                        val idFieldName = foreignIdField.foreignKey.substring(foreignIdField.foreignKey.lastIndexOf(".") + 1)
                                                                        //isc debugTrap idFieldName

                                                                        val idField = form.getItem(formItem.foreignField.get)
                                                                        //isc debugTrap(form, idField)

                                                                        if (idField == null && form.grid.isEmpty)
                                                                            isc.error(s"Нет поля ${formItem.foreignField.get}")
                                                                        else {
                                                                            val valueId = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(idFieldName)
                                                                            if (form.grid.isEmpty)
                                                                                idField.setValue(valueId)
                                                                            else
                                                                                form.grid.foreach(_.getSelectedRecord().asInstanceOf[JSDynamic].updateDynamic(item.name)(valueId))
                                                                            val lookupCaption = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(formItem.captionLookupFieldName.get)

                                                                            textItem setValue lookupCaption
                                                                            item setValue lookupCaption

                                                                            editorDataSource.foreach {
                                                                                dataSource =>
                                                                                    dataSource.fields.filter(!_.primaryKey) foreach {
                                                                                        field =>
                                                                                            if (form.grid.isEmpty)
                                                                                                form.setValue(field.name, selectedRecord.asInstanceOf[JSDynamic].selectDynamic(field.name))
                                                                                            else
                                                                                                form.grid.foreach(_.getSelectedRecord().asInstanceOf[JSDynamic].updateDynamic(field.name)(selectedRecord.asInstanceOf[JSDynamic].selectDynamic(field.name)))
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
