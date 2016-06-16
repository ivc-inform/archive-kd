package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{IconMenuButtonSS, RibbonGroupSS, TabSet, TabSetSS}
import com.simplesys.SmartClient.System.{Tab, isc}
import com.simplesys.System.Types.ID
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait TabSetStack {
    self =>

    protected val tabSet: TabSetSS
    protected val functionGroup: RibbonGroupSS
    protected val functionButton: IconMenuButtonSS

    def addTab(canvas: Canvas, menuItem: MenuSSItem): Unit = {
        if (canvas.identifier.isEmpty)
            isc.error(s"Компонент ${canvas.getIdentifier()} не имеет постоянного identifier, поэтому не может быть добавлен.")
        else {
            val tab = tabSet.findTab(canvas.getIdentifier())
            if (tab.isDefined) {
                tabSet selectTab tab.get
                functionGroup.show()
            } else {
                val len = tabSet.tabs.length

                val _title = s"${CanvasStatic.imgHTML(menuItem.icon, 16, 14)} ${menuItem.title}"

                isc debugTrap canvas.funcMenu

                tabSet.addTab(
                    Tab(
                        new TabProps {
                            pane = canvas.opt
                            tabSelected = {
                                (tabSet: TabSet, tabNum: Int, tabPane: Canvas, ID: ID, tab: Tab, name: String) =>
                                    //functionButton.menu = tabSet.funcMenu
                            }.toFunc.opt
                            //funcMenu = canvas.funcMenu.opt
                            name = canvas.getIdentifier().opt
                            title = _title.opt
                        }
                    ),
                    len + 1
                )

                tabSet selectTab len
                functionGroup.show()
            }
        }
    }
}
