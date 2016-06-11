package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.{ID, void}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

@js.native
trait TabSetSS extends TabSet {
    @JSName("tabsReordered")
    var tabsReorderedSS: js.ThisFunction0[TabSetSS, _]
    var beforeRemoveTabs : js.Function1[Tab, _]
    var afterRemoveTabs : js.Function1[Tab, _]
    def filter4Visiblity(tabs: IscArray[Tab]): IscArray[Tab]
    def addTabs(tabs: IscArray[Tab], position: Int = js.native): void
    def addTab(tab: Tab, position: Int = js.native): void
    def removeTab(tab: Tab | Int | ID, position: Int = js.native): void
    def removeAllTabs(): void
    def getLength(): Int
    def isEmpty(): Boolean
    def isNotEmpty(): Boolean
    def findTab(): JSUndefined[Tab]
}


