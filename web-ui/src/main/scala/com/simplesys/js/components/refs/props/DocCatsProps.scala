package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.DocCats
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class DocCatsProps extends CommonListGridEditorComponentProps {
    simpleTable = true.opt

    type classHandler <: DocCats

    dataSource = DataSourcesJS.eakd_doccats_DS.opt
    identifier = "5D64B1C6-342F-4623-6E73-DD11B89ACFD7".opt
}
