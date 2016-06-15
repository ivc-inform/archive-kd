package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Grids.TreeGridEditor
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridRecordProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Grids.treeGrid.Tree
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class TreeGridEditorProps extends GridEditorProps[TreeGridFieldProps, ListGridRecordProps] {
    type classHandler <: TreeGridEditor

    var dataProperties: ScOption[Tree] = ScNone
    var fields: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var defaultFields: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var folderIcon: ScOption[SCImgURL] = ScNone
    var nodeIcon: ScOption[SCImgURL] = ScNone
    var canReparentNodes: ScOption[Boolean] = ScNone
    var showOpenIcons: ScOption[Boolean] = ScNone
    var data: ScOption[Tree] = ScNone
}
