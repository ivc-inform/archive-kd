package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Record
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait LookupEditor extends Canvas {
    def selectRecordsByKey(keyValues: JSObject, newState: Boolean): Record
    def getSelectedRecord(): JSUndefined[Record]
    def getDataSource(): JSUndefined[DataSource]
}
