package data;

import transfer.Address;
import transfer.Attachment;
import transfer.Contact;
import transfer.Telephone;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface AbstractDAOFactory
{
    DAO<Address> getAddressDAO();
    DAO<Attachment> getAttachmentDAO();
    DAO<Contact> getContactDAO();
    DAO<Telephone> getTelephoneDAO();
}
