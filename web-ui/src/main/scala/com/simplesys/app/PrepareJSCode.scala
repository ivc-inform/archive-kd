package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{CommonListGridEditorComponent, ListGridEditor, isc}

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode{

    @JSExport
    override def createJS(): Unit = {
        isc.defineClass(AbonentsOrg.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(AbonentsTypes.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Abonents.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
    }
}
