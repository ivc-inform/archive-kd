package com.simplesys.container

import java.sql.Connection

import oracle.sql._


object ORDDOC {
    val _ordDocFactory = ORDDOC()

    def apply(): ORDDOC = new ORDDOC {
        override val source: ORDSOURCE = null
        override val format: CHAR = null
        override val mimeType: CHAR = null
        override val contentLength: Int = null
        override val comments: CLOB = null
    }

    def apply(_source: ORDSOURCE, _format: CHAR, _mimeType: CHAR, _contentLength: Int, _comments: CLOB): ORDDOC = new ORDDOC {
        override val source: ORDSOURCE = _source
        override val format: CHAR = _format
        override val mimeType: CHAR = _mimeType
        override val contentLength: Int = _contentLength
        override val comments: CLOB = _comments
    }
}

abstract class ORDDOC extends ORAData with ORADataFactory {

    val source: ORDSOURCE
    val format: CHAR
    val mimeType: CHAR
    val contentLength: Int
    //    -- a place holder for annotations stored in the above attributes +
    //    -- anything which is not covered by the above fields, in a XML format.
    val comments: CLOB

    override def toDatum(connection: Connection): Datum = {
        val sd = StructDescriptor.createDescriptor("ORDSYS.ORDDOC", connection)
        val attributes = Array(source, format, mimeType, contentLength, comments)
        new STRUCT(sd, connection, attributes.map(_.asInstanceOf[Object]))
    }

    override def create(datum: Datum, i: Int): ORAData = {
        if (datum == null)
            null
        else {
            val attributes = datum.asInstanceOf[STRUCT].getOracleAttributes
            ORDDOC(attributes(0).asInstanceOf[ORDSOURCE], attributes(1).asInstanceOf[CHAR], attributes(2).asInstanceOf[CHAR], attributes(3).asInstanceOf[Int], attributes(4).asInstanceOf[CLOB])
        }
    }
}
