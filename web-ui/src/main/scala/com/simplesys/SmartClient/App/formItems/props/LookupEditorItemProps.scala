package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.LookupEditorItem
import com.simplesys.SmartClient.Control.props.{IButtonSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.{CanvasItem, TextItem}
import com.simplesys.SmartClient.Forms.FormsItems.props.{CanvasItemProps, TextItemProps}
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

import scala.scalajs.js.{ThisFunction0, ThisFunction1}


class LookupEditorItemProps extends CanvasItemProps {
    type classHandler <: LookupEditorItem

    var buttonIcon: ScOption[SCImgURL] = ScNone
    var editor: ScOption[Canvas] = ScNone
    var captionLookupFieldName: ScOption[String] = ScNone
    shouldSaveValue = true.opt

    var setValue: ScOption[ThisFunction1[classHandler, JSAny, _]] = {
        (thiz: classHandler, value: JSAny) =>
            if (thiz.textItem.isEmpty)
                isc.error("topThiz.textItem.")
            else
                thiz.textItem.foreach(_ setValue value)

    }.toThisFunc.opt

    var getValue: ScOption[ThisFunction0[classHandler, JSAny]] = {
        (thiz: classHandler) =>
            if (thiz.textItem.isEmpty) {
                isc.error("topThiz.textItem.")
                null
            }
            else
                thiz.textItem.get.getValue()

    }.toThisFunc.opt

    createCanvas = {
        (thiz: classHandler, form: DynamicFormSS, item: CanvasItem) =>
            val topThiz = thiz

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

            topThiz.textItem = df.getItem(0).asInstanceOf[TextItem]

            val button = IButtonSS.create(
                new IButtonSSProps {
                    iconAlign = "center".opt
                    icon = topThiz.buttonIcon.getOrElse(Common.ellipsis).opt
                    width = 22
                    click = {
                        (thiz: classHandler) =>
                            val textItem = df.getItem(0)

                            if (topThiz.editor.isEmpty)
                                isc.error("Отсутствует редактор.")
                            else {
                                topThiz.editor.foreach {
                                    editor =>

                                        if (!topThiz.lookup.getOrElse(false))
                                            isc.error("Поле не является полем lookup")
                                        else if (topThiz.foreignField.isEmpty)
                                            isc.error("Нет значения для foreignField.")
                                        else if (topThiz.captionLookupFieldName.isEmpty)
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
                                                    title = s"${topThiz.captionClassLookup.getOrElse("Неизвестное поле captionClassLookup.")}".ellipsis.opt
                                                    headerIconPath = Common.iconEdit.opt
                                                }
                                            )

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

                                                                    var selectedRecord: JSUndefined[Record] = jSUndefined

                                                                    if (isc.isA.ListGrid(editor)) {
                                                                        val _editor = editor.asInstanceOf[ListGrid]
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    }
                                                                    else if (isc.isA.ListGridEditor(editor)) {
                                                                        val _editor = editor.asInstanceOf[ListGridEditor]
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    } else if (isc.isA.TreeGridEditor(editor)) {
                                                                        val _editor = editor.asInstanceOf[TreeGridEditor]
                                                                        selectedRecord = _editor.getSelectedRecord()
                                                                    }

                                                                    if (selectedRecord.isEmpty)
                                                                        isc.error("Не возможно выделить значение для ввода.")
                                                                    else {
                                                                        val foreignIdField = form.dataSource.getField(topThiz.foreignField.get)
                                                                        val idFieldName = foreignIdField.foreignKey.substring(foreignIdField.foreignKey.lastIndexOf(".") + 1)

                                                                        val idField = form.getItem(topThiz.foreignField.get)

                                                                        if (idField == null)
                                                                            isc.error(s"Нет поля ${topThiz.foreignField.get}")
                                                                        else {
                                                                            val valueId = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(idFieldName)
                                                                            idField.setValue(valueId)
                                                                            val lookupCaption = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(topThiz.captionLookupFieldName.get)

                                                                            textItem setValue lookupCaption
                                                                            item setValue lookupCaption
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
