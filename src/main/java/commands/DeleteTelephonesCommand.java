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
public class DeleteTelephonesCommand implements Command
{
    private static Logger logger = Logger.getLogger(DeleteTelephonesCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //Удаление телефонов
            //из базы
            //

            //Не забыть передать нужные параметры!!!
            RequestDispatcher dispatcher = req.getRequestDispatcher("?command=CreateEditContactCommand&option=edit");
            dispatcher.forward(req, resp);
        }
        catch (ServletException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SendEmailCommand\n");
        }
        catch (IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SendEmailCommand\n");
        }
    }
}
