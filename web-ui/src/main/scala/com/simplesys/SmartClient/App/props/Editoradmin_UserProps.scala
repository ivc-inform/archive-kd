package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.Editoradmin_User
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Forms.FormsItems.props.SelectItemProps
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{Alignment, DSOperationType, ListGridFieldType}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.ScalaJSDefined


@ScalaJSDefined
trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class Editoradmin_UserProps extends TreeListGridEditorProps {
    type classHandler <: Editoradmin_User

    folderDropImageTree = Common.iconFolder.opt
    autoFetchData = true.opt
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
    showOpenIconsTree = false.opt
    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            thiz.setTreeFields(
                IscArray(
                    TreeGridField(
                        new TreeGridFieldProps {
                            name = "active".opt
                            `type` = ListGridFieldType.boolean.opt
                        }),
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
                        })
                )
            )

            thiz.setListFields(
                IscArray(
                    ListGridField(
                        new ListGridFieldProps {
                            name = "active".opt
                            `type` = ListGridFieldType.boolean.opt
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
                            title = "Наименование группы".opt
                            editorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.listGrid.dataSource.opt
                                    displayField = "captionGroup".opt
                                    valueField = "di".asInstanceOf[JSAny].opt
                                }
                            ).opt
                            filterEditorProperties = SelectItem(
                                new SelectItemProps {
                                    optionDataSource = thiz.treeGrid.dataSource.opt
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

            thiz.setFuncMenu(
                User_ComponentMenu.create(
                    new User_ComponentMenuProps {
                        owner = thiz.opt
                    }
                )
            )

            thiz.newTreeRequestProperties = DSRequest.create(
                new DSRequestProps {
                    operationType = DSOperationType.add.opt
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

            thiz.getViewState()

    }.toThisFunc.opt
}
