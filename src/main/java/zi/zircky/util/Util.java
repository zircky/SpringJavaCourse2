package zi.zircky.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
  private static String url = "jdbc:mysql://localhost:3306/mydbtest";
  private static String username = "root";
  private static String password = "zIRCKY159";

  public static Connection getConnection() {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }


}
