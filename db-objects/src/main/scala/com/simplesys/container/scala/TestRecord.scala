package com.simplesys.container.scala

import java.io.{File, FileInputStream}

import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.container.java.{JOrdDoc ⇒ JOrdDoc}
import OrdDoc._
import oracle.jdbc.OracleConnection

object TestRecord extends App {
    val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")
    implicit val connection = ds.getConnection().asInstanceOf[OracleConnection]

    val idAttatch = 134

    GetAttFile.getOrdDoc(idAttatch).foreach {
        item ⇒
            val ordDoc: JOrdDoc = item
            val file = new File(".idea/inspectionProfiles/Project_Default.xml")
            val fileInputStream = new FileInputStream(file)

            //(new RecorderOrdDoc (Some(idAttatch), None)).writeOrdDoc(fileInputStream, file.getName, "application/xml")
            println(ordDoc.toString)
    }
    println("Hello")
}
