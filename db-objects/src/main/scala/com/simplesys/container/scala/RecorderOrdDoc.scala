package com.simplesys.container.scala

import java.io.InputStream
import java.sql.{SQLException, Timestamp}

import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import oracle.jdbc.dcn.DatabaseChangeRegistration
import oracle.jdbc.{OracleConnection, OraclePreparedStatement, OracleResultSet}
import oracle.ord.im.OrdDoc
//import org.apache.commons.io.IOUtils._

class RecorderOrdDoc(idAttatch: Option[Long], dcr: Option[DatabaseChangeRegistration] = None)(implicit connection: OracleConnection) {


    def writeOrdDoc(inputStream: InputStream, fiName: String, fiContentType: String)(implicit connection: OracleConnection): Unit = {
        idAttatch.foreach {
            idAttatch ⇒
                println()
                prepareStatement(connection, "select ATTFILE from ARX_ATTATCH where ID = ?") {
                    preparedStatement ⇒
                        val oraclePreparedStatement = preparedStatement.asInstanceOf[OraclePreparedStatement]
                        oraclePreparedStatement.setLong(1, idAttatch)

                        val ors = oraclePreparedStatement.executeQuery().asInstanceOf[OracleResultSet]

                        if (ors.next()) {
                            val ordDoc = ors.getORAData(1, OrdDoc.getORADataFactory).asInstanceOf[OrdDoc]

                            ordDoc.deleteContent()
                            ordDoc.setLocal()
                            ordDoc.setUpdateTime(null.asInstanceOf[Timestamp])

                            //val length = copyLarge(inputStream, ordDoc.getBlobContent().setBinaryStream(1))

                            ordDoc.loadDataFromInputStream(inputStream)
                            ordDoc.setMimeType("application/xml")
                            ordDoc.setFormat("FILE")

                            prepareStatement(connection, "UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?") {
                                preparedStatement ⇒
                                    val oraclePreparedStatement = preparedStatement.asInstanceOf[OraclePreparedStatement]
                                    oraclePreparedStatement.setORAData(1, ordDoc)
                                    oraclePreparedStatement.setLong(2, idAttatch)
                                    oraclePreparedStatement.executeUpdate()

                                    dcr.foreach(connection unregisterDatabaseChangeNotification _)
                            }
                        }

                }


        }
    }

    def writeOrdDoc1(inputStream: InputStream, fiName: String, fiContentType: String)(implicit connection: OracleConnection): Unit = {
        val pstmt = connection.prepareStatement("select ATTFILE from ARX_ATTATCH where ID = ? FOR UPDATE").asInstanceOf[OraclePreparedStatement]
        pstmt.setLong(1, idAttatch.get)

        val rs = pstmt.executeQuery.asInstanceOf[OracleResultSet]
        if (rs.next() == false)
            throw new SQLException()
        else {
            val ordDoc = rs.getORAData(1, OrdDoc.getORADataFactory).asInstanceOf[OrdDoc]

            rs.close
            pstmt.close

            val pstmt1 = connection.prepareCall("UPDATE ARX_ATTATCH SET ATTFILE = ? WHERE ID = ?").asInstanceOf[OraclePreparedStatement]
            pstmt1.setLong(2, idAttatch.get)
            ordDoc.loadDataFromInputStream(inputStream)
            pstmt1.setORAData(1, ordDoc)
            pstmt1.execute
            pstmt1.close
        }
    }
}
