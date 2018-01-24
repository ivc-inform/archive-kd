// This file is generated automatically (at 19.07.2017 19:27:32), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.arx

import java.sql.{Connection, Types}
import java.time.LocalDateTime

import akka.actor.Actor
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings._
import com.simplesys.container.scala.GetAttFile
import com.simplesys.isc.dataBinging.{DSRequest, DSResponse, RPCResponse}
import com.simplesys.jdbc.control.SessionStructures.callableStatement
import com.simplesys.jdbc.control.classBO._
import com.simplesys.jdbc.control.clob._
import com.simplesys.servlet.isc.{GetData, ServletActor}
import io.circe.generic.auto._
import io.circe.syntax._
import com.simplesys.circe.Circe._
import com.simplesys.isc.dataBinging.DSResponse.DSResponseFailureEx
import com.simplesys.jdbc.control.DsRequest
import com.simplesys.tuple.TupleSS15
import io.circe.Json
import io.circe.Json._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.bo.arx.{AttatchDS, DocizvDS}

import scala.collection.mutable.ArrayBuffer
import scala.compat.Platform.EOL
import scalaz.{Failure, Success}


trait arx_attatch_SemiHandTrait_Fetch extends SessionContextSupport with ServletActor {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData: DSRequest = request.JSONData.as[DSRequest].getOrElse(throw new RuntimeException("Dont parsed Request JSON"))

    logger debug s"Request for Fetch: ${newLine + requestData.asJson.toPrettyString}"

    val dataSet = AttatchDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    val dataSetDocIzv = DocizvDS(oraclePool)

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {

                val data = requestData.data.getOrElse(Json.Null)
                logger debug s"requestData.Data: ${newLine + data.toPrettyString}"

                val _data = ArrayBuffer.empty[Json]
                val qty: Int = requestData.endRow.getOrElse(1) - requestData.startRow.getOrElse(0) + 1

                val select = dataSet.Fetch(
                    dsRequest = DsRequest(
                        sqlDialect = sessionContext.getSQLDialect,
                        startRow = requestData.startRow.getOrElse(0),
                        endRow = requestData.endRow.getOrElse(0),
                        sortBy = requestData.sortBy.getOrElse(Vector.empty),
                        data = data,
                        textMatchStyle = requestData.textMatchStyle.getOrElse("exact")
                    ))

                lazy val connection = oraclePool.getConnection()

                def checkStatus(connection: Connection, id: Long): Int = {
                    callableStatement(connection, "begin ? := record_doc.get_lock_record(fid => ?); end;") {
                        callableStatement ⇒
                            callableStatement.registerOutParameter(1, Types.NUMERIC)
                            callableStatement.setLong(2, id)
                            callableStatement.executeUpdate()
                            val status = callableStatement.getInt(1)
                            logger debug s"Status: $status for id: $id"
                            status
                    }
                }

                Out(out = select.result match {
                    case Success(list) => {
                        list foreach {
                            case TupleSS15(ddateinAttatch: Array[LocalDateTime],
                            idAttatch: Long,
                            idattypesAttatch: Array[Long],
                            idcardAttatch: Long,
                            idizvAttatch: Array[Long],
                            invnumenzAttatch: Array[String],
                            statusAttatch: Array[Long],
                            vatcodeAttatch: Array[String],
                            vatdescrAttatch: Array[String],
                            idAttatchtypes_Idattypes: Long,
                            vattypenameAttatchtypes_Idattypes: String,
                            idCard_Idcard: Long,
                            vcrcodeCard_Idcard: Array[String],
                            idDocizv_Idizv: Long,
                            vizcodeDocizv_Idizv: Array[String]) =>

                                val record = obj(
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
                                    arx_attatch_status_NameStrong.name → checkStatus(connection, idAttatch)
                                )

                                dataSetDocIzv.selectPList(where = Where(dataSetDocIzv.idDocizv === idDocizv)).result match {
                                    case Success(list) ⇒
                                        list.foreach {
                                            item ⇒
                                                record += (arx_docizvstat_vname_NameStrong.name → fromString(item.vnameDocizvstat_Idsts.headOption.getOrElse("")))
                                                record += (arx_docizvtype_viztname_NameStrong.name → fromString(item.viztnameDocizvtype_Idtypiz.headOption.getOrElse("")))
                                        }
                                    case Failure(e) ⇒
                                        logger error e.getStackTrace().mkString("", EOL, EOL)
                                }

                                GetAttFile.getOrdDoc(idAttatch).foreach {
                                    ordDoc ⇒
                                        ordDoc.source.foreach {
                                            ordSource ⇒
                                                ordSource.srcName.foreach(srcName ⇒ record += ("fileName" → fromString(srcName)))
                                                ordDoc.contentLength.foreach(contentLength ⇒ record += ("contentLength" → fromString(s"${(contentLength / 1024 / 1024).setScale(4, BigDecimal.RoundingMode.HALF_UP)} MB")))
                                                ordDoc.mimeType.foreach(mimeType ⇒ record += ("mimeType" → fromString(mimeType)))
                                        }
                                }

                                _data += record


                            case x =>
                                new RuntimeException(s"mached as : $x")
                        }

                        logger debug s"_data: ${newLine + arr(_data: _*).toPrettyString}"

                        DSResponse(
                            status = RPCResponse.statusSuccess,
                            data = arr(_data: _*),
                            totalRows = Some(
                                requestData.startRow.getOrElse(0) + (if (qty == list.length)
                                    qty * 2
                                else list.length)))
                    }
                    case Failure(_) =>
                        DSResponseFailureEx(select.printException.get.message,
                            select.printException.get.stackTrace)
                })

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = inputStream2Sting(blob)
}
