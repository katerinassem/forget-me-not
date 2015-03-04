package data.daoexception;

/**
 * Created by Катерина on 04.03.2015.
 */
public class DAOSQLException extends Exception {

    public DAOSQLException(String message) {
        super(message);
    }

    public DAOSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOSQLException(Throwable cause) {
        super(cause);
    }
}
