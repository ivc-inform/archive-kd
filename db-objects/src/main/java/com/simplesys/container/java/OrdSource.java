package com.simplesys.container.java;

import com.simplesys.container.Helper;
import oracle.jdbc.OracleBlob;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleStruct;
import oracle.sql.NUMBER;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrdSource implements OracleData, OracleDataFactory {

  public OracleBlob localData;
  public String srcType;
  public String srcLocation;
  public String srcName;
  public Timestamp updateTime;
  public NUMBER local;

  static final OrdSource _ordSource = new OrdSource();

  public static OracleDataFactory getOracleDataFactory() {
    return _ordSource;
  }

  public OrdSource() {
  }

  @Override
  public String toString() {
    try {
      return "localData: BLOB" + " ,srcType: " + srcType + " ,srcLocation: " + srcLocation + " ,srcName: " + srcName + " ,updateTime: " + updateTime.toString() + " ,local: " + Helper.asBigDecimal(local);
    } catch (SQLException e) {
      return e.getMessage();
    }
  }

  public OrdSource(OracleBlob localData,
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

    Object[] attributes = ((OracleStruct) o).getOracleAttributes();
    return new OrdSource(
            (OracleBlob) attributes[0],
            (String) attributes[1],
            (String) attributes[2],
            (String) attributes[3],
            (Timestamp) attributes[4],
            (NUMBER) attributes[5]);
  }
}
