package business.bllexception;

/**
 * Created by Катерина on 04.03.2015.
 */
public class BLLDataException extends Exception {

    public BLLDataException(String message) {
        super(message);
    }

    public BLLDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public BLLDataException(Throwable cause) {
        super(cause);
    }
}
