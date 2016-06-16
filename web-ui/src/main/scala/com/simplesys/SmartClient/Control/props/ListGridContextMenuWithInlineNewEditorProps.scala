package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.System.{Common, simpleSyS, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

object ListGridContextMenuWithInlineNewEditorProps {

    val items1 = Seq(
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
    )

    val items2 = Seq(
        new MenuSSItemProps {
            title = "Новый".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconAdd.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditingNewInForm()
            }.toFunc.opt
        }
    )

    val items3 = Seq(
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
                    owner.getSelectedRecords().length == 1
            }.toFunc.opt
        },
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
        },
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
        },
        new MenuSSItemProps {
            isSeparator = true.opt
        },
        new MenuSSItemProps {
            title = "Сохранить изменения".opt
            identifier = "saveAll".opt
            icon = Common.iconSave.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.saveAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
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
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.discardAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges()
            }.toFunc.opt
        }
    )
}

class ListGridContextMenuWithInlineNewEditorProps extends MenuSSProps {
    items = (ListGridContextMenuWithInlineNewEditorProps.items1 ++ ListGridContextMenuWithInlineNewEditorProps.items3).opt
}

class ListGridContextMenuWithFormNewEditorProps extends MenuSSProps {
    items = (ListGridContextMenuWithInlineNewEditorProps.items2 ++ ListGridContextMenuWithInlineNewEditorProps.items3).opt
}
