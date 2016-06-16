package com.simplesys.js.components.refs.props

import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsOrgProps extends CommonListGridEditorComponentProps {
    dataSource = DataSourcesJS.eakd_abonents_org_DS.opt
}
