package commands;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class CreateEditTelephoneCommand implements Command
{
    private static Logger logger = Logger.getLogger(CreateEditTelephoneCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditTelephone.jsp");
            dispatcher.forward(req, resp);
        }
        catch (ServletException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditTelephoneCommand\n");
        }
        catch (IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditTelephoneCommand\n");
        }
    }
}
