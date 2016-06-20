package com.simplesys.SmartClient.Grids.editors.props

import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.FormsItems.TextItem
import com.simplesys.SmartClient.Forms.FormsItems.props.TextItemProps
import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.Grids.editors.LookupEditor
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, OkCancelPanelProps, WindowSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.System.JSAny
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction, ThisFunction1}


class LookupEditorProps extends HLayoutSSProps {
    type classHandler <: LookupEditor

    var editedFieldName: ScOption[String] = ScNone
    var record: ScOption[Record] = ScNone

    width = "100%"
    height = 22
    align = Alignment.center

    var setValue: ScOption[ThisFunction1[classHandler, JSAny, _]] = {
        (thiz: classHandler, value: JSAny) =>
            if (thiz.textItem.isEmpty)
                isc.error("topThiz.textItem.")
            else
                thiz.textItem.foreach(_ setValue value)

    }.toThisFunc.opt

    var setValueFromRecord: ScOption[ThisFunction1[classHandler, Record, _]] = {
        (thiz: classHandler, record: Record) =>
            thiz setValue record.asInstanceOf[JSDynamic].selectDynamic(thiz.editedFieldName.get)

    }.toThisFunc.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            val topThiz = thiz

            topThiz.Super("initWidget", arguments)

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
                                width = "*"
                                showTitle = false.opt
                            }
                        )
                    ).opt
                }
            )

            topThiz.textItem = df.getItem(0).asInstanceOf[TextItem]
            topThiz.record.foreach {
                record =>
                    if (topThiz.editedFieldName.isEmpty)
                        isc.error("topThiz.editedFieldName.")
                    else
                        topThiz setValueFromRecord record
            }

            val button = IButtonSS.create(
                new IButtonSSProps {
                    iconAlign = "center".opt
                    icon = topThiz.buttonIcon.getOrElse(Common.ellipsis).opt
                    width = 22
                    visibility = Visibility.hidden.opt
                    click = {
                        (thiz: classHandler) =>
                            if (topThiz.editor.isEmpty)
                                isc.error("Не найден topThiz.editor.")
                            else {
                                val window = WindowSS.create(
                                    new WindowSSProps {
                                        height = 400
                                        width = 300
                                        isModal = true.opt
                                        autoPosition = true.opt
                                        showMaximizeButton = false.opt
                                        showMinimizeButton = false.opt
                                        identifier = s"${topThiz.identifier}_lookup".opt
                                        title = s"${topThiz.titleEditor.getOrElse("Нет заглавия !!!!")}".ellipsis.opt
                                        headerIconPath = Common.iconEdit.opt
                                    }
                                )

                                window.addItems(
                                    IscArray(
                                        topThiz.editor.get,
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


                                                }.toThisFunc.opt
                                            }
                                        )
                                    )
                                )
                            }
                            false
                    }.toThisFunc.opt
                }
            )
            topThiz.addMembers(IscArray(
                df, button
            ))

    }.toThisFunc.opt
}
