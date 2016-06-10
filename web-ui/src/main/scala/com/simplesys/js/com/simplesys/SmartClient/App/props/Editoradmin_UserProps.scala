package com.simplesys.js.com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.option.ScOption._

class Editoradmin_UserProps extends TreeListGridEditorProps {
    folderDropImageTree = Common.iconFolder.opt
    autoFetchData = true.opt
    fieldsTree = Seq(
        TreeGridField(
            new TreeGridFieldProps {
                name = "codeGroup".opt
            }
        ),
        TreeGridField(
            new TreeGridFieldProps {
                name = "captionGroup".opt
            }
        ),
        TreeGridField(
            new TreeGridFieldProps {
                name = "descriptionGroup".opt
            }
        ),
        TreeGridField(
            new TreeGridFieldProps {
                name = "active".opt
            }
        ),
        TreeGridField(
            new TreeGridFieldProps {
                name = "active".opt
            }
        )
    ).opt
    fieldsList = Seq(
        ListGridField(
            new ListGridFieldProps {
                name = "parent".opt
                hidden = true.opt
            }
        ),
        ListGridField(
            new ListGridFieldProps {
                name = "id".opt
            }
        )
    ).opt
}
