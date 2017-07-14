package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.refs.ArxUser
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class ArxUserProps extends CommonListGridEditorComponentProps {
    type classHandler <: ArxUser

    identifier = "15EC1A89-2233-128F-1186-372AF0FD1DC2".opt
    simpleTable = true.opt

    dataSource = DataSourcesJS.arx_User_DS.opt

    newRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt
}
