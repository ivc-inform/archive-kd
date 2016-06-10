package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.User_ComponentMenu
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, MenuSSProps}
import com.simplesys.SmartClient.Grids.TreeListGridEditor
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class User_ComponentMenuProps extends MenuSSProps {
    type classHandler <: User_ComponentMenu

    init = {
        (thiz: User_ComponentMenu, arguments: IscArray[JSAny]) =>
            val topOwner = thiz.owner.asInstanceOf[TreeListGridEditor]

            topOwner.setContextMenuListGridEditor(
                ListGridContextMenu.create(
                    new ListGridContextMenuProps {
                        owner = topOwner.listGrid.opt
                    }
                )
            )

            topOwner.setContextMenuTreeGridEditor(
                TreeGridContextMenu.create(
                    new TreeGridContextMenuProps {
                        owner = topOwner.treeGrid.opt
                    }
                )
            )

            thiz.setItems(
                IscArray(
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = topOwner.contextMenuListGridEditor.opt
                            title = "\u0413\u0440\u0443\u043F\u043F\u044B \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0435\u0439".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    ),
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = topOwner.contextMenuTreeGridEditor.opt
                            title = "\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0438".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    )
                )
            )

    }.toThisFunc.opt
}
