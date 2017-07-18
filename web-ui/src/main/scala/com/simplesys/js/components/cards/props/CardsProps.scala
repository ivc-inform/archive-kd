package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.function._
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
            val menu = MenuSS.create(
                new MenuSSProps {
                    
                }
            )
            thiz.Super("expandRecord", IscArray(component, record))
    }.toThisFunc.opt

    getExpansionComponent = {
        (record: ListGridRecord) ⇒
            Canvas.create(
                new CanvasProps {
                    height = 200
                }
            )
    }.toFunc.opt
}
