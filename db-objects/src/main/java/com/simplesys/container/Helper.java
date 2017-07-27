package com.simplesys.container;

import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.NUMBER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Helper {
  public static String clobToString(CLOB data) {
    StringBuilder sb = new StringBuilder();
    try {
      BufferedReader br = new BufferedReader(data.getCharacterStream());

      String line;
      while (null != (line = br.readLine())) {
        sb.append(line);
      }
      br.close();
    } catch (SQLException e) {
      // handle this exception
    } catch (IOException e) {
      // handle this exception
    }
    return sb.toString();
  }

  public static String asString(CHAR ch) throws SQLException {
    if (ch == null)
      return "null";
    else
      return ch.getString();
  }

  public static BigDecimal asBigDecimal(NUMBER n) throws SQLException {
    if (n == null)
      return new BigDecimal("-99999999999999999999999999999999999999999999999999");
    else
      return NUMBER.toBigDecimal(n.toBytes());
  }
}
