package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.DoubleType._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait NewDSRequestData extends JSObject {
    val active: Boolean
}


class EditorUsersProps extends CommonTreeListGridEditorComponentProps {
    identifier = "58125E1C-252A-01C4-11A1-557FA3222D3F".opt

    newTreeRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt

    editWindowPropertiesTree = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt

    dataSourceList = DataSourcesJS.admin_User_DS.opt
    dataSourceTree = DataSourcesJS.admin_UserGroup_DS.opt

    fieldsTree = ListGridFiledsJS.admin_UserGroup_FLDS.opt
    editingTreeFields = FormItemsJS.admin_UserGroup_FRMITM.opt

    fieldsList = ListGridFiledsJS.admin_User_FLDS.opt
}
