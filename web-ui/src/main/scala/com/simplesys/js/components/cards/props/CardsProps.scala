package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.JSUndefined
import com.simplesys.app.Attachments
import com.simplesys.function._
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class CardsProps extends CommonListGridEditorComponentProps with Implicits {
    simpleTable = false.opt
    //selectionType = SelectionStyle.multiple.opt

    type classHandler <: Cards

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 290
            height = 645
        }
    ).opt

    dataSource = DataSourcesJS.arx_card_DS.opt
    identifier = "D8DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.arx_card_FRMITM.opt
    fields = ListGridFiledsJS.arx_card_FLDS.opt
    itemsType = Seq(miNew(false), miCopy(false), miDelete(false), miEdit(false), miRefresh()).opt

    canExpandRecords = true.opt
    expandRecord = {
        (thiz: classHandler, component: JSUndefined[Canvas], record: ListGridRecord) ⇒
            MenuSS.create(
                new MenuSSProps {
                    items = Seq(
                        new MenuSSItemProps {
                            title = "Вложения".ellipsis.opt
                            identifier = "attachments".opt
                            icon = Common.attach.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                    val component = Attachments.create(
                                        new AttachmentsProps {

                                        }
                                    )
                                    thiz.Super("expandRecord", IscArray(component, record))
                                    false
                            }.toFunc.opt
                            enableIf = {
                                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                    true
                            }.toFunc.opt
                        },
                        new MenuSSItemProps {
                            title = "Рассылка".ellipsis.opt
                            identifier = "distribution".opt
                            icon = Common.distribution.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                    false
                            }.toFunc.opt
                            enableIf = {
                                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                    false
                            }.toFunc.opt
                        },
                        new MenuSSItemProps {
                            title = "Запросы".ellipsis.opt
                            identifier = "requests".opt
                            icon = Common.request.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                    false
                            }.toFunc.opt
                            enableIf = {
                                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                    false
                            }.toFunc.opt
                        }
                    ).opt
                }
            ).showContextMenu()
    }.toThisFunc.opt
}
