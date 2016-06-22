package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.GroupItem
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class GroupItemProps extends CommonListGridEditorComponentProps {
    override val simpleTable = true

    type classHandler <: GroupItem

    dataSource = DataSourcesJS.eakd_mtype_DS.opt
    identifier = "D5151B68-E433-F7D0-BFA9-725DDECDAEDD".opt
}
