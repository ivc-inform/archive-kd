package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.System._

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode {

    @JSExport
    override def createJS(): Unit = {
        isc.defineClass(RibbonGroupSS.getClass.getSimpleName, RibbonGroup.getClass.getSimpleName)
        isc.defineClass(SettingsEditor.getClass.getSimpleName, WindowSS.getClass.getSimpleName)
        isc.defineClass(ListGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(ListGridContextMenuWithForm.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(TreeGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(UserComponentMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(EditorUsers.getClass.getSimpleName, TreeListGridEditor.getClass.getSimpleName)
        isc.defineClass(EditorUserGroups.getClass.getSimpleName, TreeGridEditor.getClass.getSimpleName)
        isc.defineClass(CommonListGridEditorComponent.getClass.getSimpleName, ListGridEditor.getClass.getSimpleName)
        isc.defineClass(CommonTreeGridEditorComponent.getClass.getSimpleName, TreeGridEditor.getClass.getSimpleName)
        isc.defineClass(LookupEditorItem.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.defineClass(LookupEditor.getClass.getSimpleName, Canvas.getClass.getSimpleName)
    }
}

