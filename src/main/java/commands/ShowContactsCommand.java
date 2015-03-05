package commands;

import business.Service;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;
import business.service.ShowList;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import commands.commandexception.CommandFatalException;
import listhandler.ContactListHandler;
import listhandler.ListHandler;
import org.apache.log4j.Logger;
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
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            Service showService = new ShowList();
            ArrayList<Contact> contacts = (ArrayList<Contact>)showService.service(null);
            if(contacts != null) {
                int count = Integer.parseInt(bundle.getString("count"));
                int pageCount = contacts.size() / count;
                if(contacts.size() % count != 0){
                    pageCount++;
                }
                req.getSession().setAttribute("pageCount", pageCount);
                String str = req.getParameter("page");
                Integer page = str == null ? null : Integer.parseInt(str);
                if (page == null || page < 1 || page > pageCount) {
                    page = 1;
                }
                int start = (page - 1) * count;
                ListHandler<Contact> handler = new ContactListHandler();
                contacts = handler.handleList(contacts, start, count);
            }
            req.getSession().setAttribute("contacts", contacts);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            dispatcher.forward(req, resp);
        }
        catch(IOException e)
        {
            throw new ServletException();
        }
        catch(ServiceDataException e)
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
        catch(ServiceFatalException e)
        {
            throw new CommandFatalException(e);
        }
    }
}
