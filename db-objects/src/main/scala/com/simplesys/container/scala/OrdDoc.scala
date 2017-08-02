package com.simplesys.container.scala

import com.simplesys.container.Helper
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}
import com.simplesys.container.scala.OrdSource._
import oracle.jdbc.OracleConnection
import oracle.sql.{CLOB, NUMBER}

object OrdDoc {
    implicit def JOrdSource2SOrdSource(ordDoc: JOrdDoc): OrdDoc = new OrdDoc {
        override val source: Option[OrdSource] = Option(ordDoc.source).map(OrdSource.JOrdSource2SOrdSource(_))
        override val format: Option[String] = Option(ordDoc.format).map(Helper.asString(_))
        override val mimeType: Option[String] = Option(ordDoc.mimeType).map(Helper.asString(_))
        override val contentLength: Option[BigDecimal] = Option(ordDoc.contentLength).map(Helper.asBigDecimal(_))
        override val comments: Option[String] = Option(Helper.clobToString(ordDoc.comments))
    }

    implicit def SOrdSource2SOrdSource(ordDoc: OrdDoc)(implicit connection: OracleConnection): JOrdDoc = {
        val res = new JOrdDoc
        ordDoc.source.foreach(res.source = _)
        ordDoc.format.foreach(format ⇒ res.format = Helper.asCHAR(format))
        ordDoc.mimeType.foreach(mimeType ⇒ res.format = Helper.asCHAR(mimeType))
        ordDoc.contentLength.foreach(contentLength ⇒ res.contentLength = new NUMBER(contentLength.bigDecimal))
        ordDoc.comments.foreach(comment ⇒ res.comments = new CLOB(connection, comment.map(_.toByte).toArray))
        res
    }
}

trait OrdDoc {
    val source: Option[OrdSource]
    val format: Option[String]
    val mimeType: Option[String]
    val contentLength: Option[BigDecimal]
    val comments: Option[String]
}
