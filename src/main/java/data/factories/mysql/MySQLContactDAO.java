package data.factories.mysql;
import data.DAO;
import transfer.Contact;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLContactDAO implements DAO<Contact>
{
    @Override
    public int create(Contact object) throws SQLException, ServletException {
        int generatedId = -1;
        return generatedId;
    }

    @Override
    public Contact read(int id) throws SQLException, ServletException {
        return null;
    }

    @Override
    public boolean update(Contact object) throws SQLException, ServletException {
        return false;
    }

    @Override
    public boolean delete(Contact object) throws SQLException, ServletException {
        return false;
    }

    @Override
    public ArrayList<Contact> readAll() throws SQLException, ServletException {
        return null;
    }
}
