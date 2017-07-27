package com.simplesys.container;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;

public class OrdDoc implements OracleData, OracleDataFactory {

  OrdSource source;
  CHAR format;
  CHAR mimeType;
  NUMBER contentLength;
  CLOB comments;

  static final OrdDoc _ordDoc = new OrdDoc();

  public OrdSource getSource() {
    return this.source;
  }

  public String getFormat() throws SQLException {
    return Helper.asString(this.format);
  }

  public String getMimeType() throws SQLException {
    return Helper.asString(this.mimeType);
  }

  public BigDecimal getContentLength() throws SQLException {
    return NUMBER.toBigDecimal(this.contentLength.getBytes());
  }

  public String getComments() throws SQLException {
    return Helper.clobToString(this.comments);
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
                CLOB comments) {
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

    Object[] attributes = ((STRUCT) o).getOracleAttributes();
    Object[] attributesOrdSource = ((STRUCT) attributes[0]).getAttributes();

    BLOB a = (BLOB) attributesOrdSource[0];
    String b = (String) attributesOrdSource[1];
    String c = (String) attributesOrdSource[2];
    String d = (String) attributesOrdSource[3];
    Timestamp e = (Timestamp) attributesOrdSource[4];
    NUMBER f = (NUMBER) attributesOrdSource[5];

    return new OrdDoc(
            (OrdSource) new OrdSource(
                    a,
                    b,
                    c,
                    d,
                    e,
                    f
            ),
            (CHAR) attributes[1],
            (CHAR) attributes[2],
            (NUMBER) attributes[3],
            (CLOB) attributes[4]
    );
  }
}
