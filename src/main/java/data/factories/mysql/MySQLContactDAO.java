package data.factories.mysql;
import data.DAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Contact;
import transfer.Sex;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLContactDAO implements DAO<Contact>
{
    private static Logger logger = Logger.getLogger(MySQLAddressDAO.class);

    @Override
    public int create(Contact object) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: create(Address object), PARAMETERS: [Contact object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        int generatedId = -1;
        String query = "INSERT INTO contact (first_name, second_name, name_by_father, date_of_birth, sex, sitizenship, web_site, email, company, photo_url, address_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            String firstName = object.getFirstName();
            statement.setString(1, firstName);

            String secondName = object.getSecondName();
            statement.setString(2, secondName);

            String nameByFather = object.getNameByFather();
            if(StringUtils.isNotEmpty(nameByFather)) {
                statement.setString(3, nameByFather);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            java.util.Date dateOfBirth = object.getDateOfBirth();
            if(dateOfBirth != null) {
                statement.setDate(4, Date.valueOf(dateOfBirth.toString()));
            }
            else {
                statement.setNull(4, Types.DATE);
            }

            Sex sex = object.getSex();
            if(sex != null) {
                statement.setString(5, sex.name());
            }
            else {
                statement.setNull(5, Types.VARCHAR);
            }


            String sitizenship = object.getSitizenship();
            if(StringUtils.isNotEmpty(sitizenship)) {
                statement.setString(6, sitizenship);
            }
            else {
                statement.setNull(6, Types.VARCHAR);
            }

            String website = object.getWebSite();
            if(StringUtils.isNotEmpty(website)) {
                statement.setString(7, website);
            }
            else {
                statement.setNull(7, Types.VARCHAR);
            }

            String email = object.getEmail();
            if(StringUtils.isNotEmpty(email)) {
                statement.setString(8, email);
            }
            else {
                statement.setNull(8, Types.VARCHAR);
            }
            String company = object.getCompany();
            if(StringUtils.isNotEmpty(company)) {
                statement.setString(9, company);
            }
            else {
                statement.setNull(9, Types.VARCHAR);
            }

            String photoUrl = object.getPhotoUrl();
            if(StringUtils.isNotEmpty(photoUrl)) {
                statement.setString(10, photoUrl);
            }
            else {
                statement.setNull(10, Types.VARCHAR);
            }

            Integer addressId = object.getAddressId();
            statement.setInt(11, addressId);

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
    public Contact read(int id) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: read(int id), PARAMETERS: [int id = " + id + "]");
        Connection con = null;
        PreparedStatement statement = null;
        Contact contact = null;
        String query = "SELECT * FROM contact WHERE id=?";
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
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String nameByFather = rs.getString("name_by_father");
                java.util.Date dateOfBirth = rs.getDate("date_of_birth");
                Sex sex = Sex.valueOf(rs.getString("sex"));
                String sitizenship = rs.getString("sitizenship");
                String webSite = rs.getString("web_site");
                String email = rs.getString("email");
                String company = rs.getString("company");
                String photoUrl = rs.getString("photo_url");
                contact = new Contact(id, firstName, secondName, nameByFather, dateOfBirth, sex, sitizenship, webSite, email, company, photoUrl, null);
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
        return contact;
    }

    @Override
    public boolean update(Contact object) throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: update(Contact object), PARAMETERS: [Contact object = " + object + "]");
        Connection con = null;
        PreparedStatement statement = null;
        boolean updated = false;
        String query = "UPDATE contact SET first_name=?, second_name=?, name_by_father=?, date_of_birth=?, sex=?, sitizenship=?, web_site=?, email=?, company=?, photo_url=?, address_id=? WHERE id=?";
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

            String firstName = object.getFirstName();
            statement.setString(1, firstName);

            String secondName = object.getSecondName();
            statement.setString(2, secondName);

            String nameByFather = object.getNameByFather();
            if(StringUtils.isNotEmpty(nameByFather)) {
                statement.setString(3, nameByFather);
            }
            else {
                statement.setNull(3, Types.VARCHAR);
            }

            java.util.Date dateOfBirth = object.getDateOfBirth();
            if(dateOfBirth != null) {
                statement.setDate(4, Date.valueOf(dateOfBirth.toString()));
            }
            else {
                statement.setNull(4, Types.DATE);
            }

            Sex sex = object.getSex();
            if(sex != null) {
                statement.setString(5, sex.name());
            }
            else {
                statement.setNull(5, Types.VARCHAR);
            }


            String sitizenship = object.getSitizenship();
            if(StringUtils.isNotEmpty(sitizenship)) {
                statement.setString(6, sitizenship);
            }
            else {
                statement.setNull(6, Types.VARCHAR);
            }

            String website = object.getWebSite();
            if(StringUtils.isNotEmpty(website)) {
                statement.setString(7, website);
            }
            else {
                statement.setNull(7, Types.VARCHAR);
            }

            String email = object.getEmail();
            if(StringUtils.isNotEmpty(email)) {
                statement.setString(8, email);
            }
            else {
                statement.setNull(8, Types.VARCHAR);
            }
            String company = object.getCompany();
            if(StringUtils.isNotEmpty(company)) {
                statement.setString(9, company);
            }
            else {
                statement.setNull(9, Types.VARCHAR);
            }

            String photoUrl = object.getPhotoUrl();
            if(StringUtils.isNotEmpty(photoUrl)) {
                statement.setString(10, photoUrl);
            }
            else {
                statement.setNull(10, Types.VARCHAR);
            }

            Integer addressId = object.getAddressId();
            statement.setInt(11, addressId);

            statement.setInt(12, object.getId());

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
        String query = "DELETE FROM contact WHERE id=?";
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
    public ArrayList<Contact> readAll() throws SQLException, ServletException {
        logger.info(" - [ENTERING METHOD: readAll(), NO PARAMETERS]");
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<Contact> contacts = null;
        String query = "SELECT * FROM contact";
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
            contacts = new ArrayList<Contact>();
            while(rs.next())
            {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String nameByFather = rs.getString("name_by_father");
                java.util.Date dateOfBirth = rs.getDate("date_of_birth");
                Sex sex = Sex.valueOf(rs.getString("sex"));
                String sitizenship = rs.getString("sitizenship");
                String webSite = rs.getString("web_site");
                String email = rs.getString("email");
                String company = rs.getString("company");
                String photoUrl = rs.getString("photo_url");
                Contact contact = new Contact(id, firstName, secondName, nameByFather, dateOfBirth, sex, sitizenship, webSite, email, company, photoUrl, null);
                contacts.add(contact);
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
        return contacts;
    }
}
