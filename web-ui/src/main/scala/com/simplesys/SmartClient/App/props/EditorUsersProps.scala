package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.EditorUsers
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.ScalaJSDefined


@ScalaJSDefined
trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class EditorUsersProps extends TreeListGridEditorProps with InitialTrait {
    type classHandler <: EditorUsers

    identifier = "58125E1C-252A-01C4-11A1-557FA3222D3F".opt
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

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            val res = initWidget(thiz, thiz.fieldsList, thiz.replacingFieldsList, thiz.editingListFields, arguments)
            thiz.fieldsList = res._1
            thiz.editingListFields = res._2

            val res1 = initWidget(thiz, thiz.fieldsTree, thiz.replacingFieldsTree, thiz.editingTreeFields, arguments)
            thiz.fieldsTree = res1._1
            thiz.editingTreeFields = res1._2

            thiz.Super("initWidget", arguments)

            thiz setFuncMenu UserComponentMenu.create(
                new UserComponentMenuProps {
                    owner = thiz.opt
                }
            )

            thiz.getViewState()

    }.toThisFunc.opt

    editWindowPropertiesTree = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
