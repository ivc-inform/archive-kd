package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.DataBinding.dataSource.Operator
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.FieldType.FieldType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.OperatorValueType.OperatorValueType
import com.simplesys.System.Types.{ID, Record}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js

class OperatorProps extends AbstractClassProps {
    var condition: ScOption[js.Function5[JSAny, Record, JSAny, Criterion, Operator, Boolean]] = ScNone
    var editorType: ScOption[FormItem] = ScNone
    var fieldTypes: ScOption[IscArray[FieldType]] = ScNone
    var hidden: ScOption[Boolean] = ScNone
    var ID: ScOption[OperatorId] = ScNone
    var requiresServer: ScOption[Boolean] = ScNone
    var symbol: ScOption[String] = ScNone
    var title: ScOption[String] = ScNone
    var titleProperty: ScOption[ID] = ScNone
    var valueType: ScOption[OperatorValueType] = ScNone
}
