package commands;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import session.SearchContactsFacade;

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
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        req.getSession().removeAttribute("infoMessage");

        try {
            String option = req.getParameter("option");
            if(StringUtils.isNotEmpty(option)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("SearchContacts.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            //  Запоминаем найденные контакты, перенаправляем на страницу контактов
            //  где отображаем результаты поиска
            //
            SearchContactsFacade searchContactsFacade = new SearchContactsFacade();
            searchContactsFacade.search(req);
            resp.sendRedirect("Front?command=ShowContactsCommand&option=search");
        }
        catch (FacadeFatalException e){
            throw new CommandFatalException(e);
        }
        catch (FacadeServiceException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно отобразить данные поиска. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch (IOException e)
        {
            throw new ServletException(e);
        }
    }
}
