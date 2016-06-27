package com.simplesys.js.components.misc.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.cards.Zapros
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class EmulateOutMessageProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: Zapros

    dataSource = DataSourcesJS.test_emulate_outmessage_DS.opt
    identifier = "0B8822B1-844D-33E2-57AD-6D763F2AECCF".opt
}
