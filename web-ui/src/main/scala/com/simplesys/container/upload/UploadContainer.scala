package com.simplesys.container.upload

import java.io.File
import java.sql.{Connection, Timestamp, Types}
import java.time.{Instant, LocalDateTime, ZoneId}
import java.util.Properties

import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.annotation.RSTransfer
import com.simplesys.app.SessionContextSupport
import com.simplesys.common.Strings.newLine
import com.simplesys.container.scala.{GetAttFile, OrdDoc, OrdSource}
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.jdbc.control.SessionStructures._
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.json.{JsonLong, JsonObject, JsonString}
import com.simplesys.messages.ActorConfig.SendMessage
import com.simplesys.messages.Message
import com.simplesys.servlet.ContentType._
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}
import com.simplesys.servlet.{GetData, HTMLContent, ServletContext}
import com.simplesys.util.DT
import oracle.jdbc.dcn.{DatabaseChangeEvent, DatabaseChangeListener, DatabaseChangeRegistration}
import oracle.jdbc.{OracleBlob, OracleConnection}
import org.apache.commons.fileupload.ProgressListener
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import ru.simplesys.defs.bo.arx.{Attatch, AttatchBo}

import scala.collection.JavaConverters._
import scala.compat.Platform.EOL
import scala.util.{Failure, Success, Try}

trait UploadData extends JSObject {
    val idAttatch: JSUndefined[Double]
    val percentsDone: JSUndefined[Double]
    val fileName: JSUndefined[String]
    val fileSize: JSUndefined[Double]
    val elapsedTime: JSUndefined[String]
    val title: JSUndefined[String]
}

trait ErrorStr extends JSObject {
    val message: JSUndefined[String]
    val stack: JSUndefined[String]
}

object UploadContainer {

    @RSTransfer(urlPattern = "/logic/arx_attatch/Upload")
    class UploadActor(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

        val requestData = new DSRequestDyn(request)
        val connection = oraclePool.getConnection().asInstanceOf[OracleConnection]

        val dataSetBo = AttatchBo(oraclePool)

        logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

        def receive = {
            case GetData => {

                val startTime = System.currentTimeMillis()

                val isMultipart = ServletFileUpload.isMultipartContent(request)
                response.ContentType = HTMLContent

                logger.debug(" \n\n\n----------------------------------------------------------- Parametrs -------------------------------------------------------------------")
                request.Parameters.foreach {
                    case (key, value) ⇒
                        logger.debug(s"key: $key, value: ${value.getOrElse("None")}")
                }
                logger.debug(" ------------------------------------------------------- End Parametrs1 -------------------------------------------------------------------")


                val idAttatch = request.Parameter("id").map(_.toLong)
                val channelSubscribeToChannel = "12DC1876-F489-3172-1297-729FFB73B575" //Не менять используется в AttachProps !!!

                val channelMessageEndUpload = request.Parameter("p1")
                val channelMessageError = request.Parameter("p2")
                val channelMessageUploadPercent = request.Parameter("p3")
                val channelMessageRecordInBase = request.Parameter("p4")

                val dcr: Option[DatabaseChangeRegistration] = {
                    Try {
                        val prop = new Properties()
                        prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true")
                        val dcr = connection.asInstanceOf[OracleConnection].registerDatabaseChangeNotification(prop)

                        dcr.addListener(new DatabaseChangeListener {
                            def onDatabaseChangeNotification(dce: DatabaseChangeEvent): Unit = {
                                ///?????
                            }
                        })
                        dcr
                    } match {
                        case Success(dcr) ⇒ Some(dcr)
                        case Failure(e) ⇒ None
                    }
                }

                val factory = new DiskFileItemFactory()
                val file = new File(s"./temp")

                factory setRepository file
                val upload: ServletFileUpload = new ServletFileUpload(factory)

                if (!isMultipart) {
                    channelMessageError.foreach(channelMessageError ⇒ SendMessage(Message(data = JsonObject("message" → JsonString("No file uploaded"), "stack" → JsonString("No file uploaded")), channels = channelMessageError)))
                    OutFailure(new RuntimeException("No file uploaded"))
                    connection.close()
                } else {
                    idAttatch.foreach {
                        idAttatch ⇒

                            val attachRecord: Attatch = dataSetBo.selectPOne(where = Where(dataSetBo.id === idAttatch)) result match {
                                case scalaz.Success(attach) ⇒ attach
                                case scalaz.Failure(e) ⇒ throw e
                            }

                            def recordLock(connection: Connection, id: Long) = {
                                callableStatement(connection, "begin record_doc.set_lock_record(fid => ?); end;") {
                                    callableStatement ⇒
                                        callableStatement.setLong(1, id)
                                        callableStatement.executeUpdate()
                                        logger debug s"set lock on record id = $id"
                                }
                            }

                            Try {

                                val progressListener = new ProgressListener() {
                                    private var step = 1
                                    private var firstStep = true

                                    override def update(pBytesRead: Long, pContentLength: Long, pItems: Int): Unit = {
                                        val stepSize = pContentLength / 100

                                        if (firstStep) {
                                            SendMessage(Message(data = JsonObject("idAttatch" → JsonLong(idAttatch)), channels = channelSubscribeToChannel))
                                            firstStep = false
                                        }

                                        if (pBytesRead >= stepSize * step) {
                                            channelMessageUploadPercent.foreach(channelMessageUploadPercent ⇒ SendMessage(Message(data = JsonObject("percentsDone" → JsonLong(step)), channels = channelMessageUploadPercent)))
                                            step += 1
                                        }
                                    }
                                }

                                upload setProgressListener progressListener

                                def sendMessageTypeRecordInBase(title: String) = channelMessageRecordInBase.foreach(channelMessageRecordInBase ⇒ SendMessage(Message(data = JsonObject("title" → JsonString(title)), channels = channelMessageRecordInBase)))

                                transaction(oraclePool.getConnection().asInstanceOf[OracleConnection]) {
                                    connectionBlock ⇒
                                        recordLock(connectionBlock, idAttatch)
                                        upload.parseRequest(request).asScala.headOption.map {
                                            fi ⇒
                                                def getEmptySource: OrdSource =
                                                    new OrdSource {
                                                        override val srcName: Option[String] = Some(fi.getName)
                                                        override val srcLocation: Option[String] = None
                                                        override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                                                        override val local: Option[BigDecimal] = None
                                                        override val srcType: Option[String] = Some("FILE")
                                                        override val localData: Option[OracleBlob] = None
                                                    }


                                                def ordDoc: OrdDoc = GetAttFile.getOrdDoc(idAttatch) match {
                                                    case Some(ordDoc) ⇒
                                                        val _source = ordDoc.source match {
                                                            case Some(source) ⇒
                                                                new OrdSource {
                                                                    override val srcName: Option[String] = Some(fi.getName)
                                                                    override val srcLocation: Option[String] = None
                                                                    override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                                                                    override val local: Option[BigDecimal] = Some(1)
                                                                    override val srcType: Option[String] = source.srcType
                                                                    override val localData: Option[OracleBlob] = None
                                                                }
                                                            case None ⇒
                                                                getEmptySource
                                                        }

                                                        new OrdDoc {
                                                            override val comments: Option[String] = Some("Updated by UploadContainer !!")
                                                            override val format: Option[String] = Some(fi.getContentType)
                                                            override val source: Option[OrdSource] = Some(_source)
                                                            override val mimeType: Option[String] = None
                                                            override val contentLength: Option[BigDecimal] = Some(fi.getSize)
                                                        }

                                                    case None ⇒
                                                        new OrdDoc {
                                                            override val comments: Option[String] = Some("Inserted by UploadContainer !!")
                                                            override val format: Option[String] = Some(fi.getContentType)
                                                            override val source: Option[OrdSource] = Some(getEmptySource)
                                                            override val mimeType: Option[String] = None
                                                            override val contentLength: Option[BigDecimal] = Some(fi.getSize)
                                                        }
                                                }

                                                transaction(connectionBlock) {
                                                    connection ⇒
                                                        callableStatement(connection, "begin Record_Doc.MainRecOrdDoc(source_srcname => ?, source_srclocation => ?, source_updatetime => ?, source_local => ?, source_srctype => ?,source_localdata => ?, orddoc_format => ?, orddoc_mimetype => ?, orddoc_contentlength => ?, orddoc_comments => ?, fid => ?); end;") {
                                                            callableStatement ⇒

                                                                sendMessageTypeRecordInBase("Запись в БД ...")

                                                                var index = 1

                                                                ordDoc.source.foreach {
                                                                    source ⇒

                                                                        source.srcName match {
                                                                            case Some(srcName) ⇒
                                                                                callableStatement.setString(index, srcName)
                                                                            case None ⇒
                                                                                callableStatement.setNull(index, Types.VARCHAR)
                                                                        }

                                                                        index += 1
                                                                        source.srcLocation match {
                                                                            case Some(srcLocation) ⇒
                                                                                callableStatement.setString(index, srcLocation)
                                                                            case None ⇒
                                                                                callableStatement.setNull(index, Types.VARCHAR)
                                                                        }

                                                                        index += 1
                                                                        source.updateTime match {
                                                                            case Some(updateTime) ⇒
                                                                                callableStatement.setTimestamp(index, Timestamp.valueOf(updateTime))
                                                                            case None ⇒
                                                                                callableStatement.setNull(index, Types.TIMESTAMP)
                                                                        }

                                                                        index += 1
                                                                        source.local match {
                                                                            case Some(local) ⇒
                                                                                callableStatement.setBigDecimal(index, local.bigDecimal)
                                                                            case None ⇒
                                                                                callableStatement.setNull(index, Types.INTEGER)
                                                                        }

                                                                        index += 1
                                                                        source.srcType match {
                                                                            case Some(srcType) ⇒
                                                                                callableStatement.setString(index, srcType)
                                                                            case None ⇒
                                                                                callableStatement.setNull(index, Types.VARCHAR)
                                                                        }

                                                                        index += 1
                                                                        callableStatement.setBlob(index, fi.getInputStream)
                                                                }

                                                                index += 1
                                                                ordDoc.format match {
                                                                    case Some(format) ⇒
                                                                        callableStatement.setString(index, format)
                                                                    case None ⇒
                                                                        callableStatement.setNull(index, Types.VARCHAR)
                                                                }

                                                                index += 1
                                                                ordDoc.mimeType match {
                                                                    case Some(mimeType) ⇒
                                                                        callableStatement.setString(index, mimeType)
                                                                    case None ⇒
                                                                        callableStatement.setNull(index, Types.VARCHAR)
                                                                }

                                                                index += 1
                                                                ordDoc.contentLength match {
                                                                    case Some(contentLength) ⇒
                                                                        callableStatement.setBigDecimal(index, contentLength.bigDecimal)
                                                                    case None ⇒
                                                                        callableStatement.setNull(index, Types.INTEGER)
                                                                }

                                                                index += 1
                                                                ordDoc.comments match {
                                                                    case Some(comments) ⇒
                                                                        callableStatement.setString(index, comments)
                                                                    case None ⇒
                                                                        callableStatement.setString(index, "Recorded by Archive-KD")
                                                                }

                                                                index += 1
                                                                callableStatement.setLong(index, idAttatch)
                                                                callableStatement.executeUpdate()

                                                                dcr.foreach(connection.asInstanceOf[OracleConnection] unregisterDatabaseChangeNotification _)
                                                        }
                                                }.result match {
                                                    case scalaz.Success(res) ⇒
                                                    case scalaz.Failure(e) ⇒
                                                        throw e
                                                }

                                                fi
                                        }
                                }.result match {
                                    case scalaz.Success(fi) ⇒
                                        fi
                                    case scalaz.Failure(e) ⇒
                                        throw e
                                }

                            }
                            match {
                                case Success(fi) ⇒
                                    channelMessageEndUpload.foreach(channelMessageEndUpload ⇒ SendMessage(Message(data = JsonObject(
                                        "elapsedTime" → JsonString(DT(System.currentTimeMillis() - startTime).toString),
                                        "fileName" → JsonString(fi.get.getName),
                                        "fileSize" → JsonLong(fi.get.getSize)
                                    ), channels = channelMessageEndUpload)))

                                    fi.foreach(_.delete())
                                    Out("Ok")
                                case Failure(e) ⇒

                                    channelMessageError.foreach(channelMessageError ⇒ SendMessage(Message(data = JsonObject("message" → JsonString(e.getMessage), "stack" → JsonString(e.getStackTrace().mkString("", EOL, EOL))), channels = channelMessageError)))
                                    OutFailure(e)
                            }
                    }
                }
                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    }

    @RSTransfer(urlPattern = "/logic/arx_attatch/StopUpload")
    class StopUpload(val request: HttpServletRequest, val response: HttpServletResponse, val servletContext: ServletContext) extends SessionContextSupport with ServletActorDyn {

        val requestData = new DSRequestDyn(request)
        val connection = oraclePool.getConnection()

        logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"


        def receive = {
            case GetData => {
                def recStatus(status: Long, id: Long) = {
                    prepareStatement(connection, "update arx_attatch set status = ?, attfile = null where id = ?") {
                        preparedStatement ⇒
                            preparedStatement.setLong(1, status)
                            preparedStatement.setLong(2, id)
                            preparedStatement.executeUpdate()
                    }
                }

                val requestData = new DSRequestDyn(request)
                recStatus(requestData.getLong("status"), requestData.getLong("id"))

                OutOk
                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    }
}
