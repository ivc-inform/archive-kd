package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.System.{RibbonGroup, RibbonGroupSS, SettingsEditor, WindowSS, isc}

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode{

    @JSExport
    override def createJS(): Unit = {
        isc.defineClass(RibbonGroupSS.getClass.getSimpleName,  RibbonGroup.getClass.getSimpleName)

        isc.defineClass(SettingsEditor.getClass.getSimpleName, WindowSS.getClass.getSimpleName)
    }
}

