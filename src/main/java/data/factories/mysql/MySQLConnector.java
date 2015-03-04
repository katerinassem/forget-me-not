package data.factories.mysql;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLConnector
{
    private Logger logger = Logger.getLogger(MySQLConnector.class);
    DataSource pool;

    private static MySQLConnector instance = new MySQLConnector();

    private MySQLConnector()
    {
        logger.info(" - [ENTERING METHOD: MySQLConnector CONSTRUCTOR, NO PARAMETERS]");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            logger.error(e + " - [CANNOT REGISTER com.mysql.jdbc.Driver]");
        }
        Context env = null;
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/youcontact");
            if(pool == null) {
                logger.error(" - [CANNOT FIND DATA SOURCE]");
            }
        } catch (NamingException e) {
            logger.error(e + " - [CONTEXT EXCEPTION] while creating a pool.");
        }
    }

    public static MySQLConnector getInstance() throws DAOFatalException
    {
        if(instance == null)
        {
            throw new DAOFatalException("Database connector wasn't created.");
        }
        return instance;
    }

    public Connection getConnection() throws DAOFatalException
    {
        logger.info(" - [ENTERING METHOD: getConnection(), NO PARAMETERS]");
        try {
            Connection conn = pool.getConnection();
            logger.info(" - [ CONNECTED TO DATABASE ]");
            return conn;
        }
        catch (SQLException e)
        {
            logger.error(e);
            throw new DAOFatalException("Cannot get connection to the database.", e);
        }
    }

    public boolean closeConnection(Connection conn) throws SQLException
    {
        logger.info(" - [ENTERING METHOD: closeConnection(Connection conn), PARAMETERS: [Connection conn = " + conn + "]");
        logger.info(" - [ CLOSING THE CONNECTION]");
        if(conn != null) {
            conn.close();
        }
        logger.info(" - [CLOSED THE CONNECTION]");
        return true;
    }
}
