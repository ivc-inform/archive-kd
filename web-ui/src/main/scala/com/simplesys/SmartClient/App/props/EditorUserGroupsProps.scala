package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.EditorUserGroups
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.JSAny
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class EditorUserGroupsProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: EditorUserGroups

    identifier = "C4C652D1-7823-F191-BD54-88A8A2238DCC".opt
    dataSource = DataSourcesJS.admin_UserGroup_DS.opt
    fields = ListGridFiledsJS.admin_UserGroup_FLDS.opt
    editingFields = FormItemsJS.admin_UserGroup_FRMITM.opt

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

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
