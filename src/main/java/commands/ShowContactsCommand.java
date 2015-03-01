package commands;

import data.AbstractDAOFactory;
import data.DAO;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 20.02.2015.
 */
public class ShowContactsCommand implements Command
{

    private static Logger logger = Logger.getLogger(ShowContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            req.getSession().setAttribute("firstName", "Smirnov");
            AbstractDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
            DAO<Address> addrDAO = mySQLDAOFactory.getAddressDAO();
            Contact contact = new Contact();
            contact.setId(1);
            addrDAO.create(new Address(0, "Belarus", "Minsk", "Skripnikova", 7, 154, new Long(220019), contact));
            RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            dispatcher.forward(req, resp);
        }
        catch(IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
        }
        catch(ServletException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class ShowContactsCommand\n");
        }
    }
}
