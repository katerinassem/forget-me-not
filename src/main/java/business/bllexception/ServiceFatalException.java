package business.bllexception;

/**
 * Created by Катерина on 05.03.2015.
 */
public class ServiceFatalException extends Exception {

    public ServiceFatalException(String message) {
        super(message);
    }

    public ServiceFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceFatalException(Throwable cause) {
        super(cause);
    }
}
