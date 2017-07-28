package com.simplesys.container.scala

import oracle.jdbc.OracleClob



trait OrdDoc {
    val source: OrdSource
    val format: String
    val mimeType: String
    val contentLength: BigDecimal
    val comments: OracleClob
}
