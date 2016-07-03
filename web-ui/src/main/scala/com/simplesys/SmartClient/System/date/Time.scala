package com.simplesys.SmartClient.System.date

import com.simplesys.SmartClient.System.AbstractClassCompanion

import scala.scalajs.js

@js.native
trait Time extends com.simplesys.SmartClient.System.Class {
}

@js.native
abstract trait AbstractTimeCompanion extends AbstractClassCompanion {
    def format (date: Date, formatter: String, shortFormat: Boolean = js.native, logicalTime: Boolean = js.native): String = js.native
}

/*@js.native
object Time extends AbstractTimeCompanion*/
