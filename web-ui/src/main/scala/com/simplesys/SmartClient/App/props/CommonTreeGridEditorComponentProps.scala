package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonTreeGridEditorComponent
import com.simplesys.SmartClient.Control.props.TreeGridContextMenuProps
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.TreeGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.collection.mutable.ArrayBuffer

trait CommonTreeGridEditorComponentProps extends TreeGridEditorProps {

    type classHandler <: CommonTreeGridEditorComponent

    height = "100%"
    width = "100%"
    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    selectionType = SelectionStyle.single.opt
    folderIcon = Common.iconFolder.opt
    nodeIcon = Common.iconTreeNode.opt
    showOpenIcons = false.opt
    openIconSuffix = "".opt
    dropIconSuffix = "".opt
    closedIconSuffix = "".opt
    selectFirstRecordAfterFetch = false.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.fields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #39")))

            val replacingEditingFields = ArrayBuffer.empty[FormItem]

            thiz.replacingFields.foreach {
                _.foreach {
                    field =>
                        if (field.nameStrong.isDefined) {
                            field._name = field.nameStrong.get.name
                            field.editorProperties.foreach {
                                formItem =>
                                    formItem._name = field._name
                                    formItem.nameStrong = new NameStrong {
                                        override val name = field._name
                                    }
                                    replacingEditingFields += formItem
                            }

                        }
                        else
                            thiz.logError("Field not have nameStrong, error #77")
                }
            }

            thiz.editingFields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #60")))

            val _fieldsListGrid = ArrayBuffer.empty[ListGridField]
            val _fieldsFormItem = ArrayBuffer.empty[FormItem]

            val enableReplacingField = thiz.fields.isDefined && thiz.replacingFields.isDefined
            val enableReplacingFormItem = thiz.editingFields.isDefined && replacingEditingFields.length > 0

            if (enableReplacingField) {
                thiz.fields.get.foreach {
                    field =>
                        thiz.replacingFields.get.find(_._name == field._name) match {
                            case None =>
                                _fieldsListGrid += field
                            case Some(field) =>
                                field.filterEditorProperties.filteredGridTree = thiz
                                _fieldsListGrid += field
                        }
                }

                thiz.fields = IscArray(_fieldsListGrid: _*)
            }

            if (enableReplacingFormItem) {
                thiz.editingFields.get.foreach {
                    field =>
                        replacingEditingFields.find(_._name == field._name) match {
                            case None =>
                                _fieldsFormItem += field
                            case Some(field) =>
                                _fieldsFormItem += field
                        }
                }

                thiz.editingFields = IscArray(_fieldsFormItem: _*)
            }


            thiz.Super("initWidget", arguments)

            val funcMenu = TreeGridContextMenu.create(
                new TreeGridContextMenuProps {
                    owner = thiz.opt
                }
            )

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
