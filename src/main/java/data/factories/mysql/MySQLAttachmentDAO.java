package data.factories.mysql;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Attachment;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLAttachmentDAO implements DAO<Attachment>
{
    private static Logger logger = Logger.getLogger(MySQLAddressDAO.class);

    @Override
    public int create(Attachment object) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: create(Attachment object), PARAMETERS: [Attachment object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        MySQLConnector connector = null;
        int generatedId = -1;
        String query = "INSERT INTO attachment (file_name, upload_date, comment, unique_name, contact_id) VALUES (?, ?, ?, ?, ?)";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            String fileName = object.getFileName();
            statement.setString(1, fileName);

            Date uploadDate = object.getUploadDate();
            statement.setDate(2, java.sql.Date.valueOf(uploadDate.toString()));

            String comment = object.getComment();
            if(StringUtils.isNotEmpty(comment)) {
                statement.setString(3, comment);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            String uniqueName = object.getUniqueName();
            statement.setString(4, uniqueName);

            statement.setInt(5, object.getContactId());
            logger.info(" - [EXECUTING QUERY] " + statement);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                object.setId(rs.getInt(1));
                generatedId = object.getId();
            }
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new DAOSQLException(e);
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
            if(con != null)
            {
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return generatedId;
    }

    @Override
    public Attachment read(int id) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: read(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        Attachment attachment = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM attachment WHERE id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                String fileName = rs.getString("file_name");
                Date uploadDate = rs.getDate("upload_date");
                String comment = rs.getString("comment");
                String uniqueName = rs.getString("unique_name");
                Integer contactId = rs.getInt("contact_id");
                attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
                attachment.setUniqueName(uniqueName);
            }
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new DAOSQLException(e);
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
            if(con != null)
            {
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return attachment;
    }

    @Override
    public boolean update(Attachment object) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: create(Attachment object), PARAMETERS: [Attachment object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        MySQLConnector connector = null;
        boolean updated = false;
        String query = "UPDATE attachment SET file_name=?, comment=?, WHERE id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);

            String fileName = object.getFileName();
            statement.setString(1, fileName);

            String comment = object.getComment();
            if(StringUtils.isNotEmpty(comment)) {
                statement.setString(2, comment);
            }
            else {
                statement.setNull(2, Types.VARCHAR);
            }

            statement.setInt(3, object.getId());
            logger.info(" - [EXECUTING QUERY] " + statement);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0)
                updated = true;
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new DAOSQLException(e);
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
            if(con != null)
            {
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: delete(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        MySQLConnector connector = null;
        boolean deleted = false;
        String query = "DELETE FROM attachment WHERE id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0)
                deleted = true;
        }
        catch (SQLException e) {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new DAOSQLException(e);
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
            if(con != null)
            {
                try {
                    connector.closeConnection(con);
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return deleted;
    }

    @Override
    public ArrayList<Attachment> readAll() throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: readAll(), NO PARAMETERS]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Attachment> attachments = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM attachment";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
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
                Integer contactId = rs.getInt("contact_id");
                Attachment attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
                attachment.setUniqueName(uniqueName);
                attachments.add(attachment);
            }
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new DAOSQLException(e);
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
            if(con != null)
            {
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
}
