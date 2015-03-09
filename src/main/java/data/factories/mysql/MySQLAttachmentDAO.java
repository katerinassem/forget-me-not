package data.factories.mysql;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import transfer.Attachment;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
        MySQLConnector connector = null;
        int generatedId = -1;
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            generatedId = createWithExistingConnection(object, con);
        }
        catch (SQLException e)
        {
            throw new DAOSQLException(e);
        }
        finally {
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
            if(rs != null && rs.next())
            {
                String fileName = rs.getString("file_name");
                DateTime uploadDate = DateTime.parse(rs.getDate("upload_date").toString());
                String comment = rs.getString("comment");
                Integer contactId = rs.getInt("contact_id");
                attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
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
        MySQLConnector connector = null;
        boolean updated = false;
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            updated = updateWithExistingConnection(object, con);
        }
        catch (SQLException e)
        {
            throw new DAOSQLException(e);
        }
        finally {
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
            if(rs != null) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String fileName = rs.getString("file_name");
                    DateTime uploadDate = DateTime.parse(rs.getDate("upload_date").toString());
                    String comment = rs.getString("comment");
                    Integer contactId = rs.getInt("contact_id");
                    Attachment attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
                    attachments.add(attachment);
                }
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

    @Override
    public ArrayList<Attachment> readAllByContactId(int contactId) throws DAOFatalException, DAOSQLException
    {
        logger.info(" - [ENTERING METHOD: readAllByContactId(int contactId), PARAMETERS: int contactId = " + contactId + "]");
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
            if(rs != null) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String fileName = rs.getString("file_name");
                    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
                    DateTime uploadDate = format.parseDateTime(rs.getTimestamp("upload_date").toString());
                    String comment = rs.getString("comment");
                    Attachment attachment = new Attachment(id, fileName, uploadDate, comment, contactId);
                    attachments.add(attachment);
                }
            }
        }
        catch (SQLException e){
            throw new DAOSQLException(e);
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

    boolean updateWithExistingConnection(Attachment object, Connection con) throws SQLException{

        logger.info(" - [ENTERING METHOD: updateWithExistingConnection(Attachment object), PARAMETERS: [Attachment object = " + object + ", Connection con]");
        PreparedStatement statement = null;
        boolean updated = false;
        String query = "UPDATE attachment SET file_name=?, comment=?, WHERE id=?";
        try {
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
        }
        return updated;
    }

    int createWithExistingConnection(Attachment object, Connection con) throws SQLException{
        logger.info(" - [ENTERING METHOD: createWithExistingConnection(Attachment object), PARAMETERS: [Attachment object = " + object + ", Connection con]");
        PreparedStatement statement = null;
        int generatedId = -1;
        String query = "INSERT INTO attachment (file_name, upload_date, comment, contact_id) VALUES (?, ?, ?, ?)";
        try {
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            String fileName = object.getFileName();
            statement.setString(1, fileName);

            DateTime uploadDate = object.getUploadDate();
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
            statement.setDate(2, java.sql.Date.valueOf(dateTimeFormatter.print(uploadDate)));

            String comment = object.getComment();
            if(StringUtils.isNotEmpty(comment)) {
                statement.setString(3, comment);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            statement.setInt(4, object.getContactId());
            logger.info(" - [EXECUTING QUERY] " + statement);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                object.setId(rs.getInt(1));
                generatedId = object.getId();
            }
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
        }
        return generatedId;
    }
}
