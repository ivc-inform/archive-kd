package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse, ResponseData}
import com.simplesys.SmartClient.System.isc
import com.simplesys.System.Types.void
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.UndefOr._

class RestDataSourceProps extends DataSourceProps {

    import com.simplesys.SmartClient.DataBinding.RestDataSource

    type classHandler <: RestDataSource

    var addDataURL: ScOption[String] = ScNone
    var disableQueuing: ScOption[Boolean] = ScNone
    var fetchDataURL: ScOption[String] = ScNone
    var jsonRecordXPath: ScOption[String] = ScNone
    var metaDataPrefix: ScOption[String] = ScNone
    var prettyPrintJSON: ScOption[Boolean] = ScNone
    var removeDataURL: ScOption[String] = ScNone
    var sendMetaData: ScOption[Boolean] = ScNone
    var transformRequest: ScOption[js.Function1[void, _]] = ScNone
    var updateDataURL: ScOption[String] = ScNone
    var xmlRecordXPath: ScOption[String] = ScNone

    transformResponse = {
        import com.simplesys.System.JSUndefined


        (thiz: classHandler, resp: DSResponse, req: DSRequest, data: JSUndefined[ResponseData]) ⇒

            import com.simplesys.System.Types.DSOperationType

            val status: Int = {
                if (data.isDefined && data.get.status.isDefined)
                    data.get.status.get
                else if (data.isDefined && data.get.data.isDefined && data.get.data.get.status.isDefined)
                    data.get.data.get.status.get
                else
                    resp.status
            }

            if (resp.data.isDefined && resp.data.get.length == 0 && (req.operationType.get.toString == DSOperationType.add.toString || req.operationType.get.toString == DSOperationType.update.toString) && status >= 0) {
                data.foreach(resp.data.get add _.data)
            }

            if (data.isDefined && data.get.data.isDefined && status < 0) {

                import com.simplesys.System.JSDynamic
                //isc debugTrap data

                val errorStruct = data.get.data.get
                resp.asInstanceOf[JSDynamic].updateDynamic("errorStruct")(errorStruct)

                //isc debugTrap resp
            }

            //isc debugTrap (status)

            if (resp.errorStruct.isDefined && resp.status != status) {
                //isc debugTrap (resp)
                resp.status = status
                //isc debugTrap (resp)
            }

            resp
    }.toThisFunc.opt
}
