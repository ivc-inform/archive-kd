package com.simplesys.container.scala

import java.time.LocalDateTime

import com.simplesys.container.Helper
import com.simplesys.container.java.{OrdSource ⇒ JOrdSource}
import oracle.jdbc.OracleBlob

object OrdSource {
    def JOrdSource2SOrdSource(ordSource: JOrdSource): OrdSource = new OrdSource {
        override val srcName: Option[String] = Option(ordSource.srcName)
        override val srcLocation: Option[String] = Option(ordSource.srcLocation)
        override val updateTime: Option[LocalDateTime] = Option(ordSource.updateTime).map(_.toLocalDateTime)
        override val local: Option[BigDecimal] = Option(Helper.asBigDecimal(ordSource.local))
        override val srcType: Option[String] = Option(ordSource.srcType)
        override val localData: Option[OracleBlob] = Option(ordSource.localData)
    }
}

trait OrdSource {
    val localData: Option[OracleBlob]
    val srcType: Option[String]
    val srcLocation: Option[String]
    val srcName: Option[String]
    val updateTime: Option[LocalDateTime]
    val local: Option[BigDecimal]
}
