package business.bllexception;

/**
 * Created by Катерина on 04.03.2015.
 */
public class BLLFatalException extends Exception {

    public BLLFatalException(String message) {
        super(message);
    }

    public BLLFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BLLFatalException(Throwable cause) {
        super(cause);
    }
}
