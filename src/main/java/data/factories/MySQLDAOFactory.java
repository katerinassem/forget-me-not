package data.factories;
import data.AbstractDAOFactory;
import data.DAO;
import data.factories.mysql.MySQLAddressDAO;
import data.factories.mysql.MySQLAttachmentDAO;
import data.factories.mysql.MySQLContactDAO;
import transfer.Address;
import transfer.Attachment;
import transfer.Contact;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLDAOFactory implements AbstractDAOFactory
{
    @Override
    public DAO<Address> getAddressDAO() {
        return new MySQLAddressDAO();
    }

    @Override
    public DAO<Attachment> getAttachmentDAO() {
        return new MySQLAttachmentDAO();
    }

    @Override
    public DAO<Contact> getContactDAO() {
        return new MySQLContactDAO();
    }
}
