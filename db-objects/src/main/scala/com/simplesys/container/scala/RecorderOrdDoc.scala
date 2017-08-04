package com.simplesys.container.scala

import java.io.InputStream
import java.sql.{CallableStatement, Timestamp, Types}
import java.time.{Instant, LocalDateTime, ZoneId}

import com.simplesys.jdbc.control.SessionStructures.callableStatement
import oracle.jdbc.dcn.DatabaseChangeRegistration
import oracle.jdbc._
import oracle.sql.CLOB
import org.apache.commons.io.IOUtils._

class RecorderOrdDoc(idAttatch: Option[BigDecimal], dcr: Option[DatabaseChangeRegistration] = None)(implicit connection: OracleConnection) {
    def writeOrdDoc(inputStream: InputStream, fiName: String, fiContentType: String)(implicit connection: OracleConnection): Unit = {
        idAttatch.foreach {
            idAttatch ⇒
                val blob = connection.createBlob().asInstanceOf[OracleBlob]

                val fiSize = copyLarge(inputStream, blob.setBinaryStream(1))

                def getEmptySource: OrdSource =
                    new OrdSource {
                        override val srcName: Option[String] = Some(fiName)
                        override val srcLocation: Option[String] = None
                        override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                        override val local: Option[BigDecimal] = None
                        override val srcType: Option[String] = Some("FILE")
                        override val localData: Option[OracleBlob] = Some(blob)
                    }


                val ordDoc: OrdDoc = GetAttFile.getOrdDoc(idAttatch) match {
                    case Some(ordDoc) ⇒
                        val _source = ordDoc.source match {
                            case Some(source) ⇒
                                new OrdSource {
                                    override val srcName: Option[String] = Some(fiName)
                                    override val srcLocation: Option[String] = None
                                    override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                                    override val local: Option[BigDecimal] = source.local
                                    override val srcType: Option[String] = source.srcType
                                    override val localData: Option[OracleBlob] = Some(blob)
                                }
                            case None ⇒
                                getEmptySource
                        }

                        new OrdDoc {
                            override val comments: Option[String] = Some("Updated by UploadContainer !!")
                            override val format: Option[String] = ordDoc.format
                            override val source: Option[OrdSource] = Some(_source)
                            override val mimeType: Option[String] = Some(fiContentType)
                            override val contentLength: Option[BigDecimal] = Some(fiSize)
                        }

                    case None ⇒
                        new OrdDoc {
                            override val comments: Option[String] = Some("Inserted by UploadContainer !!")
                            override val format: Option[String] = None
                            override val source: Option[OrdSource] = Some(getEmptySource)
                            override val mimeType: Option[String] = Some(fiContentType)
                            override val contentLength: Option[BigDecimal] = Some(fiSize)
                        }
                }

                "begin recorddoc(source_srcname => ?, source_srclocation => ?, source_updatetime => ?, source_local => ?, source_srctype => ?,source_localdata => ?, orddoc_format => ?, orddoc_mimetype => ?, orddoc_contentlength => ?, orddoc_comments => ?, fid => ?); end;"

                callableStatement(connection, "begin recorddoc(source_srcname => ?, source_srclocation => ?, source_updatetime => ?, source_local => ?, source_srctype => ?,source_localdata => ?, orddoc_format => ?, orddoc_mimetype => ?, orddoc_contentlength => ?, fid => ?); end;") {
                    callableStatement ⇒
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
                                source.localData match {
                                    case Some(localData) ⇒
                                        callableStatement.setBlob(index, localData)
                                    case None ⇒
                                        callableStatement.setNull(index, Types.BLOB)
                                }
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

                        /*index += 1
                        ordDoc.comments match {
                            case Some(comments) ⇒
                                callableStatement.setClob(index, new CLOB(connection, comments.map(_.toByte).toArray))
                            case None ⇒
                                callableStatement.setClob(index, CLOB.getEmptyCLOB)
                        }*/

                        index += 1

                        callableStatement.setBigDecimal(index, idAttatch.bigDecimal)
                        callableStatement.executeUpdate()

                        dcr.foreach(connection unregisterDatabaseChangeNotification _)
                }
        }
    }
}
