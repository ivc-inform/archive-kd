package com.simplesys.container;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

public class OrdSource implements OracleData, OracleDataFactory {

  BLOB localData;
  CHAR srcType;
  CHAR srcLocation;
  CHAR srcName;
  DATE updateTime;
  NUMBER local;

  static final OrdSource _ordSource = new OrdSource();

  public static OracleDataFactory getOracleDataFactory()
    {
      return _ordSource;
    }

  public OrdSource() {
  }

  public OrdSource(BLOB localData,
                   CHAR srcType,
                   CHAR srcLocation,
                   CHAR srcName,
                   DATE updateTime,
                   NUMBER local) {
    this.localData = localData;
    this.srcType = srcType;
    this.srcLocation = srcLocation;
    this.srcName = srcName;
    this.updateTime = updateTime;
    this.local = local;
  }

  @Override
  public Object toJDBCObject(Connection connection) throws SQLException {
    Object[] attributes = {localData, srcType, srcLocation, srcName, updateTime, local};
    return connection.createStruct("ORDSYS.ORDSOURCE", attributes);
  }

  @Override
  public OracleData create(Object o, int i) throws SQLException {
    if (o == null)
      return null;

    Object[] attributes = ((STRUCT) o).getOracleAttributes();
    return new OrdSource(
            (BLOB) attributes[0],
            (CHAR) attributes[1],
            (CHAR) attributes[2],
            (CHAR) attributes[3],
            (DATE) attributes[4],
            (NUMBER) attributes[5]);
  }
}
