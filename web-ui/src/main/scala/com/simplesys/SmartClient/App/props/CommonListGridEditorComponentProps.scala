package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonListGridEditorComponent
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps}
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.collection.mutable.ArrayBuffer

trait CommonListGridEditorComponentProps extends ListGridEditorProps {
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
                        //isc.debugTrap(field)
                        if (field.nameStrong.isDefined) {
                            field._name = field.nameStrong.get.name
                            field.editorProperties.foreach {
                                formItem =>
                                    formItem._name = field._name
                                    formItem.nameStrong = new NameStrong {
                                        override val name = field._name
                                    }
                                    replacingEditingFields += formItem
                                    //isc.debugTrap(replacingEditingFields)
                            }
                        }
                        else
                            thiz.logError("Field not have nameStrong, error #37")
                }
            }

            thiz.editingFields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #38")))
            //isc debugTrap replacingEditingFields

            val _fieldsListGrid = ArrayBuffer.empty[ListGridField]
            val _fieldsFormItem = ArrayBuffer.empty[FormItem]

            //isc debugTrap(thiz, thiz.fields, thiz.replacingFields, thiz.editingFields, replacingEditingFields)
            val enableReplacingField = thiz.fields.isDefined && thiz.replacingFields.isDefined
            val enableReplacingFormItem = thiz.editingFields.isDefined && replacingEditingFields.length > 0
            //isc debugTrap(enableReplacingField, enableReplacingFormItem)

            if (enableReplacingField || enableReplacingFormItem) {

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

                    //isc debugTrap(thiz.fields, _fieldsListGrid)
                    thiz.fields = IscArray(_fieldsListGrid: _*)
                    //isc debugTrap(thiz.fields, _fieldsListGrid)
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

                    isc debugTrap(thiz.editingFields, _fieldsFormItem, replacingEditingFields)
                    thiz.editingFields = IscArray(_fieldsFormItem: _*)
                    isc debugTrap(thiz.editingFields, _fieldsFormItem)
                }
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
