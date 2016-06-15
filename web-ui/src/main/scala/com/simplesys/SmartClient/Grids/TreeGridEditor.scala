package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeGridField, TreeNode}
import com.simplesys.SmartClient.Layout.VLayoutSS
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait TreeGridEditor extends VLayoutSS with GridEditor[TreeGridField, TreeNode, TreeGridSelectedState] {
    var folderIcon: SCImgURL
    var dataProperties: Tree
    var nodeIcon: SCImgURL
    var canReparentNodes: JSUndefined[Boolean]
    def startEditingNew(): void
    def startEditing(): void
    var showOpenIcons: Boolean
    var grid: TreeGrid
    def setSelectedRecordsAsFolders(): IscArray[TreeNode]
    def setRecordsAsFolders(records: ListGridRecord): IscArray[TreeNode]
}


