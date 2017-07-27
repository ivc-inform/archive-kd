package com.simplesys.container;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.NUMBER;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

public class OrdDoc implements OracleData, OracleDataFactory {

  OrdSource source;
  CHAR format;
  CHAR mimeType;
  NUMBER contentLength;
  CLOB comments;

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
    return new OrdDoc(
            (OrdSource) attributes[0],
            (CHAR) attributes[1],
            (CHAR) attributes[2],
            (NUMBER) attributes[3],
            (CLOB) attributes[4]
    );
  }
}
