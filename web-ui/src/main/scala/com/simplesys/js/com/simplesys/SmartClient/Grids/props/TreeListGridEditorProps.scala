package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Layout.props.HLayoutSSProps
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.option.{IntString, ScNone, ScOption}

class TreeListGridEditorProps extends HLayoutSSProps {
    var widthTree: ScOption[IntString[Int, String]] = ScNone
    var widthList: ScOption[IntString[Int, String]] = ScNone
    var fetchTreeDelay: ScOption[Int] = ScNone
    var fetchListDelay: ScOption[Int] = ScNone
    var showTreeRecordComponents: ScOption[Boolean] = ScNone
    var showListRecordComponents: ScOption[Boolean] = ScNone
    var editByCellTree: ScOption[Boolean] = ScNone
    var editByCellList: ScOption[Boolean] = ScNone
    var enableChangeSelectionTree: ScOption[Boolean] = ScNone
    var showAllChild: ScOption[Boolean] = ScNone
    var selectionTypeTree: ScOption[SelectionStyle] = ScNone
    var selectionTypeList: ScOption[SelectionStyle] = ScNone
    var getTreeSelection: ScOption[IscArray[ListGridRecord]] = ScNone
    var showTreeFilterEditor: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecordsTree: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecordsList: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var showTreeAdvancedFilter: ScOption[Boolean] = ScNone
    var cancelEditingConfirmationMessageList: ScOption[String] = ScNone
    var cancelEditingConfirmationMessageTree: ScOption[String] = ScNone
    var showListAdvancedFilte: ScOption[Boolean] = ScNone
    var canAcceptDropTree: ScOption[Boolean] = ScNone
    var drawAheadRatioList: ScOption[Double] = ScNone
    var drawAheadRatioTree: ScOption[Double] = ScNone
    var showResizeBarList: ScOption[Boolean] = ScNone
    var heightTree: ScOption[IntString[Int, String]] = ScNone
    var heightList: ScOption[IntString[Int, String]] = ScNone
    var canSelectCellsLis: ScOption[Boolean] = ScNone
    var showTreeRecordComponentsByCell: ScOption[Boolean] = ScNone
    var showListRecordComponentsByCell: ScOption[Boolean] = ScNone
    var canSelectCellsTree: ScOption[Boolean] = ScNone
    var emptyMessageTree: ScOption[String] = ScNone
    var emptyMessageList: ScOption[String] = ScNone
    var showResizeBarTree: ScOption[Boolean] = ScNone
    var canReparentNodesTree: ScOption[Boolean] = ScNone
    var showListFilterEditor: ScOption[Boolean] = ScNone
    var filterTreeOnKeypress: ScOption[Boolean] = ScNone
    var filterListOnKeypress: ScOption[Boolean] = ScNone
    var showOpenIconsTree: ScOption[Boolean] = ScNone
    var loadDataOnDemandTree: ScOption[Boolean] = ScNone
    var dataPageSizeTree: ScOption[Int] = ScNone
    var dataPageSizeList: ScOption[Int] = ScNone
    var autoFetchTextMatchStyleTree: ScOption[TextMatchStyle] = ScNone
    var autoFetchTextMatchStyleList: ScOption[TextMatchStyle] = ScNone
    var canEditTree: ScOption[Boolean] = ScNone
    var canEditList: ScOption[Boolean] = ScNone
    var folderDropImageTree: ScOption[SCImgURL] = ScNone
    var folderIconTree: ScOption[SCImgURL] = ScNone
    var nodeIconTree: ScOption[SCImgURL] = ScNone
    var wrapTreeCells: ScOption[Boolean] = ScNone
    var canSelectCellsList: ScOption[Boolean] = ScNone
    var autoSaveListEdits: ScOption[Boolean] = ScNone
    var autoSaveTreeEdits: ScOption[Boolean] = ScNone
    var dataSourceList: ScOption[DataSource] = ScNone
    var dataSourceTree: ScOption[DataSource] = ScNone
    var wrapListCells: ScOption[Boolean] = ScNone
    var fieldsTree: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var defaultFieldsTree: ScOption[Seq[TreeGridFieldProps]] = ScNone
    var fieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
}