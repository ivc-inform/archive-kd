package com.simplesys.js.components.props

import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.CommonListGridEditorComponent
import com.simplesys.option.ScOption._

class CommonListGridEditorComponentProps extends ListGridEditorProps {
    type classHandler <: CommonListGridEditorComponent

    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    selectionType = SelectionStyle.multiple.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val funcMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thiz.opt
                }
            )

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
