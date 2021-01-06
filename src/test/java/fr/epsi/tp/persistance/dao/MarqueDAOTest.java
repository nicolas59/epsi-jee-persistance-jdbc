package fr.epsi.tp.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.epsi.tp.persistance.ConnectionFactory;
import fr.epsi.tp.persistance.bean.Marque;

public class MarqueDAOTest {

  private Connection conn;

  private MarqueDAO marqueDao = new MarqueDAO();

  @Before
  public void beforeTest() throws Exception {
    this.conn = ConnectionFactory.getInstance()
      .getConnection();
    SQLUtils.initDatabase(this.conn);
    DatabaseConnection dbConn = new DatabaseConnection(conn);
    IDataSet dataSet = new FlatXmlDataSetBuilder().build(MarqueDAOTest.class.getResourceAsStream("/dataset.xml"));
    DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
  }

  @Test
  public void should_return_all_marques() throws SQLException {
    Collection<Marque> marques = marqueDao.findAll();
    assertThat(marques).isNotNull();
    assertThat(marques).isNotEmpty();
    assertThat(marques.size()).isEqualTo(2);
  }

  @Test
  public void should_provide_samsung() throws SQLException {

    Marque ret = marqueDao.findById(1L);
    assertThat(ret).isNotNull();
    assertThat(ret.getIdentifier()).isNotNull();
    assertThat(ret.getLibelle()).isEqualTo("Samsung");
  }
  
  @Test
  public void should_add_marque() throws SQLException {

    Marque marque = new Marque();
    marque.setLibelle("Huawei");

    Marque ret = marqueDao.create(marque);
    assertThat(ret).isNotNull();
    assertThat(ret.getIdentifier()).isNotNull();
  }

  @Test
  public void should_update_marque() throws SQLException {
    Marque marque = marqueDao.findAll()
      .stream()
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Object non trouvé"));

    marque.setLibelle("Nouvelle marque");
    marqueDao.update(marque);

    Marque marquetModifie = marqueDao.findById(marque.getIdentifier());

    assertThat(marquetModifie).isNotNull();
    assertThat(marquetModifie.getLibelle()).isEqualTo("Nouvelle marque");

  }

  @After
  public void after() throws SQLException {
    this.conn.close();
  }
}
