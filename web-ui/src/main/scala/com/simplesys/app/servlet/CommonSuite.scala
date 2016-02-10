package com.simplesys.app.servlet

import akka.actor.ActorSystem
import com.simplesys.app.SessionContext
import com.simplesys.isc.RPC.TransactionDyn
import com.simplesys.isc.dataBinging.DSResponseDyn._
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn}
import com.simplesys.isc.system.ArrayDyn
import com.simplesys.isc.system.misc._
import com.simplesys.isc.templates.logJSActor
import com.simplesys.json.JsonObject
import com.simplesys.log.Logging
import com.simplesys.servlet.http.HttpServletResponse

trait CommonSuite extends Logging {
    def makeResponce(transaction: TransactionDyn,
                     sessionContext: SessionContext,
                     recRequestData: (DSRequestDyn, Number) => DSResponseDyn,
                     response: HttpServletResponse,
                     requestData: DSRequestDyn)(implicit system: ActorSystem) {
        if (!transaction.isEmpty) {
            val responseList = ArrayDyn.empty[DSResponseDyn]

            logger trace (s"transaction.Operations: ${transaction.Operations.toPrettyString}")

            transaction.Operations foreach {
                _ match {
                    case jsonObject: JsonObject =>
                        responseList += recRequestData(new DSRequestDyn(jsonObject), 0)
                    case _ =>
                }
            }
            response PrintAndFlush logJSActor(responseList.toMultyResponse.toString())
        } else
            response PrintAndFlush logJSActor(recRequestData(requestData, 0).WrapResponseObject.toString)
    }
}