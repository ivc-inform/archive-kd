package com.simplesys.app

import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{CommonListGridEditorComponent, HLayout, HLayoutSS, ImgButton, VLayoutSS, WindowSSDialog, isc}

import scala.scalajs.js.annotation.JSExportTopLevel

object PrepareJSCode extends StaticJSCode {
    @JSExportTopLevel("CreateAppJS")
    override def createJS(): Unit = {
        //        isc.defineClass(AbonentsOrg.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(AbonentsTypes.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(Abonents.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(AttachTypes.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(AuStat.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocTypes.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocFormats.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocCats.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocItem.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocIzvStat.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocIzvType.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocLiter.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(MType.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(GroupItem.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(State.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(Status.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(StatVersion.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(MVid.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)

        isc.defineClass(Cards.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Attach.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(AttachRowComponent.getClass.getSimpleName, HLayoutSS.getClass.getSimpleName)
        isc.defineClass(ImgButtonAttatch.getClass.getSimpleName, ImgButton.getClass.getSimpleName)
        isc.defineClass(WindowUploadDialog.getClass.getSimpleName, WindowSSDialog.getClass.getSimpleName)

        //        isc.defineClass(Zapros.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        //        isc.defineClass(DocIzv.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)

        isc.defineClass(ArxUser.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(UploadTestTab.getClass.getSimpleName, HLayoutSS.getClass.getSimpleName)
    }
}
