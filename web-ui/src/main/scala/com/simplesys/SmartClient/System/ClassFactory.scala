package com.simplesys.SmartClient.System

import com.simplesys.System.Types.void
import com.simplesys.System.{JSDictionaryAny, JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ClassFactoryTrt extends JSObject {
    def defineClass(className: String, superClass: Class = js.native): Class
    def getClass (className: String, warnOnFailure: Boolean = js.native): JSUndefined[Class]
    def GetClass(): Class
    def newInstance(className: String, props: JSObject | JSDictionaryAny = js.native, props2: JSObject | JSDictionaryAny = js.native, props3: JSObject | JSDictionaryAny = js.native): Class
    def overwriteClass(): void
    def addGlobalID(id: JSObject): void
}
