// This file is generated automatically (at 18.07.2017 13:44:03), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.servlet.GetData
import com.simplesys.tuple.TupleSS28
import org.joda.time.LocalDateTime
import com.simplesys.jdbc._
import com.simplesys.common._
import ru.simplesys.defs.bo.arx._

import scalaz.{Failure, Success}


trait arx_card_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = CardDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = None

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
