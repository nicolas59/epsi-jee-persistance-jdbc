package fr.epsi.tp.persistance;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

  private static ConnectionFactory INSTANCE = new ConnectionFactory();

  private ConnectionFactory() {
    // TODO Charger la class
  }

  public Connection getConnection(String url, String login, String password) throws SQLException {
    // TODO A implementer
    return null;
  }

  public Connection getConnection() throws SQLException {
    // TODO A implementer
    return null;

  }

  public static ConnectionFactory getInstance() {
    return INSTANCE;
  }
}
