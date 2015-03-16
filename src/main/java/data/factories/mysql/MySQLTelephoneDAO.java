package data.factories.mysql;

import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Telephone;
import transfer.TelephoneType;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Катерина on 03.03.2015.
 */
public class MySQLTelephoneDAO implements DAO<Telephone> {

    private static Logger logger = Logger.getLogger(MySQLAddressDAO.class);

    @Override
    public int create(Telephone object) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: create(Telephone object), PARAMETERS: [Telephone object = " + object + "]");
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
    public Telephone read(int id) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: read(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        Telephone telephone = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM telephone WHERE id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            if(rs != null) {
                if (rs.next()) {
                    Integer countryCode = rs.getInt("country_code");
                    Integer operatorCode = rs.getInt("operator_code");
                    Long telephoneNumber = rs.getLong("telephone_number");
                    TelephoneType telephoneType = TelephoneType.valueOf(rs.getString("telephone_type"));
                    String comment = rs.getString("comment");
                    Integer contactId = rs.getInt("contact_id");
                    telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
                }
            }
        }
        catch (SQLException e)
        {
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
                    logger.error(e);
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
        return telephone;
    }

    @Override
    public boolean update(Telephone object) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: update(Telephone object), PARAMETERS: [Telephone object = " + object + "]");
        Connection con = null;
        MySQLConnector connector = null;
        boolean updated = false;
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            if(object.isDeleted()){
                deleteWithRxistingConnection(object.getId(), con);
            }
            else {
                updated = updateWithExistingConnection(object, con);
            }
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
        String query = "DELETE FROM telephone WHERE id=?";
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
            logger.error(e);
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
                    logger.error(e);
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
    public ArrayList<Telephone> readAll() throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: readAll(), NO PARAMETERS]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Telephone> telephones = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM telephone";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            telephones = new ArrayList<Telephone>();
            if(rs != null) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    Integer countryCode = rs.getInt("country_code");
                    Integer operatorCode = rs.getInt("operator_code");
                    Long telephoneNumber = rs.getLong("telephone_number");
                    TelephoneType telephoneType = TelephoneType.valueOf(rs.getString("telephone_type"));
                    String comment = rs.getString("comment");
                    Integer contactId = rs.getInt("contact_id");
                    Telephone telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
                    telephones.add(telephone);
                }
            }
        }
        catch (SQLException e)
        {
            logger.error(e);
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
                    logger.error(e);
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
        return telephones;
    }

    @Override
    public ArrayList<Telephone> search(Telephone object, Object params) throws DAOSQLException, DAOFatalException {
        return null;
    }

    @Override
    public ArrayList<Telephone> readAllByContactId(int contactId) throws DAOFatalException, DAOSQLException
    {
         logger.info(" - [ENTERING METHOD: readAllByContactId(int contactId), PARAMETERS: int id = " + contactId + "]");
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
            if(rs != null) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    Integer countryCode = rs.getInt("country_code");
                    Integer operatorCode = rs.getInt("operator_code");
                    Long telephoneNumber = rs.getLong("telephone_number");
                    TelephoneType telephoneType = TelephoneType.valueOf(rs.getString("telephone_type"));
                    String comment = rs.getString("comment");
                    Telephone telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
                    telephones.add(telephone);
                }
            }
        }
        catch (SQLException e){
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
                    logger.error(e);
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

    boolean updateWithExistingConnection(Telephone object, Connection con) throws SQLException {

        logger.info(" - [ENTERING METHOD: updateWithExistingConnection(Telephone telephone, Connection con), PARAMETERS: [Telephone object = " + object + ", Connection con]");

        PreparedStatement statement = null;
        boolean updated = false;
        if(object.isDeleted()){
            updated = deleteWithRxistingConnection(object.getId(), con);
            return false;
        }
        String query = "UPDATE telephone SET country_code=?, operator_code=?, telephone_number=?, telephone_type=?, comment=? WHERE id=?";
        try {
            statement = con.prepareStatement(query);

            Integer countryCode = object.getCountryCode();
            statement.setInt(1, countryCode);

            Integer operatorCode = object.getOperatorCode();
            statement.setInt(2, operatorCode);

            Long telephoneNumber = object.getTelephoneNumber();
            statement.setLong(3, telephoneNumber);

            TelephoneType telephoneType = object.getType();
            statement.setString(4, telephoneType.name());

            String comment = object.getComment();
            if(StringUtils.isNotEmpty(comment)) {
                statement.setString(5, comment);
            }
            else {
                statement.setNull(5, Types.VARCHAR);
            }

            statement.setInt(6, object.getId());

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
                    logger.error(e);
                }
            }
        }
        return updated;
    }

    int createWithExistingConnection(Telephone object, Connection con)throws SQLException{

        logger.info(" - [ENTERING METHOD: createWithExistingConnection(Telephone telephone, Connection con), PARAMETERS: [Telephone object = " + object + ", Connection con]");
        if(object.isDeleted()) {
            return -1;
        }
        PreparedStatement statement = null;
        int generatedId = -1;
        String query = "INSERT INTO telephone (country_code, operator_code, telephone_number, telephone_type, comment, contact_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Integer countryCode = object.getCountryCode();
            statement.setInt(1, countryCode);

            Integer operatorCode = object.getOperatorCode();
            statement.setInt(2, operatorCode);

            Long telephoneNumber = object.getTelephoneNumber();
            statement.setLong(3, telephoneNumber);

            TelephoneType telephoneType = object.getType();
            statement.setString(4, telephoneType.name());

            String comment = object.getComment();
            if(StringUtils.isNotEmpty(comment)) {
                statement.setString(5, comment);
            }
            else {
                statement.setNull(5, Types.VARCHAR);
            }

            statement.setInt(6, object.getContactId());

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
                    logger.error(e);
                }
            }
        }
        return generatedId;
    }

    public boolean deleteWithRxistingConnection(int id, Connection con) throws SQLException {
        logger.info(" - [ENTERING METHOD: deleteWithExistingConnection(int id, Connection con), PARAMETERS: [int id = " + id + "]");
        PreparedStatement statement = null;
        boolean deleted = false;
        String query = "DELETE FROM telephone WHERE id=?";
        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0)
                deleted = true;
        }
        finally {
            if(statement != null)
            {
                try {
                    statement.close();
                    logger.info(" - [CLOSED THE STATEMENT]");
                }
                catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return deleted;
    }
}
