package data;

import javax.servlet.ServletException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface DAO<T>
{
    int create (T object) throws SQLException, ServletException;
    T read(int id) throws SQLException, ServletException;
    boolean update (T object) throws SQLException, ServletException;
    boolean delete (int id) throws SQLException, ServletException;
    ArrayList<T> readAll() throws SQLException, ServletException;
}
