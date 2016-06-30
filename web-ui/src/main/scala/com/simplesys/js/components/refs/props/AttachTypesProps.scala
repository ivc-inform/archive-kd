package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.AttachTypes
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AttachTypesProps extends CommonListGridEditorComponentProps {
    simpleTable = true.opt

    type classHandler <: AttachTypes

    dataSource = DataSourcesJS.eakd_attachtypes_DS.opt
    identifier = "763B59FC-CD39-2550-8EBF-CDEBE6760347".opt
}
