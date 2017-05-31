package com.simplesys.SmartClient.Workdlow

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.|

@js.native
trait Task extends ProcessElement {
}

@js.native
abstract trait AbstractTaskCompanion extends AbstractProcessElementCompanion {
}

@js.native
@JSGlobal
object Task extends AbstractTaskCompanion

