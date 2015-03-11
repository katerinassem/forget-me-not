package business.factory.katebusiness;

import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import data.factories.mysql.MySQLConnector;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import transfer.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Катерина on 04.03.2015.
 */
public class ContactBO extends Business<Contact>{

    private static Logger logger = Logger.getLogger(AddressBO.class);

    public ContactBO(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        logger.info(" [ENTERING AddressBO CONSTRUCTOR]");
        entityDAO = daoFactory.getContactDAO();
    }

    @Override
    public Contact getObjectById(int id) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: getObjectById(int id), PARAMETERS: int id = " + id + "]");
        Contact contact = super.getObjectById(id);
        DAO<Address> addressDAO = daoFactory.getAddressDAO();
        if(contact != null && contact.getAddressId() != null) {
            try {
                Address address = addressDAO.read(contact.getAddressId());
                if (address != null) {
                    contact.setAddress(address);
                }
            }
            catch (DAOSQLException e){
                throw new BLLDataException(e);
            }
            catch (DAOFatalException e){
                throw new BLLFatalException(e);
            }
        }
        return contact;
    }

    @Override
    public Contact getFullObjectById(int id) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: getFullObjectById(int id), PARAMETERS: int id = " + id + "]");
        Contact contact = super.getFullObjectById(id);
        if(contact != null) {
            DAO<Attachment> attachmentDAO = daoFactory.getAttachmentDAO();
            try {
                ArrayList<Attachment> attachments = attachmentDAO.readAllByContactId(id);
                if (CollectionUtils.isNotEmpty(attachments)) {
                    contact.setAttachments(attachments);
                }
                DAO<Telephone> telephoneDAO = daoFactory.getTelephoneDAO();
                ArrayList<Telephone> telephones = telephoneDAO.readAllByContactId(id);
                if (CollectionUtils.isNotEmpty(telephones)) {
                    contact.setTelephones(telephones);
                }
            } catch (DAOSQLException e) {
                throw new BLLDataException(e);
            } catch (DAOFatalException e) {
                throw new BLLFatalException(e);
            }
        }
        return contact;
    }

    @Override
    public ArrayList<Contact> readAllObjects() throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: readAllObjects(), NO PARAMETERS]");
        ArrayList<Contact> contacts =  super.readAllObjects();
        try {
            appendAddressForContact(contacts);
        }
        catch (DAOSQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        return contacts;
    }

    @Override
    public ArrayList<Contact> searchAllObjects(Contact object, Object params) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: searchAllObjects(Contact object, Object params), PARAMETERS: Contact object = " + object + ", Object params = " + params + "]");
        ArrayList<Contact> contacts =  super.searchAllObjects(object, params);
        try {
            appendAddressForContact(contacts);
        }
        catch (DAOSQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        return contacts;
    }

    private void appendAddressForContact(ArrayList<Contact> contacts) throws DAOSQLException, DAOFatalException{

        logger.info(" - [ENTERING help METHOD appendAddressForContact(ArrayList<Contact> contacts), PARAMETERS: ArrayList<Contact> contacts = " + contacts +"]");
        if(CollectionUtils.isNotEmpty(contacts)) {
            DAO<Address> addressDAO = daoFactory.getAddressDAO();
            for (Contact contact : contacts) {
                if (contact.getAddressId() != null) {
                    Address address = addressDAO.read(contact.getAddressId());
                    if (address != null) {
                        contact.setAddress(address);
                    }
                }
            }
        }
    }


}
