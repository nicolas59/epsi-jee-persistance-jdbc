package fr.epsi.tp.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
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
import fr.epsi.tp.persistance.bean.Produit;

public class ProduitDAOTest {

  private Connection conn;

  private ProduitDAO produitdao = new ProduitDAO();

  @Before
  public void beforeTest() throws Exception {
    this.conn = ConnectionFactory.getInstance()
      .getConnection();

    assert conn != null;
    SQLUtils.initDatabase(this.conn);
    DatabaseConnection dbConn = new DatabaseConnection(conn);
    IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProduitDAOTest.class.getResourceAsStream("/dataset.xml"));
    DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
  }

  @Test
  public void should_return_all_products() throws SQLException {
    Collection<Produit> produits = produitdao.findAll();
    assertThat(produits).isNotNull();
    assertThat(produits).isNotEmpty();
    assertThat(produits.size()).isEqualTo(41);
  }

  @Test
  public void should_add_product() throws SQLException {
    Produit produit = new Produit();
    produit.setLibelle("Samsung A5 2016");
    produit.setDescription("Un peu depassé");

    Marque marque = new Marque();
    marque.setLibelle("Samsung");
    marque.setIdentifier(1L);
    produit.setMarque(marque);
    produit.setPrix(BigDecimal.valueOf(50));

    Produit ret = produitdao.create(produit);
    assertThat(ret).isNotNull();
    assertThat(ret.getIdentifier()).isNotNull();
  }

  @Test
  public void should_update_product() throws SQLException {

    Produit produit = produitdao.findAll()
      .stream()
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Object non trouvé"));

    produit.setLibelle("Produit reconditionné");
    produitdao.update(produit);

    Produit produitModifie = produitdao.findById(produit.getIdentifier());

    assertThat(produitModifie).isNotNull();
    assertThat(produitModifie.getLibelle()).isEqualTo("Produit reconditionné");

  }

  @Test
  public void should_find_product_by_libelle() throws SQLException {
    Collection<Produit> produits = produitdao.findByLibelle("Pixel 2 XL");
    assertThat(produits).isNotNull();
    assertThat(produits).isNotEmpty();
    assertThat(produits.size()).isEqualTo(1);

    Produit prod = produits.stream()
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Object non trouvé"));
    assertThat(prod.getDescription()).isEqualTo(
        "Google Pixel 2 XL Android smartphone. Announced Oct 2017. Features 6.0 P-OLED display, Snapdragon 835 chipset, 12.2 MP primary camera, 8 MP front camera, 3520 mAh battery, 128 GB storage, 4 GB RAM, Corning Gorilla Glass 5.");
    assertThat(prod.getMarque()).isNotNull();
    assertThat(prod.getMarque()
      .getLibelle()).isEqualTo("Google");
    assertThat(prod.getPrix()).isNotNull();
  }
  
  
  @Test
  public void should_find_no_result() throws SQLException {
    Collection<Produit> produits = produitdao.findByLibelle("' or ''='");
    assertThat(produits).isNotNull();
    assertThat(produits).isEmpty();
  }

  @Test
  public void should_find_product_by_id() throws SQLException {

    Produit produit = produitdao.findAll()
      .stream()
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Object non trouvé"));

    Produit findProduit = produitdao.findById(produit.getIdentifier());

    assertThat(findProduit).isNotNull();
    assertThat(findProduit).isEqualTo(produit);
  }

  @After
  public void after() throws SQLException {
    this.conn.close();
  }

}
