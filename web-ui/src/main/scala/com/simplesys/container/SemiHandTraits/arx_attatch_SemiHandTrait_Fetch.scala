// This file is generated automatically (at 19.07.2017 19:27:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.container.scala.GetAttFile
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc._
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.jdbc.control.classBO._
import com.simplesys.jdbc.control.clob._
import com.simplesys.json.JsonString
import com.simplesys.servlet.GetData
import com.simplesys.tuple.TupleSS14
import oracle.jdbc.OracleConnection
import org.joda.time.LocalDateTime
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.bo.arx.{AttatchDS, DocizvDS}

import scalaz.{Failure, Success}

trait arx_attatch_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = AttatchDS(ds)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    val dataSetDocIzv = DocizvDS(ds)

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val data = requestData.Data
                logger debug s"requestData.Data: ${newLine + data.toPrettyString}"

                val _data = RecordsDynList()
                val qty: Int = requestData.EndRow.toInt - requestData.StartRow.toInt + 1

                val select = dataSet.Fetch(dsRequest = DSRequest(sqlDialect = sessionContext.getSQLDialect, startRow = requestData.StartRow, endRow = requestData.EndRow, sortBy = requestData.SortBy, data = data, textMatchStyle = requestData.TextMatchStyle.toString))
                implicit val oraConnection = ds.getConnection

                Out(classDyn = select.result match {
                    case Success(list) => {
                        list foreach {
                            case TupleSS14(
                            ddateinAttatch: Array[LocalDateTime],
                            idAttatch: Long,
                            idattypesAttatch: Array[Long],
                            idcardAttatch: Long,
                            idizvAttatch: Array[Long],
                            invnumenzAttatch: Array[String],
                            vatcodeAttatch: Array[String],
                            vatdescrAttatch: Array[String],
                            idAttatchtypes: Long,
                            vattypenameAttatchtypes: String,
                            idCard: Long,
                            vcrcodeCard: Array[String],
                            idDocizv: Long,
                            vizcodeDocizv: Array[String]) ⇒

                                val record = RecordDyn(
                                    arx_attatch_id_NameStrong.name → idAttatch,
                                    arx_attatch_vatcode_NameStrong.name → vatcodeAttatch,
                                    arx_attatch_ddatein_NameStrong.name → ddateinAttatch,
                                    arx_attatch_vatdescr_NameStrong.name → vatdescrAttatch,
                                    arx_attatch_invnumenz_NameStrong.name → invnumenzAttatch,
                                    arx_attatch_idizv_NameStrong.name → idizvAttatch,
                                    arx_attatch_idattypes_NameStrong.name → idattypesAttatch,
                                    arx_attatch_idcard_NameStrong.name → idcardAttatch,
                                    arx_attatch_vizcode_NameStrong.name → vizcodeDocizv,
                                    arx_attatch_vattypename_NameStrong.name → vattypenameAttatchtypes,
                                    arx_attatch_vcrcode_NameStrong.name → vcrcodeCard
                                )

                                dataSetDocIzv.selectPList(where = Where(dataSetDocIzv.idDocizv === idDocizv)).result match {
                                    case Success(list) ⇒
                                        list.foreach {
                                            item ⇒
                                                record += (arx_docizvstat_vname_NameStrong.name → JsonString(item.vnameDocizvstat_Idsts.headOption.getOrElse("")))
                                                record += (arx_docizvtype_viztname_NameStrong.name → JsonString(item.viztnameDocizvtype_Idtypiz.headOption.getOrElse("")))
                                        }
                                    case Failure(e) ⇒
                                        logger error e.getStackTraceString
                                }

                                GetAttFile.getOrdDoc(idAttatch).foreach {
                                    _.source.foreach {
                                        ordSource ⇒
                                            ordSource.srcName.foreach(srcName ⇒ record += ("fileName" → JsonString(srcName)))
                                    }
                                }

                                _data += record


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
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
