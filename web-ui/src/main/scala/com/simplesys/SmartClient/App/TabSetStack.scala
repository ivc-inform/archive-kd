package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout._
import com.simplesys.SmartClient.Layout.props.TabSetSSProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{Tab, TabSetSS, isc}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.ID
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait TabSetsStack {
    self: TabSetStack =>

    def checkInnerTabSet(groupIdentifier: ID, canvas: Canvas, menuItem: MenuSSItem): TabSetSS = {

        val tabSet = tabGroupSet.tabs.map(_.pane.asInstanceOf[TabSetSS]).find(_.identifier == groupIdentifier) match {
            case None =>
                TabSetSS.create(
                    new TabSetSSProps {
                        identifier = groupIdentifier.opt
                        afterRemoveTabs = {
                            (thiz: classHandler) =>
                                val res: Int = tabGroupSet.tabs.foldLeft(0)((qty: Int, tab: Tab) => qty + tab.pane.asInstanceOf[TabSetSS].tabs.length)
                                if (res == 0)
                                    functionGroup.hide()

                        }.toThisFunc.opt
                    }
                )
            case Some(tabSet) => tabSet
        }

        val tab = tabSet.findTab(canvas.getIdentifier())
        //isc debugTrap tab

        if (tab.isDefined) {
            tabSet selectTab tab.get
            functionGroup.show()
        } else {
            val len = tabSet.tabs.length

            val tab = Tab(
                new TabProps {
                    pane = canvas.opt
                    tabSelected = {
                        (tabSet: TabSet, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                            functionButton.menu = tabPane.funcMenu
                    }.toFunc.opt
                    name = canvas.getIdentifier().opt
                    title = s"${CanvasStatic.imgHTML(menuItem.icon, 16, 14)} ${menuItem.title}".opt
                }
            )

            tabSet.addTab(tab, len + 1)

            tabSet selectTab len
            functionGroup.show()
        }

        //isc debugTrap tabSet
        tabSet
    }

    def checkInnerTabSet(groupIdentifier: ID, canvas: Canvas, button: IconButtonSS): TabSetSS = {

        val tabSet = tabGroupSet.tabs.map(_.pane.asInstanceOf[TabSetSS]).find(_.identifier == groupIdentifier) match {
            case None =>
                TabSetSS.create(
                    new TabSetSSProps {
                        identifier = groupIdentifier.opt
                        afterRemoveTabs = {
                            (thiz: classHandler) =>
                                val res: Int = tabGroupSet.tabs.foldLeft(0)((qty: Int, tab: Tab) => qty + tab.pane.asInstanceOf[TabSetSS].tabs.length)
                                if (res == 0)
                                    functionGroup.hide()

                        }.toThisFunc.opt
                    }
                )
            case Some(tabSet) => tabSet
        }

        val tab = tabSet.findTab(canvas.getIdentifier())
        //isc debugTrap tab

        if (tab.isDefined) {
            tabSet selectTab tab.get
            functionGroup.show()
        } else {
            val len = tabSet.tabs.length

            val tab = Tab(
                new TabProps {
                    pane = canvas.opt
                    tabSelected = {
                        (tabSet: TabSet, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                            functionButton.menu = tabPane.funcMenu
                    }.toFunc.opt
                    name = canvas.getIdentifier().opt
                    title = s"${CanvasStatic.imgHTML(button.icon, 16, 14)} ${button.title}".opt
                }
            )

            tabSet.addTab(tab, len + 1)

            tabSet selectTab len
            functionGroup.show()
        }

        //isc debugTrap tabSet
        tabSet
    }
}

trait TabSetStack extends TabSetsStack {
    self =>

    protected val functionGroup: RibbonGroupSS
    protected val functionButton: IconMenuButtonSS

    protected lazy val tabGroupSet = TabSetSS.create(
        new TabSetSSProps {
            defaultTabHeight = 20.opt
            afterRemoveTabs = {
                (thiz: classHandler) =>
                    if (thiz.tabs.length == 0)
                        functionGroup.hide()

            }.toThisFunc.opt
        }
    )

    def addTab(canvas: Canvas, menuItem: MenuSSItem): Unit = {
        if (canvas.identifier.isEmpty)
            isc.error(s"Компонент ${canvas.getIdentifier()} не имеет постоянного identifier, поэтому не может быть добавлен.")
        else if (menuItem.owner1.map(_.asInstanceOf[IconMenuButtonSS]).isEmpty)
            isc.error(s"Нет привязки к owner1: IconMenuButtonSS")
        else {
            val groupButton = menuItem.owner1.map(_.asInstanceOf[IconMenuButtonSS]).get
            if (groupButton.identifier.isEmpty)
                isc.error(s"Компонент ${groupButton.getIdentifier()} не имеет постоянного identifier.")
            else {
                val tabGroup = tabGroupSet.findTab(groupButton.getIdentifier())
                if (tabGroup.isDefined) {
                    tabGroupSet selectTab tabGroup.get
                    //isc debugTrap tabGroup
                    checkInnerTabSet(groupButton.getIdentifier(), canvas, menuItem)
                }
                else {
                    val len = tabGroupSet.tabs.length
                    val _title = if (groupButton.iconSmall.isDefined) s"${CanvasStatic.imgHTML(groupButton.iconSmall.get, 16, 14)} ${groupButton.title1}" else groupButton.title

                    val tab = Tab(
                        new TabProps {
                            pane = checkInnerTabSet(groupButton.getIdentifier(), canvas, menuItem).opt
                            name = groupButton.getIdentifier().opt
                            title = _title.opt
                        }
                    )

                    tabGroupSet.addTab(tab, len + 1)

                    tabGroupSet selectTab len
                }
            }
        }
    }

    def addTab(canvas: Canvas, button: IconButtonSS): Unit = {
        if (canvas.identifier.isEmpty)
            isc.error(s"Компонент ${canvas.getIdentifier()} не имеет постоянного identifier, поэтому не может быть добавлен.")
        else {
            if (button.identifier.isEmpty)
                isc.error(s"Компонент ${button.getIdentifier()} не имеет постоянного identifier.")
            else {
                val tabGroup = tabGroupSet.findTab(button.getIdentifier())
                if (tabGroup.isDefined) {
                    tabGroupSet selectTab tabGroup.get
                    //isc debugTrap tabGroup
                    checkInnerTabSet(button.getIdentifier(), canvas, button)
                }
                else {
                    val len = tabGroupSet.tabs.length
                    val _title = if (button.iconSmall.isDefined) s"${CanvasStatic.imgHTML(button.iconSmall.get, 16, 14)} ${button.title1}" else button.title

                    val tab = Tab(
                        new TabProps {
                            pane = checkInnerTabSet(button.getIdentifier(), canvas, button).opt
                            name = button.getIdentifier().opt
                            title = _title.opt
                        }
                    )

                    tabGroupSet.addTab(tab, len + 1)

                    tabGroupSet selectTab len
                }
            }
        }
    }
}
