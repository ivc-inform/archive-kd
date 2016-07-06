package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.TreeListGridContextMenu
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Grids.TreeListGridEditor
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class TreeListGridContextMenuProps extends MenuSSProps {
    type classHandler <: TreeListGridContextMenu

    var simpleTableTree: ScOption[Boolean] = false.opt
    var simpleTableList: ScOption[Boolean] = true.opt

    initWidget = {
        (thiz: TreeListGridContextMenu, arguments: IscArray[JSAny]) =>
            isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

            thiz.Super("initWidget", arguments)

            val topOwner = thiz.owner.asInstanceOf[TreeListGridEditor]

            val listGridEditorMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = topOwner.listGrid.opt
                }
            )

            val listGridEditorMenuWithForm = ListGridContextMenuWithForm.create(
                new ListGridContextMenuWithFormProps {
                    owner = topOwner.listGrid.opt
                }
            )

            topOwner setContextMenuListGridEditor listGridEditorMenu
            topOwner setContextMenuListGridEditor listGridEditorMenuWithForm

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
                            submenu = (if (thiz.simpleTableList) listGridEditorMenu else listGridEditorMenuWithForm).opt
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
