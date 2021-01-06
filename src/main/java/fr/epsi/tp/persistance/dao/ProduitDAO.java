package fr.epsi.tp.persistance.dao;

import java.sql.SQLException;
import java.util.Collection;

import fr.epsi.tp.persistance.bean.Produit;

public class ProduitDAO implements IJdbcCrud<Produit, Long> {

  public Produit findById(Long identifier) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<Produit> findAll() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  public Produit create(Produit entity) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }
  
  public Collection<Produit> findByLibelle(String libelle) throws SQLException {
    //TODO
    return null;
  }

  @Override
  public Produit update(Produit entity) throws SQLException {
    return null;
  }

}
