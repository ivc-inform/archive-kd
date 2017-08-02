package com.simplesys.container.scala

import java.io.InputStream
import java.time.{Instant, LocalDateTime, ZoneId}

import com.simplesys.container.java.{JOrdDoc, JOrdSource}
import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import oracle.jdbc.dcn.DatabaseChangeRegistration
import oracle.jdbc.{OracleBlob, OracleConnection}
import org.apache.commons.io.IOUtils._

class RecorderOrdDoc(idAttatch: Option[Long], dcr: Option[DatabaseChangeRegistration] = None)(implicit connection: OracleConnection) {
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
                        /*val _source = ordDoc.source match {
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
                        }*/

                        new OrdDoc {
                            override val format: Option[String] = ordDoc.format
//                            override val source: Option[OrdSource] = Some(_source)
//                            override val mimeType: Option[String] = Some(fiContentType)
//                            override val contentLength: Option[BigDecimal] = Some(fiSize)
//                            override val comments: Option[String] = Some("Updated by UploadContainer !!")
                        }

                    case None ⇒
                        new OrdDoc {
                            override val format: Option[String] = None
//                            override val source: Option[OrdSource] = Some(getEmptySource)
//                            override val mimeType: Option[String] = Some(fiContentType)
//                            override val contentLength: Option[BigDecimal] = Some(fiSize)
//                            override val comments: Option[String] = Some("Inserted by UploadContainer !!")
                        }
                }

                prepareStatement(connection, "UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?") {
                    preparedStatement ⇒
                        val jOrdDoc: JOrdDoc = ordDoc
                        preparedStatement.setObject(1, jOrdDoc)
                        preparedStatement.setLong(2, idAttatch)
                        preparedStatement.executeUpdate()

                        dcr.foreach(connection unregisterDatabaseChangeNotification _)
                }
        }
    }
}
