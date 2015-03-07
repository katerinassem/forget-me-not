package commands;

import commands.commandexception.CommandFatalException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class SaveTelephoneCommand implements Command
{
    private static Logger logger = Logger.getLogger(SaveTelephoneCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        try {
            //Сохранение телефона
            //не в базу!
            //

            //Не забыть передать нужные параметры!!!
            //Возвращаем на страницу редактирования/создания телефона
            String value = req.getParameter("contactId");
            Integer contactId = value == null ? null : Integer.parseInt(value);
            RequestDispatcher dispatcher = req.getRequestDispatcher("?command=CreateEditContactCommand&option=editmore");
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
