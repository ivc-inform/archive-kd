package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{IconMenuButtonSS, RibbonGroupSS, TabSet, TabSetSS}
import com.simplesys.SmartClient.System.{Tab, isc}
import com.simplesys.System.Types.ID
import com.simplesys.System.{JSUndefined, jSUndefined, _}
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.SmartClient.System._

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
                var _funcMenu: JSUndefined[MenuSS] = jSUndefined

                isc debugTrap (canvas.getFuncMenu, canvas.funcMenu)

                canvas.getFuncMenu.foreach {
                    getFuncMenu =>
                        _funcMenu = getFuncMenu()
                }

                isc debugTrap _funcMenu

                val _title = s"${CanvasStatic.imgHTML(menuItem.icon, 16, 14)} ${menuItem.title}"
                //val _title = menuItem.title

                if (_funcMenu.isDefined) {
                    tabSet.addTab(
                        Tab(
                            new TabProps {
                                pane = canvas.opt
                                tabSelected = {
                                    (tabSet: TabSet, tabNum: Int, tabPane: Canvas, ID: ID, tab: Tab, name: String) =>
                                        functionButton.menu = _funcMenu
                                }.toFunc.opt
                                funcMenu = _funcMenu.get.opt
                                name = canvas.getIdentifier().opt
                                title = _title.opt
                            }
                        ),
                        len + 1
                    )

                    functionButton.menu = _funcMenu
                } else {
                    tabSet.addTab(
                        Tab(
                            new TabProps {
                                pane = canvas.opt
                                name = canvas.getIdentifier().opt
                                title = _title.opt
                            }
                        ),
                        len + 1
                    )
                }

                tabSet selectTab len
                functionGroup.show()
            }
        }
    }
}
