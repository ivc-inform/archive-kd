package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout.props.TabSetSSProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{IconMenuButtonSS, RibbonGroupSS, TabSet}
import com.simplesys.SmartClient.System.{Tab, TabSetSS, isc}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.ID
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait TabSetStack {
    self =>

    protected val functionGroup: RibbonGroupSS
    protected val functionButton: IconMenuButtonSS

//    protected lazy val tabGroupSet = TabSetSS.create(
//        new TabSetSSProps {
//            afterRemoveTabs = {
//                (thiz: classHandler) =>
//                    if (thiz.tabs.length == 0)
//                        functionGroup.hide()
//
//            }.toThisFunc.opt
//        }
//    )

    protected lazy val tabSet = TabSetSS.create(
        new TabSetSSProps {
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
        else {
            //isc debugTrap (canvas, menuItem)
            val tab = tabSet.findTab(canvas.getIdentifier())
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
        }
    }
}
