package fr.epsi.tp.persistance.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface IJdbcCrud<T, ID> {

  T findById(ID identifier) throws SQLException;

  Collection<T> findAll() throws SQLException;

  T create(T entity) throws SQLException;

  default T update(T entity) throws SQLException {
    throw new NotImplementedException();
  }

  default void delete(T entity) throws SQLException {
    throw new NotImplementedException();
  }

  default Collection<T> saveBulk(T... entity) throws SQLException {
    throw new NotImplementedException();
  }

  static class NotImplementedException extends RuntimeException {

  }
}
