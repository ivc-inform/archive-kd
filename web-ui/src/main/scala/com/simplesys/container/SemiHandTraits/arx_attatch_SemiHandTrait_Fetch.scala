// This file is generated automatically (at 19.07.2017 19:27:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import java.sql.Types

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
import com.simplesys.jdbc.control.SessionStructures.callableStatement
import com.simplesys.jdbc.control.classBO._
import com.simplesys.jdbc.control.clob._
import com.simplesys.json.{JsonDouble, JsonLong, JsonString}
import com.simplesys.servlet.GetData
import com.simplesys.tuple.{TupleSS14, TupleSS15}
import oracle.jdbc.OracleConnection
import org.joda.time.LocalDateTime
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.bo.arx.{AttatchDS, DocizvDS}

import scala.compat.Platform.EOL
import scalaz.{Failure, Success}


trait arx_attatch_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = AttatchDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    val dataSetDocIzv = DocizvDS(oraclePool)

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val data = requestData.Data
                logger debug s"requestData.Data: ${newLine + data.toPrettyString}"

                val _data = RecordsDynList()
                val qty: Int = requestData.EndRow.toInt - requestData.StartRow.toInt + 1

                val select = dataSet.Fetch(dsRequest = DSRequest(sqlDialect = sessionContext.getSQLDialect, startRow = requestData.StartRow, endRow = requestData.EndRow, sortBy = requestData.SortBy, data = data, textMatchStyle = requestData.TextMatchStyle.toString))

                def checkStatus(id: Long): Int = {
                    callableStatement(dataSet.dataSource.getConnection(), "begin ? := record_doc.check_loc_record(fid => :fid); end;") {
                        callableStatement ⇒
                            callableStatement.registerOutParameter(1, Types.NUMERIC)
                            callableStatement.setLong(2, id)
                            callableStatement.executeUpdate()
                            val status = callableStatement.getInt(1)
                            logger debug s"Status: $status for id: $id"
                            status
                    }
                }

                Out(classDyn = select.result match {
                    case Success(list) => {
                        list foreach {
                            case TupleSS15(ddateinAttatch: Array[LocalDateTime], idAttatch: Long, idattypesAttatch: Array[Long], idcardAttatch: Long, idizvAttatch: Array[Long], invnumenzAttatch: Array[String], statusAttatch: Array[Long], vatcodeAttatch: Array[String], vatdescrAttatch: Array[String], idAttatchtypes: Long, vattypenameAttatchtypes: String, idCard: Long, vcrcodeCard: Array[String], idDocizv: Long, vizcodeDocizv: Array[String]) ⇒

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
                                    arx_attatch_vcrcode_NameStrong.name → vcrcodeCard,
                                    arx_attatch_status_NameStrong.name → statusAttatch.headOption.map(status ⇒ if (status == 2) checkStatus(idAttatch) else status)
                                )

                                dataSetDocIzv.selectPList(where = Where(dataSetDocIzv.idDocizv === idDocizv)).result match {
                                    case Success(list) ⇒
                                        list.foreach {
                                            item ⇒
                                                record += (arx_docizvstat_vname_NameStrong.name → JsonString(item.vnameDocizvstat_Idsts.headOption.getOrElse("")))
                                                record += (arx_docizvtype_viztname_NameStrong.name → JsonString(item.viztnameDocizvtype_Idtypiz.headOption.getOrElse("")))
                                        }
                                    case Failure(e) ⇒
                                        logger error e.getStackTrace().mkString("", EOL, EOL)
                                }

                                GetAttFile.getOrdDoc(idAttatch).foreach {
                                    ordDoc ⇒
                                        ordDoc.source.foreach {
                                            ordSource ⇒
                                                ordSource.srcName.foreach(srcName ⇒ record += ("fileName" → JsonString(srcName)))
                                                ordDoc.contentLength.foreach(contentLength ⇒ record += ("contentLength" → JsonString(s"${(contentLength / 1024 / 1024).setScale(4, BigDecimal.RoundingMode.HALF_UP)} MB")))
                                                ordDoc.mimeType.foreach(mimeType ⇒ record += ("mimeType" → JsonString(mimeType)))
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
