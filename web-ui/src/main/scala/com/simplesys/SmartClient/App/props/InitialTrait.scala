package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.{ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System._

import scala.collection.mutable.ArrayBuffer

trait InitialTrait {

    def initWidget(thiz: Canvas, fields: JSUndefined[IscArray[ListGridField]], replacingFields: JSUndefined[IscArray[ListGridField]], editingFields: JSUndefined[IscArray[FormItem]], arguments: IscArray[JSAny]): (JSUndefined[IscArray[ListGridField]], JSUndefined[IscArray[FormItem]]) = {
        fields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #36")))

        val replacingEditingFields = ArrayBuffer.empty[FormItem]

        var _fields = fields
        var _editingFields = editingFields

        replacingFields.foreach {
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

        editingFields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #60")))

        val _fieldsListGrid = ArrayBuffer.empty[ListGridField]
        val _fieldsFormItem = ArrayBuffer.empty[FormItem]

        val enableReplacingField = fields.isDefined && replacingFields.isDefined
        val enableReplacingFormItem = editingFields.isDefined && replacingEditingFields.length > 0

        if (enableReplacingField) {
            fields.get.foreach {
                field =>
                    replacingFields.get.find(_._name == field._name) match {
                        case None =>
                            _fieldsListGrid += field
                        case Some(field) =>
                            isc debugTrap field
                            if (isc.isA.ListGridEditor(thiz))
                                field.filterEditorProperties.filteredGridList = thiz.asInstanceOf[ListGridEditor]
                            else if (isc.isA.TreeGridEditor(thiz))
                                field.filterEditorProperties.filteredGridTree = thiz.asInstanceOf[TreeGridEditor]
                            else
                                thiz.logError("UnknownEditor, error #62")

                            _fieldsListGrid += field
                    }
            }

            _fields = IscArray(_fieldsListGrid: _*)
        }

        if (enableReplacingFormItem) {
            editingFields.get.foreach {
                field =>
                    replacingEditingFields.find(_._name == field._name) match {
                        case None =>
                            _fieldsFormItem += field
                        case Some(field) =>
                            _fieldsFormItem += field
                    }
            }

            _editingFields = IscArray(_fieldsFormItem: _*)
        }

        (_fields, _editingFields)
    }

}
