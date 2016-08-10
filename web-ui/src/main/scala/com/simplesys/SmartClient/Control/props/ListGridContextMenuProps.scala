package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.{ListGridContextMenu, MenuSS}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.System.{Common, simpleSyS, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait IDMenu

object ListGridContextMenuProps {
    def newMenuItemWithForm = MenuSSItem(
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
        })

    def newMenuItem = MenuSSItem(
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
        })

    def copyMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Копировать".opt
            identifier = "copy".opt
            icon = Common.copy_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().foreach(owner.dataSource.addData(_))
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length > 0
            }.toFunc.opt
        })

    def editMenuItem = MenuSSItem(
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
        })

    def deleteMenuItem = MenuSSItem(
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
        })

    def refreshMenuItem = MenuSSItem(
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
        })

    def separetorMenuItem = MenuSSItem(
        new MenuSSItemProps {
            isSeparator = true.opt
        })

    def otherItems = Seq(
        copyMenuItem,
        editMenuItem,
        deleteMenuItem,
        refreshMenuItem
    )

    def saveMenuItem = MenuSSItem(
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
        })

    def cancelMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Отменить изменения".opt
            identifier = "discardAll".opt
            icon = Common.cancel.opt
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
        })

    def otherItems1 = Seq(
        separetorMenuItem,
        saveMenuItem,
        cancelMenuItem
    )
}

class ListGridContextMenuProps extends MenuSSProps {
    type classHandler <: ListGridContextMenu
    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>
            isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

            val items = Seq(ListGridContextMenuProps.newMenuItem) ++ ListGridContextMenuProps.otherItems ++ ListGridContextMenuProps.otherItems1
            thiz.items = IscArray(items: _*)
            thiz.Super("initWidget", args)
    }.toThisFunc.opt
}

class ListGridContextMenuWithFormProps extends MenuSSProps {
    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>
            isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

            val items = Seq(ListGridContextMenuProps.newMenuItemWithForm) ++ ListGridContextMenuProps.otherItems ++ ListGridContextMenuProps.otherItems1
            thiz.items = IscArray(items: _*)
            thiz.Super("initWidget", args)
    }.toThisFunc.opt
}

