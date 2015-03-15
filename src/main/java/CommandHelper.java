
import commands.Command;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import upload.UploadException;
import upload.UploadHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Катерина on 20.02.2015.
 */
public class CommandHelper {

    private static Logger logger = Logger.getLogger(CommandHelper.class);

    public void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws UploadException, ServletException, CommandFatalException
    {
        logger.info(" - [ ENTERING METHOD dispatchRequest(HttpServletRequest req, HttpServletResponse resp)]");
        prepareRequest(req);
        String attrValue = null;
        if(req.getAttribute("command") != null) {
            attrValue = String.valueOf(((String[])req.getAttribute("command"))[0]);
        }
        else{
            attrValue = req.getParameter("command");
        }
        Command command = getCommandForCommandName(attrValue);
        command.process(req, resp);
    }

    public void prepareRequest(HttpServletRequest req) throws UploadException{

        logger.info(" - [ ENTERING METHOD prepareRequest(HttpServletRequest req)]");
        UploadHelper uploadHelper = UploadHelper.getInstance();
        uploadHelper.prepareRequest(req);
    }

    public Command getCommandForCommandName(String commandName)
    {
        Command command = null;
        try {
            String commandClassName = commandName == null ? "commands.ShowContactsCommand" : commandName == "" ? "commands.ShowContactsCommand" : "commands." + commandName;
            Class c = Class.forName(commandClassName);
            command = (Command) c.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            logger.error(e + " - in getCommandForCommandName(String commandName), getting default command (commands.ShowContactsCommand)\n");
            String commandClassName = "commands.ShowContactsCommand";
            try {
                Class c = Class.forName(commandClassName);
                command = (Command) c.newInstance();
            }
            catch (ClassNotFoundException e1)
            {
                logger.error(e1 + " - Cannot get default command class in getCommandForCommandName(String commandName)\n");
            }
            catch (InstantiationException e1)
            {
                logger.error(e1 + " - Cannot get instance of default command class in getCommandForCommandName(String commandName)\n");
            }
            catch (IllegalAccessException e1)
            {
                logger.error(e1 + " - Don't have access to the default command class in getCommandForCommandName(String commandName)\n");
            }
        }
        catch (InstantiationException e)
        {
            logger.error(e + " -  in getCommandForCommandName(String commandName)\n");
        }
        catch (IllegalAccessException e)
        {
            logger.error(e + " - in getCommandForCommandName(String commandName)\n");
        }
        return command;
    }
}
