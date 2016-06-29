package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonListGridEditorComponent
import com.simplesys.SmartClient.App.formItems.LookupListGridEditorItem
import com.simplesys.SmartClient.App.formItems.props.LookupListGridEditorItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps}
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, SelectionAppearance, SelectionStyle}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.collection.mutable.ArrayBuffer

trait CommonListGridEditorComponentProps extends ListGridEditorProps with InitTrait {
    val simpleTable: Boolean

    type classHandler <: CommonListGridEditorComponent

    height = "100%"
    width = "100%"
    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    //selectionType = SelectionStyle.multiple.opt
    selectionType = SelectionStyle.single.opt
    selectFirstRecordAfterFetch = false.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.fields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #36")))

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

            val funcMenu = if (simpleTable)
                ListGridContextMenu.create(
                    new ListGridContextMenuProps {
                        owner = thiz.opt
                    }
                )
            else
                ListGridContextMenuWithForm.create(
                    new ListGridContextMenuWithFormProps {
                        owner = thiz.opt
                    }
                )

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
