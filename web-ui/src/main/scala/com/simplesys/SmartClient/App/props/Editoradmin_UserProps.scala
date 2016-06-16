package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.Editoradmin_User
import com.simplesys.SmartClient.DataBinding.props.{DSRequestProps, SortSpecifierProps}
import com.simplesys.SmartClient.Forms.FormsItems.props.{CheckboxItemProps, SelectItemProps, TextAreaItemProps, TextItemProps}
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Alignment, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.DataSourcesJS

import scala.scalajs.js.annotation.ScalaJSDefined


@ScalaJSDefined
trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class Editoradmin_UserProps extends TreeListGridEditorProps {
    type classHandler <: Editoradmin_User

    identifier = "58125E1C-252A-01C4-11A1-557FA3222D3F".opt
    dataSourceList = DataSourcesJS.admin_User_DS.opt
    dataSourceTree = DataSourcesJS.admin_UserGroup_DS.opt
    folderDropImageTree = Common.iconFolder.opt
    autoFetchData = true.opt
    wrapTreeCells = true.opt
    drawAheadRatioList = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    drawAheadRatioTree = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    canSelectCellsList = false.opt
    showListRecordComponents = false.opt
    folderIconTree = Common.iconFolder.opt
    dataPageSizeTree = simpleSyS.config.dataPageSize.getOrElse(75).opt
    dataPageSizeList = simpleSyS.config.dataPageSize.getOrElse(75).opt
    //autoSaveListEdits = false.opt
    nodeIconTree = Common.iconTreeNode.opt
    canEditList = true.opt
    canEditTree = true.opt
    showListRecordComponentsByCell = false.opt
    wrapListCells = true.opt
    wrapTreeCells = true.opt
    showOpenIconsTree = false.opt
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
    editingTreeFields = Seq(
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
    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            thiz.setTreeFields(
                IscArray(
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "codeGroup".opt
                        }),
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "captionGroup".opt
                        }),
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "descriptionGroup".opt
                        }),
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "di".opt
                            hidden = true.opt
                        }),
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "active".opt
                            `type` = ListGridFieldType.boolean.opt
                        })
                )
            )

            thiz.setListFields(
                IscArray(
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
                            title = "Наименование группы".opt
                            editorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.treeGrid.dataSource.opt
                                    displayField = "captionGroup".opt
                                    valueField = "di".asInstanceOf[JSAny].opt
                                    initialSort = Seq(
                                        new SortSpecifierProps {
                                            property = "captionGroup".opt
                                        }
                                    ).opt
                                }
                            ).opt
                            filterEditorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.treeGrid.dataSource.opt
                                    initialSort = Seq(
                                        new SortSpecifierProps {
                                            property = "captionGroup".opt
                                        }
                                    ).opt
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
                        }),
                    ListGridField(
                        new ListGridFieldProps {
                            name = "active".opt
                            `type` = ListGridFieldType.boolean.opt
                        })
                )
            )

            thiz.setFuncMenu(
                User_ComponentMenu.create(
                    new User_ComponentMenuProps {
                        owner = thiz.opt
                    }
                )
            )

            thiz.getViewState()

    }.toThisFunc.opt
}
