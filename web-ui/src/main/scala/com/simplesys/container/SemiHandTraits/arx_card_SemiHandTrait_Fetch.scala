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

    def receiveBase: Option[Actor.Receive] = Some({
        case GetData => {
            logger debug s"request: ${newLine + requestData.toPrettyString}"

            val data = requestData.Data
            logger debug s"data: ${newLine + data.toPrettyString}"

            val _data = RecordsDynList()
            val qty: Int = requestData.EndRow.toInt - requestData.StartRow.toInt + 1

            val select = dataSet.Fetch(dsRequest = DSRequest(sqlDialect = sessionContext.getSQLDialect, startRow = requestData.StartRow, endRow = requestData.EndRow, sortBy = requestData.SortBy, data = data, textMatchStyle = requestData.TextMatchStyle.toString))

            Out(classDyn = select.result match {
                case Success(list) => {
                    list foreach {
                        case TupleSS28(datecardCard: Array[LocalDateTime], idCard: Long, idcatsCard: Array[Long], idcrd_fkCard: Array[Long], iddocitemCard: Array[Long], idgroupCard: Array[Long], idstateCard: Array[Long], idtypeCard: Array[Long], vcrcodeCard: Array[String], vcrcodeprnCard: Array[String], vcrinventCard: Array[String], vcrkolpagesCard: Array[String], vcrnameCard: Array[String], vcrsdescCard: Array[String], vfileidentCard: Array[String], vformatsCard: Array[String], vrazrCard: Array[String], idDoccats: Long, vctnameDoccats: String, idDocitem: Long, vitcodeDocitem: String, vitnameDocitem: String, idGroupitem: Long, vginameGroupitem: String, idState: Long, vsecaptionState: String, idDoctypes: Long, vtynameDoctypes: String) =>
                            _data += RecordDyn("vcrname" -> vcrnameCard, "vcrcode" -> vcrcodeCard, "vcrsdesc" -> vcrsdescCard, "id" -> idCard, "datecard" -> datecardCard, "vfileident" -> vfileidentCard, "idcrd_fk" -> idcrd_fkCard, "vcrinvent" -> vcrinventCard, "vcrkolpages" -> vcrkolpagesCard, "vcrcodeprn" -> vcrcodeprnCard, "vformats" -> vformatsCard, "vrazr" -> vrazrCard, "idtype" -> idtypeCard, "idcats" -> idcatsCard, "idstate" -> idstateCard, "idgroup" -> idgroupCard, "iddocitem" -> iddocitemCard, "vtyname" -> vtynameDoctypes, "vctname" -> vctnameDoccats, "vsecaption" -> vsecaptionState, "vginame" -> vginameGroupitem, "vitcode" -> vitcodeDocitem, "vitname" -> vitnameDocitem)
                        case x =>
                            new RuntimeException(s"mached as : $x")
                    }

                    logger debug s"_data: ${newLine + _data.toPrettyString}"


                    new DSResponseDyn {
                        Status = RPCResponseDyn.statusSuccess
                        Data = _data
                        TotalRows = requestData.StartRow.toInt + (if (qty == list.length) qty * 2 else list.length)
                    }
                }
                case Failure(_) =>
                    new DSResponseFailureExDyn(select)
            })

            selfStop()
        }
        case x =>
            throw new RuntimeException(s"Bad branch $x")
    })

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
