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

            val _fieldsListGrid = ArrayBuffer.empty[ListGridField]
            val _fieldsFormItem = ArrayBuffer.empty[FormItem]

            isc debugTrap(thiz.fields, thiz.replacingFields)
            val enableReplacingField = thiz.fields.isDefined && thiz.replacingFields.isDefined && thiz.replacingFields.get.length > 0
            isc debugTrap enableReplacingField
            val enableReplacingFormItem = thiz.editingFields.isDefined && thiz.replacingEditingFields.isDefined && thiz.replacingEditingFields.get.length > 0
            isc debugTrap enableReplacingFormItem

            if (enableReplacingField || enableReplacingFormItem) {
                var allFieldsValid = true

                //<editor-fold desc="Проверка наличия поля NameStrong">
                if (enableReplacingField) {
                    isc debugTrap thiz.fields
                    thiz.fields.get.foreach {
                        field =>
                            if (field.nameStrong.isEmpty)
                                if (allFieldsValid)
                                    allFieldsValid = false
                    }

                    isc debugTrap thiz.replacingFields
                    if (allFieldsValid)
                        thiz.replacingFields.get.foreach {
                            replacingField =>
                                if (replacingField.nameStrong.isEmpty)
                                    if (allFieldsValid)
                                        allFieldsValid = false
                        }
                }

                if (enableReplacingFormItem) {
                    isc debugTrap thiz.editingFields
                    thiz.editingFields.get.foreach {
                        field =>
                            if (field.nameStrong.isEmpty)
                                if (allFieldsValid)
                                    allFieldsValid = false
                    }

                    isc debugTrap thiz.replacingEditingFields
                    if (allFieldsValid)
                        thiz.replacingEditingFields.get.foreach {
                            replacingField =>
                                if (replacingField.nameStrong.isEmpty)
                                    if (allFieldsValid)
                                        allFieldsValid = false
                        }
                }
                //</editor-fold>

                isc debugTrap allFieldsValid
                if (allFieldsValid) {
                    if (enableReplacingField) {
                        thiz.fields.get.foreach {
                            field =>
                                thiz.replacingFields.get.find(_.nameStrong == field.nameStrong) match {
                                    case None =>
                                        isc debugTrap field
                                        field._name = field.nameStrong.get.name
                                        isc debugTrap field
                                        _fieldsListGrid += field
                                    case Some(field) =>
                                        isc debugTrap field
                                        field._name = field.nameStrong.get.name
                                        isc debugTrap field
                                        _fieldsListGrid += field
                                }
                        }

                        isc debugTrap(thiz.fields, _fieldsListGrid)
                        thiz.fields = IscArray(_fieldsListGrid: _*)
                        isc debugTrap(thiz.fields, _fieldsListGrid)
                    }

                    if (enableReplacingFormItem) {
                        thiz.editingFields.get.foreach {
                            field =>
                                thiz.replacingEditingFields.get.find(_.nameStrong == field.nameStrong) match {
                                    case None =>
                                        isc debugTrap field
                                        field._name = field.nameStrong.get.name
                                        isc debugTrap field
                                        _fieldsFormItem += field
                                    case Some(field) =>
                                        isc debugTrap field
                                        field._name = field.nameStrong.get.name
                                        isc debugTrap field
                                        _fieldsFormItem += field
                                }
                        }

                        isc debugTrap(thiz.editingFields, _fieldsFormItem)
                        thiz.editingFields = IscArray(_fieldsFormItem: _*)
                        isc debugTrap(thiz.editingFields, _fieldsFormItem)
                    }

                    thiz.Super("initWidget", arguments)
                }

            } else
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
