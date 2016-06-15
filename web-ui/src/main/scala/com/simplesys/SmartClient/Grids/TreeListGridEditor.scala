package com.simplesys.SmartClient.Grids

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Grids.treeGrid.TreeGridField
import com.simplesys.SmartClient.Layout.HLayoutSS
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.SelectionStyle.SelectionStyle
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait TreeListGridEditor extends HLayoutSS {
    def getTreeSelectedRecord(): ListGridRecord
    def getListSelectedRecord(): ListGridRecord
    def findByKeyList(keyValue: JSAny): ListGridRecord
    def findByKeyTree(keyValue: JSAny): ListGridRecord
    def discardListAllEdits(): void
    def discardTreeAllEdits(): void
    def updateListData(updatedRecord: ListGridRecord, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def updateTreeData(updatedRecord: ListGridRecord, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def removeListSelectedData(): void
    def removeTreeSelectedData(): void
    var widthTree: Int | String
    var widthList: Int | String
    def fetchTreeData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def fetchListData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var fetchTreeDelay: Int
    var fetchListDelay: Int
    var showTreeRecordComponents: Boolean
    var showListRecordComponents: Boolean
    def selectListSingleRecordByKey(keyValue: JSAny, newStyle: Boolean): ListGridRecord
    def selectTreeSingleRecordByKey(keyValue: JSAny, newStyle: Boolean): ListGridRecord
    var editByCellTree: Boolean
    var editByCellList: Boolean
    var enableChangeSelectionTree: Boolean
    var showAllChild: Boolean
    var selectionTypeTree: SelectionStyle
    var selectionTypeList: SelectionStyle
    def getTreeSelection: IscArray[ListGridRecord]
    var showTreeFilterEditor: Boolean
    def cancelTreeEditing(): void
    def cancelListEditing(): void
    var canAcceptDroppedRecordsTree: Boolean
    var canAcceptDroppedRecordsList: Boolean
    var autoFetchData: Boolean
    def startListEditing(): void
    def startTreeEditing(): void
    def setTreeFields(fields: IscArray[TreeGridField])
    def setListFields(fields: IscArray[ListGridField])
    var showTreeAdvancedFilter: Boolean
    def startTreeEditingNew(): void
    def saveListAllEdits(): void
    def saveTreeAllEdits(): void
    var cancelEditingConfirmationMessageList: String
    var cancelEditingConfirmationMessageTree: String
    var showListAdvancedFilte: Boolean
    var canAcceptDropTree: Boolean
    var drawAheadRatioList: Double
    def setListSelectedState(selectedState: ListGridSelectedState): void
    def getListSelectedState(): ListGridSelectedState
    var drawAheadRatioTree: Double
    def startListEditingNew
    def startListEditingNew(newValues: JSObject | ListGridRecord = js.native, suppressFocus: Boolean = js.native): void
    def setFuncMenu(menu: MenuSS): void
    var showResizeBarList: Boolean
    def fullTreeRefresh(): void
    def fullListRefresh(): void
    def getListEditRecord(): ListGridRecord
    var heightTree: Int | String
    var heightList: Int | String
    def setTreeSelectedState(selectedState: TreeGridSelectedState): void
    def getTreeSelectedState(): TreeGridSelectedState
    var canSelectCellsLis: Boolean
    var showTreeRecordComponentsByCell: Boolean
    var showListRecordComponentsByCell: Boolean
    var canSelectCellsTree: Boolean
    var emptyMessageTree: String
    var emptyMessageList: String
    var showResizeBarTree: Boolean
    var canReparentNodesTree: Boolean
    var showListFilterEditor: Boolean
    var filterTreeOnKeypress: Boolean
    var filterListOnKeypress: Boolean
    var showOpenIconsTree: Boolean
    var loadDataOnDemandTree: Boolean
    def setTreeDataSource(dataSource: DataSource): void
    def setListDataSource(dataSource: DataSource): void
    var dataPageSizeTree: Int
    var dataPageSizeList: Int
    var autoFetchTextMatchStyleTree: TextMatchStyle
    var autoFetchTextMatchStyleList: TextMatchStyle
    var canEditTree: Boolean
    var canEditList: Boolean
    def setContextMenuTreeGridEditor(menu: MenuSS): void
    def setContextMenuListGridEditor(menu: MenuSS): void
    def setTreeCanReparentNodes(value: Boolean): void
    def getListCriteria(): Criteria
    def getTreeCriteria(): Criteria
    var folderDropImageTree: SCImgURL
    var folderIconTree: SCImgURL
    var nodeIconTree: SCImgURL
    var canSelectCellsList: Boolean
    var autoSaveListEdits: Boolean
    var autoSaveTreeEdits: Boolean
    var dataSourceList: DataSource
    var dataSourceTree: DataSource
    var wrapListCells: Boolean
    var wrapTreeCells: Boolean
    def getListGrid(): ListGridEditor
    def getTreeGrid(): TreeGridEditor
    var fieldsTree: IscArray[TreeGridField]
    var defaultFieldsTree: IscArray[TreeGridField]
    var fieldsList: IscArray[ListGridField]
    var defaultFieldsList: IscArray[ListGridField]
    val treeGrid: TreeGridEditor
    val listGrid: ListGridEditor
    val contextMenuListGridEditor: MenuSS
    val contextMenuTreeGridEditor: MenuSS
    def getViewState(): void
    var newTreeRequestProperties: JSUndefined[DSRequest]
    var editTreeRequestProperties: JSUndefined[DSRequest]
    var newListRequestProperties: JSUndefined[DSRequest]
    var editListRequestProperties: JSUndefined[DSRequest]
    var editingTreeFields  : JSUndefined[IscArray[FormItem]]
    var editingListFields  : JSUndefined[IscArray[FormItem]]
}

