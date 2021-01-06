package fr.epsi.tp.persistance.dao;

import java.sql.SQLException;
import java.util.Collection;

import fr.epsi.tp.persistance.bean.Commande;

public class CommandeDAO implements IJdbcCrud<Commande, Long> {

  public Commande findById(Long identifier) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Commande> findAll() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  public Commande create(Commande entity) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Commande update(Commande entity) throws SQLException {
    return null;
  }
}

