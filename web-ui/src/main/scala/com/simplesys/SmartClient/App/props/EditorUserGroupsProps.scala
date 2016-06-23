package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.EditorUserGroups
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Forms.FormsItems.props.{CheckboxItemProps, TextAreaItemProps, TextItemProps}
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.ListGridFieldType
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

class EditorUserGroupsProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: EditorUserGroups

    identifier = "C4C652D1-7823-F191-BD54-88A8A2238DCC".opt

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
        CheckboxItem(new CheckboxItemProps {
            name = "active".opt
        }),
        TextItem(new TextItemProps {
            name = "codeGroup".opt
        }),
        TextItem(new TextItemProps {
            name = "captionGroup".opt
        }),
        TextAreaItem(new TextAreaItemProps {
            name = "descriptionGroup".opt
        })
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

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
