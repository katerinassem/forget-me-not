package commands;

import business.Service;
import business.bllexception.*;
import business.service.ShowList;
import commands.commandexception.CommandFatalException;
import listhandler.ContactListHandler;
import listhandler.ListHandler;
import org.apache.log4j.Logger;
import session.ShowListFacade;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Катерина on 20.02.2015.
 */
public class ShowContactsCommand implements Command
{

    private static Logger logger = Logger.getLogger(ShowContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException
    {
        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        try
        {
            String str = req.getParameter("page");
            Integer page = str == null ? null : Integer.parseInt(str);
            ShowListFacade showListFacade = new ShowListFacade();
            showListFacade.show(req.getSession(), page);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            dispatcher.forward(req, resp);
        }
        catch(IOException e)
        {
            throw new ServletException();
        }
        catch(FacadeServiceException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно отобразить данные. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch(FacadeFatalException e)
        {
            throw new CommandFatalException(e);
        }
    }
}
