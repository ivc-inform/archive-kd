package com.simplesys.container.scala

import java.io.{File, FileInputStream}

import com.simplesys.oracle.pool.OraclePoolDataSource
import oracle.jdbc.OracleConnection
import oracle.ord.im.OrdDoc

object TestRecord extends App {
    val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")
    implicit val connection = ds.getConnection().asInstanceOf[OracleConnection]

    val idAttatch = 134

    GetAttFile.getOrdDoc(idAttatch).foreach {
        item ⇒
            val ordDoc: OrdDoc = item
            val file = new File(".idea/inspectionProfiles/Project_Default.xml")
            val fileInputStream = new FileInputStream(file)
            println(ordDoc.toString)

            //(new RecorderOrdDoc (Some(idAttatch), None)).writeOrdDoc(fileInputStream, file.getName, "application/xml")

    }
    println("Hello")
}
