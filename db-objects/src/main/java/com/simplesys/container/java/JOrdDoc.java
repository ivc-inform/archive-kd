package com.simplesys.container.java;

import com.simplesys.container.Helper;
import com.simplesys.container.scala.OrdDoc;
import oracle.jdbc.OracleClob;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleStruct;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.NUMBER;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;

public class JOrdDoc implements OracleData, OracleDataFactory {
  
  public CHAR format;
//  public CHAR mimeType;
//  public NUMBER contentLength;
//  public OracleClob comments;
//  public JOrdSource source;

  static final JOrdDoc _ordDoc = new JOrdDoc();

  @Override
  public String toString() {
    try {
      //return "source: {" + source.toString() + "}, format: " + Helper.asString(format) + ", mimeType: " + Helper.asString(mimeType) + ", contentLength: " + Helper.asBigDecimal(contentLength).toString() + ", comments: " + Helper.clobToString(comments);
      return "format: " + Helper.asString(format);
    } catch (SQLException e) {
      return e.getMessage();
    }

  }

  public static OracleDataFactory getOracleDataFactory() {
    return _ordDoc;
  }

  public JOrdDoc() {
  }

  public JOrdDoc(CHAR format/*,
                 CHAR mimeType,
                 NUMBER contentLength,
                 OracleClob comments,
                 JOrdSource source*/) {
    this.format = format;
//    this.source = source;
//    this.mimeType = mimeType;
//    this.contentLength = contentLength;
//    this.comments = comments;
  }


  @Override
  public Object toJDBCObject(Connection connection) throws SQLException {
    Object[] attributes = {format/*, source, mimeType, contentLength, comments*/};
    Struct struct = connection.createStruct("ORDSYS.ORDDOC", attributes);
    return struct;
  }

  @Override
  public OracleData create(Object o, int i) throws SQLException {
    if (o == null) return null;

    Object[] attributes = ((OracleStruct) o).getOracleAttributes();
    Object[] attributesOrdSource = ((OracleStruct) attributes[0]).getAttributes();

    return new JOrdDoc(
            (CHAR) attributes[1]/*,
            (CHAR) attributes[2],
            (NUMBER) attributes[3],
            (OracleClob) attributes[4],
            (JOrdSource) new JOrdSource(
                    (BLOB) attributesOrdSource[0],
                    Helper.asCHAR((String) attributesOrdSource[1]),
                    Helper.asCHAR((String) attributesOrdSource[2]),
                    Helper.asCHAR((String) attributesOrdSource[3]),
                    (Timestamp) attributesOrdSource[4],
                    (NUMBER) attributesOrdSource[5]
            )*/
    );
  }
}
