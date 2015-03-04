package data.daoexception;

/**
 * Created by Катерина on 04.03.2015.
 */
public class DAOFatalException extends Exception {

    public DAOFatalException(String message) {
        super(message);
    }

    public DAOFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOFatalException(Throwable cause) {
        super(cause);
    }
}
