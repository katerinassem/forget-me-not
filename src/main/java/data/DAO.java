package data;

import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;

import javax.servlet.ServletException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface DAO<T>
{
    int create (T object) throws DAOSQLException, DAOFatalException;
    T read(int id) throws DAOSQLException, DAOFatalException;
    ArrayList<T> readAllByContactId(int contactId) throws DAOSQLException, DAOFatalException;
    boolean update (T object) throws DAOSQLException, DAOFatalException;
    boolean delete (int id) throws DAOSQLException, DAOFatalException;
    ArrayList<T> readAll() throws DAOSQLException, DAOFatalException;
    ArrayList<T> search(T object, Object params) throws DAOSQLException, DAOFatalException;
}
