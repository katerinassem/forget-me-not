package business.bllexception;

/**
 * Created by Катерина on 06.03.2015.
 */
public class FacadeServiceException extends Exception {

    public FacadeServiceException(String message) {
        super(message);
    }

    public FacadeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacadeServiceException(Throwable cause) {
        super(cause);
    }
}
