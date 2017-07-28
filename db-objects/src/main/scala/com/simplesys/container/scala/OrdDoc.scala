package com.simplesys.container.scala

import java.time.LocalDateTime

import com.simplesys.container.Helper
import oracle.jdbc.{OracleBlob, OracleClob}
import com.simplesys.container.java.{OrdDoc ⇒ JOrdDoc}
import OrdSource._

object OrdDoc {
    implicit def JOrdSource2SOrdSource(ordDoc: JOrdDoc): OrdDoc = new OrdDoc {
        override val source: Option[OrdSource] = Option(ordDoc.source).map(OrdSource.JOrdSource2SOrdSource(_))
        override val format: Option[String] = Option(Helper.asString(ordDoc.format))
        override val mimeType: Option[String] = Option(Helper.asString(ordDoc.mimeType))
        override val contentLength: Option[BigDecimal] = Option(Helper.asBigDecimal(ordDoc.contentLength))
        override val comments: Option[String] = Option(Helper.clobToString(ordDoc.comments))
    }
}

trait OrdDoc {
    val source: Option[OrdSource]
    val format: Option[String]
    val mimeType: Option[String]
    val contentLength: Option[BigDecimal]
    val comments: Option[String]
}
