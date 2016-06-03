package com.simplesys.SmartClient.DataBinding

import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait RestDataSource extends DataSource {
    val addDataURL: String
    val disableQueuing: Boolean
    val fetchDataURL: String
    val jsonRecordXPath: String
    val metaDataPrefix: String
    val prettyPrintJSON: Boolean
    val removeDataURL: String
    val sendMetaData: Boolean
    var transformRequest: js.Function1[void, _]
    var updateDataURL: String
    val xmlRecordXPath: String
}



