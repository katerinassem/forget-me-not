package business.bllexception;

/**
 * Created by Катерина on 05.03.2015.
 */
public class ServiceDataException extends Exception {

    public ServiceDataException(String message) {
        super(message);
    }

    public ServiceDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDataException(Throwable cause) {
        super(cause);
    }
}
