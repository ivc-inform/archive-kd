package com.simplesys.container.scala

import oracle.jdbc.OracleConnection
import oracle.jdbc.dcn.DatabaseChangeRegistration
import oracle.ord.im.OrdDoc

class RecorderOrdDoc(idAttatch: Option[Long], dcr: Option[DatabaseChangeRegistration] = None)(implicit connection: OracleConnection) {
    val ordDoc = OrdDoc.getORADataFactory.asInstanceOf[OrdDoc]
    ordDoc.getDataInStream


//    def writeOrdDoc(inputStream: InputStream, fiName: String, fiContentType: String)(implicit connection: OracleConnection): Unit = {
//        idAttatch.foreach {
//            idAttatch ⇒
//                val blob = connection.createBlob().asInstanceOf[OracleBlob]
//
//                val fiSize = copyLarge(inputStream, blob.setBinaryStream(1))
//
//                def getEmptySource: SOrdSource =
//                    new SOrdSource {
//                        override val srcName: Option[String] = Some(fiName)
//                        override val srcLocation: Option[String] = None
//                        override val updateTime: Option[LocalDateTime] = Some(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault).toLocalDateTime)
//                        override val local: Option[BigDecimal] = None
//                        override val srcType: Option[String] = Some("FILE")
//                        override val localData: Option[OracleBlob] = Some(blob)
//                    }
//
//
//                val ordDoc = new SOrdDoc {
//                    override val format: Option[String] = Some("f1")
//                    //                            override val source: Option[OrdSource] = Some(getEmptySource)
//                    //                            override val mimeType: Option[String] = Some(fiContentType)
//                    //                            override val contentLength: Option[BigDecimal] = Some(fiSize)
//                    //                            override val comments: Option[String] = Some("Inserted by UploadContainer !!")
//                }
//
//                prepareStatement(connection, "UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?") {
//                    preparedStatement ⇒
//                        val jOrdDoc: JOrdDoc = ordDoc
//                        val myMap = connection.getTypeMap
//                        myMap put ("ORDSYS.ORDDOC", classOf[JOrdDoc])
//                        connection setTypeMap myMap
//
//                        preparedStatement.setObject(1, jOrdDoc.toJDBCObject(connection))
//                        preparedStatement.setLong(2, idAttatch)
//                        preparedStatement.executeUpdate()
//
//                        dcr.foreach(connection unregisterDatabaseChangeNotification _)
//                }
//        }
//    }
}
