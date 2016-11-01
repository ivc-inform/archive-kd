package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Layout.{AbstractWindowSSCompanion, WindowSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

@js.native
trait SettingsEditor extends WindowSS {
    var customSettingItems: JSUndefined[IscArray[FormItem]]
}

@js.native
abstract trait AbstractSettingsEditorCompanion extends AbstractWindowSSCompanion {
}

