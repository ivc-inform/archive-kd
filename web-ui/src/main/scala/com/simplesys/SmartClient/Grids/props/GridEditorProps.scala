package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource, SortSpecifier}
import com.simplesys.SmartClient.Forms.FormsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.{ListGridFieldProps, ListGridRecordProps}
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor}
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.Layout.props.VLayoutSSProps
import com.simplesys.System.JSObject
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.DragDataAction._
import com.simplesys.System.Types.DragTrackerMode.DragTrackerMode
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.ListGridEditEvent.ListGridEditEvent
import com.simplesys.System.Types.RecordComponentPoolingMode.RecordComponentPoolingMode
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionStyle._
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, ThisFunction2, ThisFunction4}

class GridEditorProps[T <: ListGridFieldProps, R <: ListGridRecordProps] extends VLayoutSSProps {
    var closedIconSuffix: ScOption[String] = ScNone
    var dropIconSuffix: ScOption[String] = ScNone
    var openIconSuffix: ScOption[String] = ScNone
    var canDragSelectText: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecords: ScOption[Boolean] = ScNone
    var autoFitFieldWidth: ScOption[Boolean] = ScNone
    var canResizeFields: ScOption[Boolean] = ScNone
    var showAllRecords: ScOption[Boolean] = ScNone
    var showRecordComponentsByCell: ScOption[Boolean] = ScNone
    var showRecordComponents: ScOption[Boolean] = ScNone
    var filterOnKeypress: ScOption[Boolean] = ScNone
    var drawAheadRatio: ScOption[Double] = ScNone
    var autoSaveEdits: ScOption[Boolean] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var showRollOve: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var showRowNumbers: ScOption[Boolean] = ScNone
    var autoSaveConfig: ScOption[Boolean] = ScNone
    var showAdvancedFilter: ScOption[Boolean] = ScNone
    var emptyMessage: ScOption[String] = ScNone
    var wrapCells: ScOption[Boolean] = ScNone
    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var fixedRecordHeights: ScOption[Boolean] = ScNone
    var dragTrackerMode: ScOption[DragTrackerMode] = ScNone
    var autoFitWidthApproach: ScOption[AutoFitWidthApproach] = ScNone
    var modalEditing: ScOption[Boolean] = ScNone
    var canSelectCells: ScOption[Boolean] = ScNone
    var useClientFilteringSorting: ScOption[Boolean] = ScNone
    var selectionAppearance: ScOption[SelectionAppearance] = ScNone
    var cancelEditingConfirmationMessage: ScOption[String] = ScNone
    var fetchDelay: ScOption[Int] = ScNone
    var editEvent: ScOption[ListGridEditEvent] = ScNone
    var canSelectTextExpandedField: ScOption[Boolean] = ScNone
    var showFilterEditor: ScOption[Boolean] = ScNone
    var dataPageSize: ScOption[Int] = ScNone
    var funcMenu: ScOption[MenuSS] = ScNone
    var dataSource: ScOption[DataSource] = ScNone
    var saveItems: ScOption[Seq[MenuSSItemProps]] = ScNone
    var selectionType: ScOption[SelectionStyle] = ScNone
    var masterGrid: ScOption[ListGrid] = ScNone
    var canDragRecordsOut: ScOption[Boolean] = ScNone
    var canReorderRecords: ScOption[Boolean] = ScNone
    var dragDataAction: ScOption[DragDataAction] = ScNone
    var startEditingNewInForm: ScOption[Function4[JSObject, Seq[FormItem], DSCallback, DSRequest, _]] = ScNone
    var startEditingInForm: ScOption[Function4[JSObject, Seq[FormItem], DSCallback, DSRequest, _]] = ScNone
    var newRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editingFields: ScOption[Seq[FormItem]] = ScNone
    var saveByCell: ScOption[Boolean] = ScNone
    var dataFetchMode: ScOption[FetchMode] = ScNone
    var editWindowProperties: ScOption[WindowSS] = ScNone
    var recordComponentPoolingMode: ScOption[RecordComponentPoolingMode] = ScNone
    var initialSort: ScOption[Seq[SortSpecifier]] = ScNone

    var createRecordComponent: ScOption[ThisFunction2[classHandler, ListGridRecord, Int, Canvas]] = ScNone
    var updateRecordComponent: ScOption[ThisFunction4[classHandler, ListGridRecord, Int, Canvas, Boolean, Canvas]] = ScNone

    var selectFirstRecordAfterFetch: ScOption[Boolean] = ScNone
    var replacingFields: ScOption[Seq[ListGridFieldProps]] = ScNone

    var fields: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFields: ScOption[Seq[ListGridFieldProps]] = ScNone
}

class ListGridEditorProps extends GridEditorProps[ListGridFieldProps, ListGridRecordProps] {
    type classHandler <: ListGridEditor
    var data: ScOption[Seq[ListGridRecord]] = ScNone
}
