package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.cards.Zapros
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class ZaprosProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

        type classHandler <: Zapros

        dataSource = DataSourcesJS.eakd_zapros_DS.opt
        identifier = "0B8857B1-844D-33E2-57AD-6D763F2AECCF".opt
}
