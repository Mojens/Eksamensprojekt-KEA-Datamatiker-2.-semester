package com.example.eksamensprojektkeadatamatiker2semester.Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectionManager {

  private static Connection connection;

  public static Connection getConnection() {
    String URL = System.getenv("url");
    String UID = System.getenv("user");
    String PWD = System.getenv("pwd");

    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, UID, PWD);
        System.out.println("Der er forbundet til din Database");
      } catch (Exception e) {
        System.out.println("Der blev ikke forbundet til din Database: ");
        e.printStackTrace();
      }
    }
    return connection;
  }

}
