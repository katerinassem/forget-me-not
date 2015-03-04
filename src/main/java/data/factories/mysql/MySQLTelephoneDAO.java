package data.factories.mysql;

import data.DAO;
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
    public int create(Telephone object) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: create(Telephone object), PARAMETERS: [Telephone object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        int generatedId = -1;
        String query = "INSERT INTO telephone (country_code, operator_code, telephone_number, telephone_type, comment, contact_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            MySQLConnector connector = null;
            try {
                connector = MySQLConnector.getInstance();
                con = connector.getConnection();
            }
            catch(Exception e){
                logger.error(e + " - [DATASOURCE EXCEPTION]");
                throw new ServletException(e);
            }
            statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Short countryCode = object.getCountryCode();
            statement.setShort(1, countryCode);

            Short operatorCode = object.getOperatorCode();
            statement.setShort(2, operatorCode);

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
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new SQLException(e);
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
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return generatedId;
    }

    @Override
    public Telephone read(int id) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: read(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        Telephone telephone = null;
        String query = "SELECT * FROM telephone WHERE id=?";
        try {
            MySQLConnector connector = null;
            try {
                connector = MySQLConnector.getInstance();
                con = connector.getConnection();
            }
            catch(Exception e){
                logger.error(e + " - [DATASOURCE EXCEPTION]");
                throw new ServletException(e);
            }
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                Short countryCode = rs.getShort("country_code");
                Short operatorCode = rs.getShort("operator_code");
                Long telephoneNumber = rs.getLong("telephone_number");
                TelephoneType telephoneType = TelephoneType.valueOf(rs.getString("telephone_type"));
                String comment = rs.getString("comment");
                Integer contactId = rs.getInt("contact_id");
                telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
            }
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new SQLException(e);
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
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return telephone;
    }

    @Override
    public boolean update(Telephone object) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: update(Telephone object), PARAMETERS: [Telephone object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        boolean updated = false;
        String query = "UPDATE telephone SET country_code=?, operator_code=?, telephone_number=?, telephone_type=?, comment=? WHERE id=?";
        try {
            MySQLConnector connector = null;
            try {
                connector = MySQLConnector.getInstance();
                con = connector.getConnection();
            }
            catch(Exception e){
                logger.error(e + " - [DATASOURCE EXCEPTION]");
                throw new ServletException(e);
            }
            statement = con.prepareStatement(query);

            Short countryCode = object.getCountryCode();
            statement.setShort(1, countryCode);

            Short operatorCode = object.getOperatorCode();
            statement.setShort(2, operatorCode);

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
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new SQLException(e);
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
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return updated;
    }

    @Override
    public boolean delete(int id) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: delete(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        boolean deleted = false;
        String query = "DELETE FROM telephone WHERE id=?";
        try {
            MySQLConnector connector = null;
            try {
                connector = MySQLConnector.getInstance();
                con = connector.getConnection();
            }
            catch(Exception e){
                logger.error(e + " - [DATASOURCE EXCEPTION]");
                throw new ServletException(e);
            }
            statement = con.prepareStatement(query);
            statement.setInt(1, id);
            logger.info(" - [EXECUTING QUERY] " + statement);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0)
                deleted = true;
        }
        catch (SQLException e) {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new SQLException(e);
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
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return deleted;
    }

    @Override
    public ArrayList<Telephone> readAll() throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: readAll(), NO PARAMETERS]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Telephone> telephones = null;
        String query = "SELECT * FROM telephone";
        try {
            MySQLConnector connector = null;
            try {
                connector = MySQLConnector.getInstance();
                con = connector.getConnection();
            }
            catch(Exception e){
                logger.error(e + " - [DATASOURCE EXCEPTION]");
                throw new ServletException(e);
            }
            statement = con.prepareStatement(query);
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
                Integer contactId = rs.getInt("contact_id");
                Telephone telephone = new Telephone(id, countryCode, operatorCode, telephoneNumber, telephoneType, comment, contactId);
                telephones.add(telephone);
            }
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION]");
            throw new SQLException(e);
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
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e);
                }
            }
        }
        return telephones;
    }
}
