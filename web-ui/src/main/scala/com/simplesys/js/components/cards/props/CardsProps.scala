package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class CardsProps extends CommonListGridEditorComponentProps with Implicits {
    simpleTable = false.opt

    type classHandler <: Cards

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 290
            height = 645
        }
    ).opt

    dataSource = DataSourcesJS.eakd_card_DS.opt
    identifier = "D8DC1876-F489-3172-EE97-729FFB73B575".opt

    editingFields = FormItemsJS.eakd_card_FRMITM.opt
    fields = ListGridFiledsJS.eakd_card_FLDS.opt
}
