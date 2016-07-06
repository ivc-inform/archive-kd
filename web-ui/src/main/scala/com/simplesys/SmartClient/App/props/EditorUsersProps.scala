package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{FormItemComponentType, SelectionAppearance, SelectionStyle}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

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

    val userGroupEditor = EditorUserGroups.create(new EditorUserGroupsProps)

    val userGroupFilterEditor = EditorUserGroups.create(
        new EditorUserGroupsProps {
            selectionAppearance = SelectionAppearance.checkbox.opt
            selectionType = SelectionStyle.multiple.opt
            identifier = "0CC55A45-93E8-E019-5AC7-7C154EB602E2".opt
        }
    )

    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = admin_User_codeGroup_NameStrong.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = userGroupEditor.opt
                }).opt
        }).opt
}
