package com.simplesys.js.com.simplesys.SmartClient.App

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Grids.TreeListGridEditor

import scala.scalajs.js

@js.native
trait Editoradmin_User extends TreeListGridEditor {
    val treeDataSource: DataSource
    val listDataSource: DataSource
}

//@js.native
//abstract trait AbstractEditoradmin_UserCompanion extends AbstractTreeListGridEditorCompanion {
//}

