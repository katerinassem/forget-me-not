package upload;

/**
 * Created by Катерина on 15.03.2015.
 */
public class DownloadException extends  Exception {
    public DownloadException(String message) {
        super(message);
    }

    public DownloadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadException(Throwable cause) {
        super(cause);
    }
}
