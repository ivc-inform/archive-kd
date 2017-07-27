package com.simplesys.container;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;

public class OrdSource implements OracleData, OracleDataFactory {

  BLOB localData;
  String srcType;
  String srcLocation;
  String srcName;
  Timestamp updateTime;
  NUMBER local;

  static final OrdSource _ordSource = new OrdSource();

  public static OracleDataFactory getOracleDataFactory() {
    return _ordSource;
  }

  public OrdSource() {
  }

  @Override
  public String toString() {
    try {
      return "localData: BLOB" + " ,srcType: " + srcType + " ,srcLocation: " + srcLocation + " ,srcName: " + srcName + " ,updateTime: " + updateTime.toString() + " ,local: " + Helper.asBigDecimal(local).toString();
    } catch (SQLException e) {
      return e.getMessage();
    }
  }

  public OrdSource(BLOB localData,
                   String srcType,
                   String srcLocation,
                   String srcName,
                   Timestamp updateTime,
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
            (String) attributes[1],
            (String) attributes[2],
            (String) attributes[3],
            (Timestamp) attributes[4],
            (NUMBER) attributes[5]);
  }
}
