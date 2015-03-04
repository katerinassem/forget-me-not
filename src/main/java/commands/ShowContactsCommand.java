package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.factory.KateBllFactory;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Катерина on 20.02.2015.
 */
public class ShowContactsCommand implements Command
{

    private static Logger logger = Logger.getLogger(ShowContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException
    {
        try
        {
            AbstractDAOFactory daoFactory = new MySQLDAOFactory();
            AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
            Business<Contact> contactBO = bllFactory.getContactBusiness();
            ArrayList<Contact> contacts = contactBO.readAllObjects();
            req.getSession().setAttribute("contacts", contacts);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            dispatcher.forward(req, resp);
        }
        catch(IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
        }
        catch(BLLDataException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
        }
        catch(BLLFatalException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
        }
    }
}
