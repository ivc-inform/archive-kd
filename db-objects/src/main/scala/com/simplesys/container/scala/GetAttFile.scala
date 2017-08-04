package com.simplesys.container.scala

import java.sql.Connection

import com.simplesys.container.java.{JOrdDoc ⇒ JOrdDoc}
import com.simplesys.jdbc.control.SessionStructures.prepareStatement
import oracle.jdbc.{OracleConnection, OracleResultSet}

object GetAttFile {
    def getOrdDoc(id: BigDecimal)(implicit oracleConnection: Connection): Option[OrdDoc] = {

        val selectSQL = "select ATTFILE from ARX_ATTATCH where ID = ?"

        prepareStatement(oracleConnection, selectSQL) {
            preparedStatement ⇒
                preparedStatement.setBigDecimal(1, id.bigDecimal)

                val ors = preparedStatement.executeQuery().asInstanceOf[OracleResultSet]

                if (ors.next())
                    Option(ors.getORAData(1, JOrdDoc.getOracleDataFactory()).map(ordJDoc ⇒ ordJDoc: OrdDoc)
                else
                    None
        }
    }

    def getOrdDocs(ids: BigDecimal*)(implicit oracleConnection: OracleConnection): Seq[OrdDoc] = ids.map(getOrdDoc).filter(_.nonEmpty).map(_.get)
}
