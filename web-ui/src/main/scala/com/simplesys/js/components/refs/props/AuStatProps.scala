package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.{AttachTypes, AuStat}
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AuStatProps extends CommonListGridEditorComponentProps {
    simpleTable = true.opt

    type classHandler <:  AuStat

    dataSource = DataSourcesJS.eakd_austat_DS.opt
    identifier = "C5F45CC5-D60B-78BE-998C-43BB45AFF69E".opt
}
