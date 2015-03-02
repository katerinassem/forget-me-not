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
public class SearchContactsCommand implements Command
{
    private static Logger logger = Logger.getLogger(SearchContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        try {
            //  Запоминаем найденные контакты, перенаправляем на страницу контактов
            //  где отображаем результаты поиска
            //
            RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            dispatcher.forward(req, resp);
        }
        catch (ServletException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SearchContactsCommand\n");
        }
        catch (IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SearchContactsCommand\n");
        }
    }
}
