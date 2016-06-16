package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.User_ComponentMenu
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuWithInlineNewEditorProps$, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Grids.TreeListGridEditor
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class User_ComponentMenuProps extends MenuSSProps {
    type classHandler <: User_ComponentMenu

    initWidget = {
        (thiz: User_ComponentMenu, arguments: IscArray[JSAny]) =>
            thiz.Super("initWidget", arguments)

            val topOwner = thiz.owner.asInstanceOf[TreeListGridEditor]

            val listGridEditorMenu = ListGridContextMenuWithInlineNewEditor.create(
                new ListGridContextMenuWithInlineNewEditorProps {
                    owner = topOwner.listGrid.opt
                }
            )

            topOwner setContextMenuListGridEditor listGridEditorMenu

            val treeGridEditorMenu = TreeGridContextMenu.create(
                new TreeGridContextMenuProps {
                    owner = topOwner.treeGrid.opt
                }
            )

            topOwner setContextMenuTreeGridEditor treeGridEditorMenu

            thiz.addItems(
                IscArray(
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = listGridEditorMenu.opt
                            title = "Пользователи".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    ),
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = treeGridEditorMenu.opt
                            title = "Группы пользователей".ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    )
                )
            )

    }.toThisFunc.opt
}
