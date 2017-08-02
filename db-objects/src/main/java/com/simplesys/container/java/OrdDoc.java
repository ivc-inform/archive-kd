package com.simplesys.container.java;

import com.simplesys.container.Helper;
import oracle.jdbc.OracleClob;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleStruct;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.NUMBER;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;

public class OrdDoc implements OracleData, OracleDataFactory {

  public OrdSource source;
  public CHAR format;
  public CHAR mimeType;
  public NUMBER contentLength;
  public OracleClob comments;

  static final OrdDoc _ordDoc = new OrdDoc();

  @Override
  public String toString() {
    try {
      return "source: {" + source.toString() + "}, format: " + Helper.asString(format) + ", mimeType: " + Helper.asString(mimeType) + ", contentLength: " + Helper.asBigDecimal(contentLength).toString() + ", comments: " + Helper.clobToString(comments);
    } catch (SQLException e) {
      return e.getMessage();
    }

  }

  public OrdSource getSource() {
    return this.source;
  }

  public static OracleDataFactory getOracleDataFactory() {
    return _ordDoc;
  }

  public OrdDoc() {
  }

  public OrdDoc(OrdSource source,
                CHAR format,
                CHAR mimeType,
                NUMBER contentLength,
                OracleClob comments) {
    this.source = source;
    this.format = format;
    this.mimeType = mimeType;
    this.contentLength = contentLength;
    this.comments = comments;
  }


  @Override
  public Object toJDBCObject(Connection connection) throws SQLException {
    Object[] attributes = {source, format, mimeType, contentLength, comments};
    Struct struct = connection.createStruct("ORDSYS.ORDDOC", attributes);
    return struct;
  }

  @Override
  public OracleData create(Object o, int i) throws SQLException {
    if (o == null) return null;

    Object[] attributes = ((OracleStruct) o).getOracleAttributes();
    Object[] attributesOrdSource = ((OracleStruct) attributes[0]).getAttributes();

    return new OrdDoc(
            (OrdSource) new OrdSource(
                    (BLOB) attributesOrdSource[0],
                    (String) attributesOrdSource[1],
                    (String) attributesOrdSource[2],
                    (String) attributesOrdSource[3],
                    (Timestamp) attributesOrdSource[4],
                    (NUMBER) attributesOrdSource[5]
            ),
            (CHAR) attributes[1],
            (CHAR) attributes[2],
            (NUMBER) attributes[3],
            (OracleClob) attributes[4]
    );
  }
}
