package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonTreeListGridEditorComponent
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class CommonTreeListGridEditorComponentProps extends TreeListGridEditorProps with InitialTrait {
    type classHandler <: CommonTreeListGridEditorComponent

    var simpleTableTree: ScOption[Boolean] = false.opt
    var simpleTableList: ScOption[Boolean] = true.opt

    autoFetchData = true.opt
    wrapTreeCells = true.opt
    drawAheadRatioList = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    drawAheadRatioTree = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    canSelectCellsList = false.opt
    showListRecordComponents = false.opt
    folderIconTree = Common.iconFolder.opt

    //dataFetchModeTree = FetchMode.basic.opt
    dataPageSizeTree = simpleSyS.config.dataPageSize.getOrElse(75).opt

    //dataFetchModeList = FetchMode.basic.opt
    dataPageSizeList = simpleSyS.config.dataPageSize.getOrElse(75).opt

    nodeIconTree = Common.iconTreeNode.opt
    canEditList = true.opt
    canEditTree = true.opt
    showListRecordComponentsByCell = false.opt
    wrapListCells = true.opt
    wrapTreeCells = true.opt
    showOpenIconsTree = false.opt

    var replacingFieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
    var replacingFieldsTree: ScOption[Seq[ListGridFieldProps]] = ScNone

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            val res = initWidget(thiz, thiz.fieldsList, thiz.replacingFieldsList, thiz.editingListFields, arguments)
            thiz.fieldsList = res._1
            thiz.editingListFields = res._2

            val res1 = initWidget(thiz, thiz.fieldsTree, thiz.replacingFieldsTree, thiz.editingTreeFields, arguments)
            thiz.fieldsTree = res1._1
            thiz.editingTreeFields = res1._2

            thiz.Super("initWidget", arguments)

            thiz setFuncMenu TreeListGridContextMenu.create(
                new TreeListGridContextMenuProps {
                    simpleTableList = thiz.simpleTableList.opt
                    simpleTableTree = thiz.simpleTableTree.opt
                    owner = thiz.opt
                }
            )

            thiz.getViewState()

    }.toThisFunc.opt

}
