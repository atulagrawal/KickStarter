package db.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, ID> {

    public T create(T t) throws SQLException;
    public boolean update(T t) throws SQLException;
    public boolean delete(T t) throws SQLException;
    public List<T> search (T criteria) throws SQLException;
    public T getById(ID id) throws SQLException;
    public List<T> findAll () throws SQLException;
}
