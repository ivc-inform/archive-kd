package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.EditorUserGroups
import com.simplesys.SmartClient.Grids.props.TreeGridEditorProps
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class EditorUserGroupsProps extends TreeGridEditorProps {
    type classHandler <: EditorUserGroups

    dataSource = DataSourcesJS.admin_UserGroup_DS.opt
    identifier = "C4C652D1-7823-F191-BD54-88A8A2238DCC".opt
}
