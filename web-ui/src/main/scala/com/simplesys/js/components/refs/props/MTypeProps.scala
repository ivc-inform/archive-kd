package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.MType
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class MTypeProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: MType

    dataSource = DataSourcesJS.eakd_mtype_DS.opt
    identifier = "D5DE1B68-1553-5220-BFA9-7BFDDECDAEDD".opt
}
