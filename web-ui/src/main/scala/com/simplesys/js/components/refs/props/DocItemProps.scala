package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.{DocItem, DocTypes}
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class DocItemProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: DocItem

    dataSource = DataSourcesJS.eakd_docitem_DS.opt
    identifier = "CD8E2C1D-60E0-3338-69DE-3E72850BC64B".opt
}
