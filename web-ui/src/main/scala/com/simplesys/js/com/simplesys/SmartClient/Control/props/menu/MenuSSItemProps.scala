package com.simplesys.SmartClient.Control.props.menu

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridRecordProps
import com.simplesys.SmartClient.System.{IscArray, KeyIdentifier}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class MenuSSItemProps extends ListGridRecordProps {
    var identifier: ScOption[ID] = ScNone
    var owner: ScOption[Canvas] = ScNone
    var item: ScOption[Canvas] = ScNone
    var action: ScOption[js.Function1[void, _]] = ScNone
    var canSelectParent: ScOption[Boolean] = ScNone
    var checked: ScOption[Boolean] = ScNone
    var checkIf: ScOption[js.Function3[Canvas, MenuSS, MenuSSItem, Boolean]] = ScNone
    var click: ScOption[js.Function4[Canvas, MenuSSItem, MenuSS, JSUndefined[Int], _]] = ScNone
    var disabledIcon: ScOption[String] = ScNone
    var dynamicIcon: ScOption[js.Function3[Canvas, MenuSS, MenuSSItem, SCImgURL]] = ScNone
    var dynamicTitle: ScOption[js.Function3[Canvas, MenuSS, MenuSSItem, String]] = ScNone
    var enableIf: ScOption[js.Function3[Canvas, MenuSS, MenuSSItem, Boolean]] = ScNone
    var visibilityIf: ScOption[js.Function0[Boolean]] = ScNone
    var fetchSubmenus: ScOption[Boolean] = ScNone
    var icon: ScOption[String] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var keys: ScOption[IscArray[KeyIdentifier]] = ScNone
    var keyTitle: ScOption[String] = ScNone
    var submenu: ScOption[MenuSS] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
