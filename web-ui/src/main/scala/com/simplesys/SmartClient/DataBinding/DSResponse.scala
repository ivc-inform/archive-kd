package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.RPC.{AbstractRPCResponseCompanion, RPCResponse}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.Record

import scala.scalajs.js

@js.native
trait DSResponse extends RPCResponse {
    val data: IscArray[Record]
    val dataSource: String
    val endRow: Int
    val errors: JSObject
    val fromOfflineCache: Boolean
    val offlineTimestamp: Int
    val operationType: DSOperationType
    val queueStatus: Int
    val startRow: Int
    val totalRows: Int
}

@js.native
abstract trait AbstractDSResponseCompanion extends AbstractRPCResponseCompanion {
}

@js.native
object DSResponse extends AbstractDSResponseCompanion

