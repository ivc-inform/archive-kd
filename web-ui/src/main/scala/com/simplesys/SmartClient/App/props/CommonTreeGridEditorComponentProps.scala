package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonTreeGridEditorComponent
import com.simplesys.SmartClient.Control.props.TreeGridContextMenuProps
import com.simplesys.SmartClient.Grids.props.TreeGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait CommonTreeGridEditorComponentProps extends TreeGridEditorProps {

    type classHandler <: CommonTreeGridEditorComponent

    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    selectionType = SelectionStyle.single.opt
    folderIcon = Common.iconFolder.opt
    nodeIcon = Common.iconTreeNode.opt
    showOpenIcons = false.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val funcMenu = TreeGridContextMenu.create(
                new TreeGridContextMenuProps {
                    owner = thiz.opt
                }
            )

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
