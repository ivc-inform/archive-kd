package com.simplesys.container.scala

import oracle.jdbc.OracleResultSet
import oracle.jdbc.driver.OracleConnection
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}

object GetAttFile {
    def getOrdDoc(id: BigDecimal)(implicit oracleConnection: OracleConnection): Option[OrdDoc] = {

        val selectSQL = "select ATTFILE from ARX_ATTATCH where ID = ?"
        val preparedStatement = oracleConnection.prepareStatement(selectSQL)
        preparedStatement.setBigDecimal(1, id.bigDecimal)
        val ors = preparedStatement.executeQuery().asInstanceOf[OracleResultSet]

        if (ors.next())
            Option(ors.getObject(1, JOrdDoc.getOracleDataFactory()).asInstanceOf[JOrdDoc]).map(ordJDoc ⇒ ordJDoc: OrdDoc)
        else
            None
    }

    def getOrdDocs(ids: BigDecimal*)(implicit oracleConnection: OracleConnection): Seq[OrdDoc] = ids.map(getOrdDoc).filter(_.nonEmpty).map(_.get)
}
