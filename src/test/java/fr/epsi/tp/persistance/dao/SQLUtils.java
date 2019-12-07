package fr.epsi.tp.persistance.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class SQLUtils {

  private static final String SQL_FILE = "/init-db-postgresql.sql";

  public static void initDatabase(Connection conn) {
    String lines;
    ResultSet rs = null;
    try {
      DatabaseMetaData metadata = conn.getMetaData();
      rs = metadata.getTables(null, null, "MARQUE", null);
      if(rs.next()) {
        System.out.println("Tables already created");
        return;
      }
      lines = Files.readAllLines(Paths.get(CommandeDAOTest.class.getResource(SQL_FILE)
        .toURI()))
        .stream()
        .collect(Collectors.joining());

      Statement st =null;
      for (String line : lines.split(";")) {
        st = conn.createStatement();
        st.executeUpdate(line);
        st.close();
      }
    } catch (IOException | URISyntaxException | SQLException e) {
      e.printStackTrace();
    }finally {
      close(rs);
    }
  }
  
  public static void close(AutoCloseable close) {
    if(close != null) {
      try {
        close.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
