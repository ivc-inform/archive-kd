package com.simplesys.container.scala

import java.io.InputStream
import java.time.{Instant, LocalDateTime, ZoneId}

import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import oracle.jdbc.{OracleBlob, OracleConnection}
import oracle.jdbc.dcn.DatabaseChangeRegistration
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}

class RecorderOrdDoc(idAttatch: Option[Long], dcr: Option[DatabaseChangeRegistration])(implicit connection: OracleConnection) {
    def writeOrdDoc(inputStream: InputStream, fiName: String, fiContentType: String, fiSize: Long)(implicit connection: OracleConnection): Unit = {
        idAttatch.foreach {
            idAttatch ⇒
                val blob = connection.createBlob().asInstanceOf[OracleBlob]
                //blob.getBinaryStream(1).read(inputStream.)

                def getEmptySource =
                    new OrdSource {
                        override val srcName: Option[String] = Some(fiName)
                        override val srcLocation: Option[String] = None
                        override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
                        override val local: Option[BigDecimal] = None
                        override val srcType: Option[String] = Some("FILE")
                        override val localData: Option[OracleBlob] = Some(blob)
                    }

                val ordDoc: JOrdDoc = GetAttFile.getOrdDoc(idAttatch) match {
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

                prepareStatement(connection, "UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?") {
                    preparedStatement ⇒
                        preparedStatement.setObject(1, ordDoc)
                        preparedStatement.setLong(2, idAttatch)
                        preparedStatement.executeUpdate()

                        dcr.foreach(connection unregisterDatabaseChangeNotification _)
                }
        }
    }
}
