package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.{DocIzvType, DocTypes}
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class DocIzvTypeProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: DocIzvType

    dataSource = DataSourcesJS.eakd_docizvtype_DS.opt
    identifier = "D5DE1B68-E433-F220-BFA9-7BFDDECDAEDD".opt
}
