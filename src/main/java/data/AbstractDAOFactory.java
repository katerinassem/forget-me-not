package data;

import transfer.Address;
import transfer.Attachment;
import transfer.Contact;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface AbstractDAOFactory
{
    DAO<Address> getAddressDAO();
    DAO<Attachment> getAttachmentDAO();
    DAO<Contact> getContactDAO();
}
