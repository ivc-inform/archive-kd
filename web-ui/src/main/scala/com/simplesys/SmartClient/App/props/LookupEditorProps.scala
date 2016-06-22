package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.LookupEditor
import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System.Types.Record
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js.{ThisFunction0, ThisFunction2}

class LookupEditorProps extends CanvasProps {
    type classHandler <: LookupEditor

    var selectRecordsByKey: ScOption[ThisFunction2[classHandler, JSObject, Boolean, JSUndefined[Record]]] = {
        (thiz: classHandler, keyValues: JSObject, newState: Boolean) =>

            var res: JSUndefined[Record] = jSUndefined
            if (isc.isA.ListGrid(thiz)) {
                val grid = thiz.asInstanceOf[ListGrid]
                grid.deselectAllRecords()
                res = grid.selectRecordsByKey(keyValues, newState)
            }
            else if (isc.isA.ListGridEditor(thiz)) {
                val grid = thiz.asInstanceOf[ListGridEditor].grid
                grid.deselectAllRecords()
                res = grid.selectRecordsByKey(keyValues, newState)
            }
            else if (isc.isA.TreeGridEditor(thiz)) {
                val grid = thiz.asInstanceOf[TreeGridEditor].grid
                grid.deselectAllRecords()
                res = grid.selectRecordsByKey(keyValues, newState)
            }

            res

    }.toThisFunc.opt

    var getSelectedRecord: ScOption[ThisFunction0[classHandler, JSUndefined[Record]]] = {
        (thiz: classHandler) =>

            var res: JSUndefined[Record] = jSUndefined
            if (isc.isA.ListGrid(thiz))
                res = thiz.asInstanceOf[ListGrid].getSelectedRecord()
            else if (isc.isA.ListGridEditor(thiz))
                res = thiz.asInstanceOf[ListGridEditor].getSelectedRecord()
            else if (isc.isA.TreeGridEditor(thiz))
                res = thiz.asInstanceOf[TreeGridEditor].getSelectedRecord()

            if (res == null)
                jSUndefined
            else
                res

    }.toThisFunc.opt

    var getDataSource: ScOption[ThisFunction0[classHandler, JSUndefined[DataSource]]] = {
        (thiz: classHandler) =>

            var res: JSUndefined[DataSource] = jSUndefined
            if (isc.isA.ListGrid(thiz))
                res = thiz.asInstanceOf[ListGrid].dataSource
            else if (isc.isA.ListGridEditor(thiz))
                res = thiz.asInstanceOf[ListGridEditor].dataSource
            else if (isc.isA.TreeGridEditor(thiz))
                res = thiz.asInstanceOf[TreeGridEditor].dataSource

            res

    }.toThisFunc.opt
}
