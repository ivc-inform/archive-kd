package com.simplesys.container

import java.sql.Connection
import oracle.sql.STRUCT
import oracle.sql.StructDescriptor
import scala.collection.JavaConverters._

import oracle.sql._

object ORDSOURCE {
    val _openSourceFactory = ORDSOURCE()

    def apply(): ORDSOURCE = new ORDSOURCE {
        override val localData: BLOB = null
        override val srcType: CHAR = null
        override val srcLocation: CHAR = null
        override val srcName: CHAR = null
        override val updateTime: DATE = null
        override val local: NUMBER = null
    }

    def apply(_localData: BLOB, _srcType: CHAR, _srcLocation: CHAR, _srcName: CHAR, _updateTime: DATE, _local: NUMBER): ORDSOURCE = new ORDSOURCE {
        override val localData: BLOB = _localData
        override val srcType: CHAR = _srcType
        override val srcLocation: CHAR = _srcLocation
        override val srcName: CHAR = _srcName
        override val updateTime: DATE = _updateTime
        override val local: NUMBER = _local
    }
}

abstract class ORDSOURCE extends ORAData with ORADataFactory {

    //    ------------
    //    -- Attributes
    //    -------------
    //
    //      -- storage for data within oracle. Every operation
    //      -- for the data stored in this field will be under
    //      -- the control of transaction within which the
    //      -- methods are called. This means that all the changes can
    //      -- either be commited or rolled back as desired by the
    //      -- user
    val localData: BLOB

    //      --
    //      -- srcType, srcLocation and srcName are limited to 4K
    //      -- due to system limitation on the size of varchar2 field
    //      --

    val srcType: CHAR
    val srcLocation: CHAR
    val srcName: CHAR

    //      -- updateTime maintains the time when the users updated either
    //      -- source or the object containing the source last. This attribute
    //      -- is not updated when import is called, but importFrom updates
    //      -- this method since the source information is changed.
    //      --
    //      -- For recoding updates when the top level objevt changes, users
    //      -- must call set method at appropriate time.

    val updateTime: DATE

    //      -- 1 or NULL means data is in LOB
    //      -- 0 means that the data is in external sources

    val local: NUMBER

    override def toDatum(connection: Connection): Datum = {

        val sd = StructDescriptor.createDescriptor("ORDSYS.ORDSOURCE", connection)
        val attributes = Array(localData, srcType, srcLocation, srcName, updateTime, local)
        new STRUCT(sd, connection, attributes.map(_.asInstanceOf[Object]))
    }

    override def create(datum: Datum, i: Int): ORAData = {
        if (datum == null)
            null
        else {
            val attributes = datum.asInstanceOf[STRUCT].getOracleAttributes
            ORDSOURCE(attributes(0).asInstanceOf[BLOB], attributes(1).asInstanceOf[CHAR], attributes(2).asInstanceOf[CHAR], attributes(3).asInstanceOf[CHAR], attributes(4).asInstanceOf[DATE], attributes(5).asInstanceOf[NUMBER])
        }
    }
}
