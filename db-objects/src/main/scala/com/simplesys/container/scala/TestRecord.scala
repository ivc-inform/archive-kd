package com.simplesys.container.scala

import java.io.{File, FileInputStream}

import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.container.java.{JOrdDoc ⇒ JOrdDoc}
import OrdDoc._
import oracle.jdbc.OracleConnection

object TestRecord extends App {
    val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")
    implicit val connection = ds.getConnection().asInstanceOf[OracleConnection]

    val idAttatch = 136

    GetAttFile.getOrdDoc(idAttatch).foreach {
        item ⇒
            val ordDoc: JOrdDoc = item
            //val file = new File(".idea/inspectionProfiles/Project_Default.xml")
            val file = new File("/home/uandrew/2TB/andrew/MOVIES/Red_hot.mkv")
            val fileInputStream = new FileInputStream(file)

            println(ordDoc.toString)
            (new RecorderOrdDoc (Some(idAttatch), None)).writeOrdDoc(fileInputStream, file.getName, "MOVIE/mkv")
    }
    println("Hello")
}
