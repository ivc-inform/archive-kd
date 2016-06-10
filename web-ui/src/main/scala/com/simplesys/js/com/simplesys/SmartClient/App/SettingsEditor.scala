package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Layout.{AbstractWindowSSCompanion, WindowSS}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait SettingsEditor extends WindowSS {
    var identifierApp: JSUndefined[ID]
}

@js.native
abstract trait AbstractSettingsEditorCompanion extends AbstractWindowSSCompanion {
}

