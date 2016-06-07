package com.simplesys.app

import com.simplesys.SmartClient.System.{RibbonGroup, RibbonGroupSS, isc}
import com.simplesys.js.com.simplesys.app.StaticJSCode

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode{

    @JSExport
    override def createJS(mode: Int): Unit = {
        isc.defineClass(RibbonGroupSS.getClass.getSimpleName, RibbonGroup.getClass.getSimpleName)
    }
}
