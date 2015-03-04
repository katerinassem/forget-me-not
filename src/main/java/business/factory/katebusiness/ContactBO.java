package business.factory.katebusiness;

import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import data.factories.mysql.MySQLConnector;
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
        Contact contact = super.getFullObjectById(id);
        DAO<Address> addressDAO = daoFactory.getAddressDAO();
        try {
            Address address = addressDAO.read(contact.getAddressId());
            if(contact != null && address != null){
                contact.setAddress(address);
            }
        }
        catch (DAOSQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        return contact;
    }

    @Override
    public Contact getFullObjectById(int id) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: getFullObjectById(int id), PARAMETERS: int id = " + id + "]");
        Contact contact = super.getFullObjectById(id);
        ArrayList<Attachment> attachments = getContactsAttachmentsByContactId(id);
        if(attachments != null) {
            contact.setAttachments(attachments);
        }
        ArrayList<Telephone> telephones = getContactsTelephonesByContactId(id);
        if(telephones != null){
            contact.setTelephones(telephones);
        }
        return contact;
    }

    @Override
    public ArrayList<Contact> readAllObjects() throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: readAllObjects(), NO PARAMETERS]");
        ArrayList<Contact> contacts =  super.readAllObjects();
        DAO<Address> addressDAO = daoFactory.getAddressDAO();
        try {
            for(Contact contact : contacts) {
                if(contact.getAddressId() != null) {
                    Address address = addressDAO.read(contact.getAddressId());
                    if (address != null) {
                        contact.setAddress(address);
                    }
                }
            }
        }
        catch (DAOSQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        return contacts;
    }

    public ArrayList<Attachment> getContactsAttachmentsByContactId(int contactId) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: getContactsAttachmentsByContactId(int contactId), PARAMETERS: int contactId = " + contactId + "]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Attachment> attachments = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM attachment WHERE contact_id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, contactId);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            attachments = new ArrayList<Attachment>();
            while(rs.next())
            {
                Integer id = rs.getInt("id");
                String fileName = rs.getString("file_name");
                Date uploadDate = rs.getDate("upload_date");
                String comment = rs.getString("comment");
                String uniqueName = rs.getString("unique_name");
                Attachment attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
                attachment.setUniqueName(uniqueName);
                attachments.add(attachment);
            }
        }
        catch (SQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        finally {
            if(statement != null){
                try {
                    statement.close();
                    logger.info(" - [CLOSED THE STATEMENT]");
                }
                catch (SQLException e) {
                    logger.error(e + " - [CANNOT CLOSE THE STATEMENT]");
                }
            }
            if(con != null){
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return attachments;
    }

    public ArrayList<Telephone> getContactsTelephonesByContactId(int contactId) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: getTelephonesByContactId(int contactId), PARAMETERS: int id = " + contactId + "]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Telephone> telephones = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM telephone WHERE contact_id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, contactId);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            telephones = new ArrayList<Telephone>();
            while(rs.next())
            {
                Integer id = rs.getInt("id");
                Short countryCode = rs.getShort("country_code");
                Short operatorCode = rs.getShort("operator_code");
                Long telephoneNumber = rs.getLong("telephone_number");
                TelephoneType telephoneType = TelephoneType.valueOf(rs.getString("telephone_type"));
                String comment = rs.getString("comment");
                Telephone telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
                telephones.add(telephone);
            }
        }
        catch (SQLException e){
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e){
            throw new BLLFatalException(e);
        }
        finally {
            if(statement != null)
            {
                try {
                    statement.close();
                    logger.info(" - [CLOSED THE STATEMENT]");
                }
                catch (SQLException e) {
                    logger.error(e + " - [CANNOT CLOSE THE STATEMENT]");
                }
            }
            if(con != null){
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return telephones;
    }
}
