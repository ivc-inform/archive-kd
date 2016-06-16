package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{ListGridEditor, RibbonGroup, isc}
import com.simplesys.js.components.{AbonentsOrg, CommonListGridEditorComponent}

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode{

    @JSExport
    override def createJS(): Unit = {
        isc.defineClass(CommonListGridEditorComponent.getClass.getSimpleName, ListGridEditor.getClass.getSimpleName)
        isc.defineClass(AbonentsOrg.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
    }
}
