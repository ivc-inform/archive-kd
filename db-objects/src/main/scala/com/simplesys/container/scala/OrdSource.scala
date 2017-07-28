package com.simplesys.container.scala

import java.sql.Timestamp
import java.time.LocalDateTime

import com.simplesys.container.Helper
import com.simplesys.container.java.{OrdSource ⇒ JOrdSource}
import oracle.jdbc.OracleBlob
import oracle.sql.NUMBER

object OrdSource {

    implicit def JOrdSource2SOrdSource(ordSource: JOrdSource): OrdSource = new OrdSource {
        override val srcName: Option[String] = Option(ordSource.srcName)
        override val srcLocation: Option[String] = Option(ordSource.srcLocation)
        override val updateTime: Option[LocalDateTime] = Option(ordSource.updateTime).map(_.toLocalDateTime)
        override val local: Option[BigDecimal] = Option(Helper.asBigDecimal(ordSource.local))
        override val srcType: Option[String] = Option(ordSource.srcType)
        override val localData: Option[OracleBlob] = Option(ordSource.localData)
    }

    implicit def SOrdSource2JOrdSource(ordSource: OrdSource): JOrdSource = {
        val res = new JOrdSource {}
        ordSource.srcName.foreach(res.srcName = _)
        ordSource.srcLocation.foreach(res.srcLocation = _)
        ordSource.updateTime.foreach(localDateTime ⇒ res.updateTime = Timestamp.valueOf(localDateTime))
        ordSource.local.foreach(bigDeecimal ⇒ res.local = new NUMBER(bigDeecimal))
        ordSource.srcType.foreach(res.srcType = _)
        ordSource.localData.foreach(res.localData = _)
        res
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
