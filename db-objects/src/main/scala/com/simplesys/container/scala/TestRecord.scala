package com.simplesys.container.scala

import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}
import OrdDoc._
import oracle.jdbc.OracleConnection

object TestRecord extends App {
    val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")
    implicit val connection = ds.getConnection().asInstanceOf[OracleConnection]

    val idAttatch = 134

    GetAttFile.getOrdDoc(idAttatch).foreach {
        item ⇒
            val ordDoc: JOrdDoc = item
            println(ordDoc.toString)
    }
    println("Hello")
}
