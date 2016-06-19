package com.simplesys.SmartClient.App.formItems.props

import com.simplesys.SmartClient.App.formItems.LookupEditorItem
import com.simplesys.SmartClient.Control.props.{IButtonSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Forms.DynamicFormSS
import com.simplesys.SmartClient.Forms.FormsItems.CanvasItem
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


class LookupEditorItemProps extends CanvasItemProps {
    type classHandler <: LookupEditorItem

    var buttonIcon: ScOption[SCImgURL] = ScNone
    var editor: ScOption[Canvas] = ScNone

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

            val button = IButtonSS.create(
                new IButtonSSProps {
                    iconAlign = "center".opt
                    icon = thiz.buttonIcon.getOrElse(Common.ellipsis).opt
                    width = 22
                    click = {
                        (thiz: classHandler) =>
                            WindowSS.create(
                                new WindowSSProps {
                                    height = 400
                                    width = 300
                                    isModal = true.opt
                                    autoPosition = true.opt
                                    showMaximizeButton = false.opt
                                    showMinimizeButton = false.opt
                                    identifier = s"${form.identifier}_lookup_${item.name}".opt
                                    title = s"${item.title}".ellipsis.opt
                                    headerIconPath = Common.iconEdit.opt

                                    initWidget = {
                                        (thiz: classHandler, arguments: IscArray[JSAny]) =>
                                            thiz.Super("initWidget", arguments)
                                            val window = thiz


                                            if (item.editor.isEmpty)
                                                isc.error("Отсутствует редактор.")
                                            else
                                                item.editor.foreach {
                                                    editor =>
                                                        var selectedRecord: JSUndefined[Record] = jSUndefined
                                                        if (isc.isA.ListGrid(editor))
                                                            selectedRecord = editor.asInstanceOf[ListGrid].getSelectedRecord()
                                                        else if (isc.isA.ListGridEditor(editor))
                                                            selectedRecord = editor.asInstanceOf[ListGridEditor].getSelectedRecord()
                                                        else if (isc.isA.TreeGridEditor(editor))
                                                            selectedRecord = editor.asInstanceOf[TreeGridEditor].getSelectedRecord()

                                                        if (formItem.valueField.isEmpty)
                                                            isc.error("Нет значения для valueField.")
                                                        else if (formItem.displayField.isEmpty)
                                                            isc.error("Нет значения для displayField.")
                                                        else if (selectedRecord.isEmpty)
                                                            isc.error("Не возможно выделить значение для ввода.")
                                                        else {
                                                            thiz.addItems(
                                                                IscArray(
                                                                    editor,
                                                                    OkCancelPanel.create(
                                                                        new OkCancelPanelProps {
                                                                            owner = thiz.opt
                                                                            padding = 5.opt
                                                                            okCaption = "Выбрать".opt
                                                                            ownerDestroy = false.opt
                                                                            ownerHide = false.opt
                                                                            okFunction = {
                                                                                (thiz: classHandler) =>

                                                                                    val valueId = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(formItem.valueField.get)
                                                                                    val valueDisplay = selectedRecord.asInstanceOf[JSDynamic].selectDynamic(formItem.displayField.get)
                                                                                    isc debugTrap (valueId, valueDisplay)
                                                                                    window.markForDestroy()

                                                                            }.toThisFunc.opt
                                                                        }
                                                                    )
                                                                )
                                                            )
                                                        }


                                                }
                                    }.toThisFunc.opt
                                }
                            )

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
