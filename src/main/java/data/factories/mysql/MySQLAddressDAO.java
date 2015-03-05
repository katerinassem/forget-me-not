package data.factories.mysql;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLAddressDAO implements DAO<Address>
{
    private static Logger logger = Logger.getLogger(MySQLAddressDAO.class);

    @Override
    public int create(Address object) throws DAOSQLException, DAOFatalException {
        logger.info(" - [ENTERING METHOD: create(Address object), PARAMETERS: [Address object = " + object + "]");
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
    public Address read(int id) throws DAOSQLException, DAOFatalException{
        logger.info(" - [ENTERING METHOD: read(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        Address address = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM address WHERE id=?";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer building = rs.getInt("building");
                Integer apartment = rs.getInt("apartment");
                Long postIndex = rs.getLong("post_index");
                Integer contactId = rs.getInt("contact_id");
                address = new Address(id, country, city, street, building, apartment, postIndex, contactId);
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
        return address;
    }

    @Override
    public boolean update(Address object) throws DAOSQLException, DAOFatalException{
        logger.info(" - [ENTERING METHOD: update(Address object), PARAMETERS: [Address object = " + object + "]");
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
    public boolean delete(int id) throws DAOSQLException, DAOFatalException{
        logger.info(" - [ENTERING METHOD: delete(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        MySQLConnector connector = null;
        boolean deleted = false;
        String query = "DELETE FROM address WHERE id=?";
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
    public ArrayList<Address> readAll() throws DAOSQLException, DAOFatalException{
        logger.info(" - [ENTERING METHOD: readAll(), NO PARAMETERS]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Address> addresses = null;
        MySQLConnector connector = null;
        String query = "SELECT * FROM address";
        try {
            connector = MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            addresses = new ArrayList<Address>();
            while(rs.next())
            {
                Integer id = rs.getInt("id");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer building = rs.getInt("building");
                Integer apartment = rs.getInt("apartment");
                Long postIndex = rs.getLong("post_index");
                Integer contactId = rs.getInt("contact_id");
                Address address = new Address(id, country, city, street, building, apartment, postIndex, contactId);
                addresses.add(address);
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
        return addresses;
    }

    @Override
    public ArrayList<Address> readAllByContactId(int contactId) throws DAOFatalException, DAOSQLException
    {
        return null;
    }

    boolean updateWithExistingConnection(Address object, Connection con) throws SQLException{

        logger.info(" - [ENTERING METHOD: updateWithExistingConnection(Address object, Connection con), PARAMETERS: [Address object = " + object + ", Connection con]");
        PreparedStatement statement = null;
        boolean updated = false;
        String query = "UPDATE address SET country=?, city=?, street=?, building=?, apartment=?, post_index=? WHERE id=?";
        try {
            statement = con.prepareStatement(query);

            String country = object.getCountry();
            statement.setString(1, country);

            String city = object.getCity();
            statement.setString(2, city);

            String street = object.getStreet();
            if(StringUtils.isNotEmpty(street)) {
                statement.setString(3, street);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            Integer building = object.getBuilding();
            if(building != null) {
                statement.setInt(4, building);
            }
            else {
                statement.setNull(4, Types.INTEGER);
            }

            Integer apartment = object.getApartment();
            if(apartment != null) {
                statement.setInt(5, apartment);
            }
            else {
                statement.setNull(5, Types.INTEGER);
            }

            Long index = object.getIndex();
            if(index != null) {
                statement.setLong(6, index);
            }
            else {
                statement.setNull(6, Types.BIGINT);
            }

            statement.setInt(7, object.getId());

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

    int createWithExistingConnection(Address object, Connection con) throws SQLException
    {
        logger.info(" - [ENTERING METHOD: createWithExistingConnection(Address object, Connection con), PARAMETERS: [Address object = " + object + ", Connection con]");
        PreparedStatement statement = null;
        int generatedId = -1;
        String query = "INSERT INTO address (country, city, street, building, apartment, post_index, contact_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            String country = object.getCountry();
            statement.setString(1, country);

            String city = object.getCity();
            statement.setString(2, city);

            String street = object.getStreet();
            if(StringUtils.isNotEmpty(street)) {
                statement.setString(3, street);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            Integer building = object.getBuilding();
            if(building != null) {
                statement.setInt(4, building);
            }
            else {
                statement.setNull(4, Types.INTEGER);
            }

            Integer apartment = object.getApartment();
            if(apartment != null) {
                statement.setInt(5, apartment);
            }
            else {
                statement.setNull(5, Types.INTEGER);
            }

            Long index = object.getIndex();
            if(index != null) {
                statement.setLong(6, index);
            }
            else {
                statement.setNull(6, Types.BIGINT);
            }

            statement.setNull(7, Types.INTEGER);
            logger.info(" - [EXECUTING QUERY] " + statement);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                object.setId(rs.getInt(1));
                generatedId = object.getId();
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    logger.info(" - [CLOSED THE STATEMENT]");
                } catch (SQLException e) {
                    logger.error(e + " - [CANNOT CLOSE THE STATEMENT]");
                }
            }
        }
        return generatedId;
    }
}
