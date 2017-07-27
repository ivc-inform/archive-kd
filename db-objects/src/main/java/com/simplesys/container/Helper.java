package com.simplesys.container;

import oracle.sql.CHAR;
import oracle.sql.CLOB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class Helper {
  public static String clobToString(CLOB data) {
    StringBuilder sb = new StringBuilder();
    try {
      Reader reader = data.getCharacterStream();
      BufferedReader br = new BufferedReader(reader);

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
}
