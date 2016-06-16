package com.simplesys.js.components.refs.props

import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.Abonents
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsProps extends CommonListGridEditorComponentProps {
    type classHandler <: Abonents

      dataSource = DataSourcesJS.eakd_abonents_DS.opt
      identifier = "60675A00-9949-20BE-D7B6-598C1F0D1439".opt
}
