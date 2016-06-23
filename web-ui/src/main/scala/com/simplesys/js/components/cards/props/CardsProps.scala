package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.cards.Cards
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class CardsProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: Cards

    dataSource = DataSourcesJS.eakd_card_DS.opt
    identifier = "D8DC1876-F489-3172-EE97-729FFB73B575".opt
}
