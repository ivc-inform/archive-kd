package com.simplesys.container.scala

import com.simplesys.oracle.pool.OraclePoolDataSource

class TestRecord extends App {
    val ds = new OraclePoolDataSource("db-connection-stack.prod.oraclcePoolDataSource")
    ds.getConnection()
    println("Hello")
}
