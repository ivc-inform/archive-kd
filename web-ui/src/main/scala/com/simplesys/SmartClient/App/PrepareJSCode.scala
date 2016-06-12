package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.App.props.{Editoradmin_UserProps, SettingsEditorProps, User_ComponentMenuProps}
import com.simplesys.SmartClient.Control.{ListGridContextMenu, TreeGridContextMenu}
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Grids.TreeListGridEditor
import com.simplesys.SmartClient.System.{Editoradmin_User, ListGridContextMenu, MenuSS, RibbonGroup, RibbonGroupSS, SCApply, SettingsEditor, TreeGridContextMenu, TreeListGridEditor, User_ComponentMenu, WindowSS, isc}

import scala.scalajs.js.annotation.JSExport

@JSExport
class PrepareJSCode extends StaticJSCode {

    @JSExport
    override def createJS(): Unit = {
        isc.defineClass(RibbonGroupSS.getClass.getSimpleName, RibbonGroup.getClass.getSimpleName)
        isc.defineClass(SettingsEditor.getClass.getSimpleName, WindowSS.getClass.getSimpleName)
        isc.defineClass(ListGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(TreeGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(User_ComponentMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
    }
}

