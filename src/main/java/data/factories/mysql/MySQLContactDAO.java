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
    public void create(Contact object) {

    }

    @Override
    public Contact read(int id) {
        return null;
    }

    @Override
    public boolean update(Contact object) {
        return false;
    }

    @Override
    public boolean delete(Contact object) {
        return false;
    }

    @Override
    public ArrayList<Contact> readAll() {
        return null;
    }
}
