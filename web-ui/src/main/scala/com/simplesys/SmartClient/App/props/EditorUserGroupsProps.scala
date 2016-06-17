package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.EditorUserGroups
import com.simplesys.SmartClient.DataBinding.props.{DSRequestProps, ResultTreeProps}
import com.simplesys.SmartClient.Forms.FormsItems.props.{CheckboxItemProps, TextAreaItemProps, TextItemProps}
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{ListGridFieldType, TreeModelType}
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

class EditorUserGroupsProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: EditorUserGroups

    dataSource = DataSourcesJS.admin_UserGroup_DS.opt
    identifier = "C4C652D1-7823-F191-BD54-88A8A2238DCC".opt

//    data = ResultTree.create(
//        new ResultTreeProps {
//            idField = "di".opt
//            parentIdField = "parent".opt
//            dataSource = DataSourcesJS.admin_UserGroup_DS.opt
//            modelType = TreeModelType.parent.opt
//            isFolderProperty = "isCampaign".opt
//        }
//    ).opt

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

    editingFields = Seq(
        new CheckboxItemProps {
            name = "active".opt
            width = "100%"
        },
        new TextItemProps {
            name = "codeGroup".opt
        },
        new TextItemProps {
            name = "captionGroup".opt
        },
        new TextAreaItemProps {
            name = "descriptionGroup".opt
        }
    ).opt

    fields = Seq(
        new TreeGridFieldProps {
            name = "codeGroup".opt
        },
        new TreeGridFieldProps {
            name = "captionGroup".opt
        },
        new TreeGridFieldProps {
            name = "descriptionGroup".opt
        },
        new TreeGridFieldProps {
            name = "di".opt
            hidden = true.opt
        },
        new TreeGridFieldProps {
            name = "active".opt
            `type` = ListGridFieldType.boolean.opt
        }
    ).opt
}
