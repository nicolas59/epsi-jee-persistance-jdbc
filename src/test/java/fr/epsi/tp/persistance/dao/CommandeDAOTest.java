package fr.epsi.tp.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import fr.epsi.tp.persistance.ConnectionFactory;
import fr.epsi.tp.persistance.bean.Commande;
import fr.epsi.tp.persistance.bean.CommandeLigne;

public class CommandeDAOTest {
  private Connection conn;

  private CommandeDAO commandeDao = new CommandeDAO();

  private ProduitDAO produitDao = new ProduitDAO();

  @Before
  public void beforeTest() throws Exception {
    this.conn =ConnectionFactory.getInstance().getConnection();
    SQLUtils.initDatabase(this.conn);
    DatabaseConnection dbConn = new DatabaseConnection(conn);
      
    IDataSet dataSet = new FlatXmlDataSetBuilder().build(CommandeDAOTest.class.getResourceAsStream("/dataset.xml"));
    DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
  }

  @Test
  public void should_create_new_commande() throws Exception {
    Commande commande = new Commande();

    List<CommandeLigne> lignes = produitDao.findAll()
      .stream()
      .limit(3)
      .map(produit -> {
        CommandeLigne l = new CommandeLigne();
        l.setQuantite(1);
        l.setProduit(produit);
        return l;
      })
      .collect(Collectors.toList());

    BigDecimal total = lignes.stream()
      .map(ligne -> ligne.getProduit()
        .getPrix()
        .multiply(BigDecimal.valueOf(ligne.getQuantite())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    commande.setDateCreation(LocalDate.now());
    commande.setLignes(lignes);

    Commande commandeNew = commandeDao.create(commande);
    assertThat(commandeNew).isNotNull();
    assertThat(commandeNew.getIdentifier()).isNotNull();

    Commande commSelect = this.commandeDao.findById(commandeNew.getIdentifier());
    BigDecimal totalNew = commSelect.getLignes()
      .stream()
      .map(ligne -> ligne.getProduit()
        .getPrix()
        .multiply(BigDecimal.valueOf(ligne.getQuantite())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    assertThat(total).isEqualByComparingTo(totalNew);
  }
  
  @Test
  public void should_find_commande_by_id() throws SQLException{
    Commande commande = this.commandeDao.findById(1L);
    assertThat(commande).isNotNull();
    assertThat(commande.getLignes().size()).isEqualTo(2);
  }
}
