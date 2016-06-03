package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}
import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
trait JSON extends Class {
}

@js.native
abstract trait AbstractJSONCompanion extends AbstractClassCompanion {
    def decode(jsonString: String): JSObject = js.native
    def encode(obj: JSObject, settings: JSONEncoder = js.native): String = js.native
    def printObject(obj: JSObject): String = js.native
}

@js.native
object JSON extends AbstractJSONCompanion

