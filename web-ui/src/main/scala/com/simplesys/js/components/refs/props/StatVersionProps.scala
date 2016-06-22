package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.refs.Status
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

import scala.scalajs.js._

class StatVersionProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

        type classHandler <: Status

        dataSource = DataSourcesJS.eakd_statversion_DS.opt
        identifier = "123E1B68-1553-5220-BFA9-012DD589AEDD".opt
}
