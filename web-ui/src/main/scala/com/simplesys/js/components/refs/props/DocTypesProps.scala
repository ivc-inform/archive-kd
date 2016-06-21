package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.DocTypes
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class DocTypesProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: DocTypes

    dataSource = DataSourcesJS.eakd_doctypes_DS.opt
    identifier = "D5DE1B68-E433-F7D0-BFA9-7BFDDECDAEDD".opt
}
