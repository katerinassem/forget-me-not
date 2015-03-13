package upload;

/**
 * Created by Катерина on 12.03.2015.
 */
public class UploadException extends Exception {
    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}
