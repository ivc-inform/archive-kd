package com.simplesys.js.components.cards.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.cards.{DocIzv, Zapros}
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

import scala.scalajs.js._

class DocIzvProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

        type classHandler <: DocIzv

        dataSource = DataSourcesJS.eakd_docizv_DS.opt
        identifier = "0B8857B1-844D-33E2-57AD-55563F2AECCF".opt
}
