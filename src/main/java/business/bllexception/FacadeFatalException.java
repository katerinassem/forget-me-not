package business.bllexception;

/**
 * Created by Катерина on 06.03.2015.
 */
public class FacadeFatalException extends Exception {

    public FacadeFatalException(String message) {
        super(message);
    }

    public FacadeFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacadeFatalException(Throwable cause) {
        super(cause);
    }
}
