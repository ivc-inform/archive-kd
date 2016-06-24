package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.System.{IscArray, isc}

import scala.collection.mutable.ArrayBuffer

trait MergerFields {
    self: Canvas =>
    def mergeFields(thiz: Canvas): Boolean = {
        val _fields = ArrayBuffer.empty[ListGridField]
        var res = false



        if (thiz.fields.isDefined && thiz.replacingfields.isDefined) {
            var replacingDieldIdValid = true

            thiz.replacingfields.foreach {
                replacingfields =>
                    replacingfields.foreach {
                        replacingfield =>
                            if (!thiz.fields.get.exists(_.name == replacingfield.name)) {
                                isc.error(s"Компонент ${thiz.getIdentifier()} не имеет поля ${replacingfield.name}")
                                if (replacingDieldIdValid)
                                    replacingDieldIdValid = false
                            }
                    }
            }

            if (replacingDieldIdValid) {
                thiz.fields.get.foreach {
                    field =>
                        thiz.replacingfields.get.find(_.name == field.name) match {
                            case None =>
                                _fields += field
                            case Some(field) =>
                                _fields += field
                        }
                }

                isc debugTrap(thiz.fields, _fields)
                thiz.fields = IscArray(_fields: _*)
                isc debugTrap(thiz.fields, _fields)
                thiz.Super("initWidget", arguments)
                res = true
            }
        }

        res
    }
}
