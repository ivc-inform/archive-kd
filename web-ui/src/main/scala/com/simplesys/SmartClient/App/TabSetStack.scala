package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{IconMenuButtonSS, RibbonGroupSS, TabSet, TabSetSS}
import com.simplesys.SmartClient.System.{Tab, isc}
import com.simplesys.System.Types.ID
import com.simplesys.System.{JSUndefined, jSUndefined}
import com.simplesys.function._
import com.simplesys.option.ScOption._

trait TabSetStack {
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

                canvas.getFuncMenu.foreach {
                    getFuncMenu =>
                        _funcMenu = getFuncMenu()
                }

                if (_funcMenu.isDefined) {
                    tabSet.addTab(
                        Tab(
                            new TabProps {
                                pane = canvas.opt
                                canClose = true.opt
                                tabSelected = {
                                    (tabSet: TabSet, tabNum: Int, tabPane: Canvas, ID: ID, tab: Tab, name: String) =>
                                        functionButton.menu = _funcMenu
                                }.toFunc.opt
                                funcMenu = _funcMenu.get.opt
                                name = canvas.getIdentifier().opt
                                title = s"isc.Canvas.imgHTML(${menuItem.icon}, 16, 14)${menuItem.title}".opt
                            }
                        ),
                        len + 1
                    )
                } else {
                    tabSet.addTab(
                        Tab(
                            new TabProps {
                                pane = canvas.opt
                                canClose = true.opt
                                name = canvas.getIdentifier().opt
                                title = s"isc.Canvas.imgHTML(${menuItem.icon}, 16, 14)${menuItem.title}".opt
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
