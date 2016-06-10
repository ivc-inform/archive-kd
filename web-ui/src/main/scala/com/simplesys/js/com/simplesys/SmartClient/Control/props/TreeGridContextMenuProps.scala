package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.TreeGridEditor
import com.simplesys.SmartClient.System.{Common, simpleSyS, _}
import com.simplesys.System.Types.Visibility
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

class TreeGridContextMenuProps extends MenuSSProps {
    items = Seq(
        new MenuSSItemProps {
            title = "Новый корневой узел".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconNew.opt
            visibility = Visibility.hidden.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.deselectAllRecords()
                    owner.startEditingNew()
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Новый".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconNew.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditingNew()
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Изменить".ellipsis.opt
            identifier = "edit".opt
            icon = Common.iconEdit.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditing()
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Разрешить перемещение групп".ellipsis.opt
            identifier = "enableReparent".opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    val x = !owner.canReparentNodes
                    owner.canReparentNodes = x
                    owner.canAcceptDroppedRecords = x
                    item.checkIf = {
                        (target: Canvas, menu: MenuSS, item: MenuSSItem) => x
                    }
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Удалить".opt
            identifier = "remove".opt
            icon = Common.delete_Column.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    isc.ask(simpleSyS.config.confirmDeleting, {
                        (value: Boolean) => if (value) owner.removeSelectedData()
                    })

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    simpleSyS _enableDeleteFromTree item
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Обновить".opt
            identifier = "refresh".opt
            icon = Common.iconRefresh.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.fullRefresh()
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Развернуть узел".opt
            identifier = "openFolder".opt
            icon = Common.iconOpenFolder.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    simpleSyS _openFolders menu
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length > 0
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Сохранить изменения".opt
            identifier = "saveAll".opt
            icon = Common.iconSave.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.saveAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges() && !owner.hasErrors()
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Отменить изменения".opt
            identifier = "discardAll".opt
            icon = Common.delete_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.discardAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges()
            }.toFunc.opt
        }
    ).opt
}
