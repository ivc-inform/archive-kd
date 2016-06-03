package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.OperatorId.OperatorId

import scala.scalajs.js

@js.native
trait Criterion extends JSObject {
    var criteria: IscArray[Criterion]
    var end: JSObject
    var fieldName: String
    var operator: OperatorId
    var start: JSObject
    var value: JSObject
    var _constructor: String
}
