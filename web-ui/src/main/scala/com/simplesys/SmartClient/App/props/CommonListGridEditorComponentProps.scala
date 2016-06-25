package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonListGridEditorComponent
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps}
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

            val _fields = ArrayBuffer.empty[ListGridField]

            if (thiz.fields.isDefined && thiz.replacingfields.isDefined && thiz.replacingfields.get.length > 0) {
                var allFieldsValid = true

                thiz.fields.get.foreach {
                    field =>
                        if (field.nameStrong.isEmpty)
                            if (allFieldsValid)
                                allFieldsValid = false
                }

                if (allFieldsValid)
                    thiz.replacingfields.get.foreach {
                        replacingfield =>
                            if (replacingfield.nameStrong.isEmpty)
                                if (allFieldsValid)
                                    allFieldsValid = false
                    }


                if (allFieldsValid) {
                    thiz.fields.get.foreach {
                        field =>
                            thiz.replacingfields.get.find(_.nameStrong == field.nameStrong) match {
                                case None =>
                                    field.asInstanceOf[JSDynamic]("name")(field.nameStrong)
                                    _fields += field
                                case Some(field) =>
                                    field.asInstanceOf[JSDynamic]("name")(field.nameStrong)
                                    _fields += field
                            }
                    }

                    //isc debugTrap(thiz.fields, _fields)
                    thiz.fields = IscArray(_fields: _*)
                    //isc debugTrap(thiz.fields, _fields)

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
