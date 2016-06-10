package com.simplesys.js.com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.System.{Common, MenuSSItem, simpleSyS, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class ListGridContextMenuProps extends MenuSSProps {
    items = Seq(
        MenuSSItem(
            new MenuSSItemProps {
                title = "Новый".ellipsis.opt
                identifier = "new".opt
                icon = Common.iconAdd.opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.startEditingNew()
                }.toFunc.opt
            }
        ),
        MenuSSItem(
            new MenuSSItemProps {
                title = "Изменить".opt
                identifier = "edit".opt
                icon = Common.Actions_document_edit_icon.opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.startEditing()
                        false
                }.toFunc.opt
                enableIf = {
                    (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.getSelectedRecords().length == 0
                }.toFunc.opt
            }
        ),
        MenuSSItem(
            new MenuSSItemProps {
                title = "Удалить".opt
                identifier = "remove".opt
                icon = Common.delete_icon.opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        isc.ask(simpleSyS.config.confirmDeleting, {
                            (value: Boolean) =>
                                if (value) owner.removeSelectedData()
                        })

                        false
                }.toFunc.opt
                enableIf = {
                    (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.getSelectedRecords().length > 0
                }.toFunc.opt
            }
        ),
        MenuSSItem(
            new MenuSSItemProps {
                title = "Обновить".opt
                identifier = "refresh".opt
                icon = "Refresh.png".opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.fullRefresh()
                }.toFunc.opt
            }
        ),
        MenuSSItem(
            new MenuSSItemProps {
                isSeparator = true.opt
            }
        ),
        MenuSSItem(
            new MenuSSItemProps {
                title = "Отменить изменения".opt
                identifier = "discardAll".opt
                icon = Common.delete_icon.opt
                click = {
                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.discardAllEdits()

                        false
                }.toFunc.opt
                enableIf = {
                    (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                        val owner = item.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.hasChanges() && !owner.hasErrors()
                }.toFunc.opt
            }
        )
    ).opt
}
