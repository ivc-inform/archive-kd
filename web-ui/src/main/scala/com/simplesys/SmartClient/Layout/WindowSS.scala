package com.simplesys.SmartClient.Layout

import scala.scalajs.js

@js.native
trait WindowSS extends Window {
    var headerIconPath: String
    var autoPosition: Boolean
}

@js.native
abstract trait AbstractWindowSSCompanion extends AbstractWindowCompanion {
}

