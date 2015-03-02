package data.factories.mysql;
import data.DAO;
import transfer.Contact;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLContactDAO implements DAO<Contact>
{
    @Override
    public int create(Contact object) throws Exception {
        int generatedId = -1;
        return generatedId;
    }

    @Override
    public Contact read(int id) throws Exception {
        return null;
    }

    @Override
    public boolean update(Contact object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Contact object) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Contact> readAll() throws Exception {
        return null;
    }
}
