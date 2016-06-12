package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.Editoradmin_User
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Forms.FormsItems.props.SelectItemProps
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.JSAny
import com.simplesys.System.Types.Alignment
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class Editoradmin_UserProps extends TreeListGridEditorProps {
    type classHandler <: Editoradmin_User

    var treeDataSource: ScOption[DataSource] = ScNone
    var listDataSource: ScOption[DataSource] = ScNone

    folderDropImageTree = Common.iconFolder.opt
    autoFetchData = true.opt
    fieldsTree = Seq(
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
            hidden = true.opt
        }
    ).opt
    wrapTreeCells = true.opt
    drawAheadRatioList = simpleSyS.config.drawAheadRatio.getOrElse(1.3).opt
    drawAheadRatioTree = simpleSyS.config.drawAheadRatio.getOrElse(1.3).opt
    canSelectCellsList = false.opt
    showListRecordComponents = false.opt
    folderIconTree = Common.iconFolder.opt
    dataPageSizeTree = simpleSyS.config.dataPageSize.getOrElse(70).opt
    dataPageSizeList = simpleSyS.config.dataPageSize.getOrElse(70).opt
    autoSaveListEdits = false.opt
    nodeIconTree = Common.iconTreeNode.opt
    canEditList = true.opt
    canEditTree = true.opt
    showListRecordComponentsByCell = false.opt
    wrapListCells = true.opt
    wrapTreeCells = true.opt
    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.Super("initWidget", arguments)

            thiz.setListFields(
                IscArray(
                    ListGridField(
                        new ListGridFieldProps {
                            name = "active".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "caption".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "di".opt
                            hidden = true.opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "firstName".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "group".opt
                            displayField = "captionGroup".opt
                            align = Alignment.center.opt
                            editorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.listGrid.dataSource.opt
                                    displayField = "captionGroup".opt
                                    valueField = "group".asInstanceOf[JSAny].opt
                                }
                            ).opt
                            filterEditorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.listGrid.dataSource.opt
                                }
                            ).opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "lastName".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "login".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "password".opt
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "secondName".opt
                        })
                )
            )

            thiz setTreeDataSource thiz.treeDataSource
            thiz setListDataSource thiz.listDataSource

            thiz.setFuncMenu(
                User_ComponentMenu.create(
                    new User_ComponentMenuProps {
                        owner = thiz.opt
                    }
                )
            )
    }.toThisFunc.opt
}
