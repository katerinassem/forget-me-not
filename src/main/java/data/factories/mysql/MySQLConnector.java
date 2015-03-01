package data.factories.mysql;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLConnector
{
    private Logger logger = Logger.getLogger(MySQLConnector.class);
    DataSource pool;
    private String dbUrl;
    private String user;
    private String password;

    private static MySQLConnector instance = new MySQLConnector();

    private MySQLConnector()
    {
        String propPath = "config.properties";
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        dbUrl = bundle.getString("dbUrl");
        user = bundle.getString("user");
        password = bundle.getString("password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            logger.error(e + " - [CANNOT REGISTER com.mysql.jdbc.Driver] - in getConnection() ");
        }
        Context env = null;
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/youcontact");
            if(pool == null) {
                logger.error(" - [CANNOT FIND DATA SOURCE] - in MySQLConnector CONSTRUCTOR");
            }
        } catch (NamingException e) {
            logger.error(e + " - [NAMING EXCEPTION] while creating a pool - in MySQLConnector CONSTRUCTOR");
        }
    }

    public static MySQLConnector getInstance()
    {
        return instance;
    }

    public Connection getConnection() throws SQLException
    {
        logger.info(" - [" + user + " CONNECTING TO DATABASE " + dbUrl + "]");
        Connection conn = pool.getConnection(user, password);
                //DriverManager.getConnection(dbUrl, user, password);
        return conn;
    }

    public boolean closeConnection(Connection conn) throws SQLException
    {
        conn.close();
        logger.info(" - [CLOSED THE CONNECTION]");
        return true;
    }
}
