package data.factories.mysql;
import data.DAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLAddressDAO implements DAO<Address>
{
    private static Logger logger = Logger.getLogger(MySQLAddressDAO.class);

    @Override
    public void create(Address object) {
        logger.info(" - [ENTERING METHOD: create(Address object), PARAMETERS: [Address object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO address (country, city, street, building, apartment, post_index, contact_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            MySQLConnector connector =  MySQLConnector.getInstance();
            con = connector.getConnection();
            statement = con.prepareStatement(query);

            String country = object.getCountry();
            if(StringUtils.isNotEmpty(country)) {
                statement.setString(1, country);
            }
            else {
                statement.setNull(1, Types.VARCHAR);
            }

            String city = object.getCity();
            if(StringUtils.isNotEmpty(city)) {
                statement.setString(2, String.valueOf(city));
            }
            else {
                statement.setNull(2, Types.VARCHAR);
            }

            String street = object.getStreet();
            if(StringUtils.isNotEmpty(street)) {
                statement.setString(3, String.valueOf(street));
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

            if(object.getContact() == null || object.getContact().getId() == null) {
                statement.setNull(7, Types.INTEGER);
            }
            else {
                statement.setInt(7, object.getContact().getId());
            }
            logger.info(" - [EXECUTING QUERY] " + statement);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(e + " - [SQL EXCEPTION] - in create(Address object) ");
        }
        finally {
            if(statement != null)
            {
                try {
                    statement.close();
                    logger.info(" - [CLOSED THE STATEMENT]");
                }
                catch (SQLException e) {
                    logger.error(e + " - [CANNOT CLOSE THE STATEMENT] - in create(Address object)");
                }
            }
            if(con != null)
            {
                try {
                    con.close();
                }
                catch(SQLException e) {
                    logger.error(e + " - [CANNOT CLOSE THE CONNECTION TO DATABASE] - in create(Address object)");
                }
            }
        }

    }

    @Override
    public Address read(int id) {
        return null;
    }

    @Override
    public boolean update(Address object) {
        return false;
    }

    @Override
    public boolean delete(Address object) {
        return false;
    }

    @Override
    public ArrayList<Address> readAll() {
        return null;
    }
}
