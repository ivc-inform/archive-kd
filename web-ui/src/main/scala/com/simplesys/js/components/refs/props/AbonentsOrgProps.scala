package com.simplesys.js.components.refs.props

import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.AbonentsOrg
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsOrgProps extends CommonListGridEditorComponentProps {
    type classHandler <: AbonentsOrg

    dataSource = DataSourcesJS.eakd_abonents_org_DS.opt
    identifier = "A8512DFA-C397-201F-6205-690102F6E1DB".opt

}
