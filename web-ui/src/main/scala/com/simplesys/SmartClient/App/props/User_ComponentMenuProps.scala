package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.User_ComponentMenu
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, MenuSSProps, TreeGridContextMenuProps}
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

            thiz.addItems(
                IscArray(
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = topOwner.contextMenuListGridEditor.opt
                            title = "Группы пользователей".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    ),
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = topOwner.contextMenuTreeGridEditor.opt
                            title = "Пользователи".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    )
                )
            )

    }.toThisFunc.opt
}
