package commands.commandexception;

/**
 * Created by Катерина on 05.03.2015.
 */
public class CommandFatalException extends Exception {

    public CommandFatalException(String message) {
        super(message);
    }

    public CommandFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandFatalException(Throwable cause) {
        super(cause);
    }
}
