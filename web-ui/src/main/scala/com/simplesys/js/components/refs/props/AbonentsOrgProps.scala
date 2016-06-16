package com.simplesys.js.components.refs.props

import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps
import com.simplesys.SmartClient.System.{IscArray, ListGridContextMenu}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.props.CommonListGridEditorComponentProps
import com.simplesys.js.components.refs.AbonentsOrg
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class AbonentsOrgProps extends CommonListGridEditorComponentProps {
    type classHandler <: AbonentsOrg

    dataSource = DataSourcesJS.eakd_abonents_org_DS.opt
    identifier = "A8512DFA-C397-201F-6205-690102F6E1DB".opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            val funcMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thiz.opt
                }
            )

            thiz setFuncMenu funcMenu
    }.toThisFunc.opt
}
