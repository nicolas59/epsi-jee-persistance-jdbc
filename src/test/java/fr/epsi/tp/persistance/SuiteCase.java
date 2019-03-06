package fr.epsi.tp.persistance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.epsi.tp.persistance.dao.CommandeDAOTest;
import fr.epsi.tp.persistance.dao.MarqueDAOTest;
import fr.epsi.tp.persistance.dao.ProduitDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  MarqueDAOTest.class,
  ProduitDAOTest.class,
  CommandeDAOTest.class
})
public class SuiteCase {

}
