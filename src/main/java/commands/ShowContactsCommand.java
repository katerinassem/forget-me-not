package commands;

import data.AbstractDAOFactory;
import data.DAO;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Attachment;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
            req.getSession().setAttribute("firstName", "Smirnov");
            AbstractDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
            DAO<Address> addrDAO = mySQLDAOFactory.getAddressDAO();
            DAO<Attachment> attDAO = mySQLDAOFactory.getAttachmentDAO();
            DAO<Contact> contDAO = mySQLDAOFactory.getContactDAO();
            try {
                ArrayList<Address> l1 = addrDAO.readAll();
                //ArrayList<Attachment> l2 = attDAO.readAll();
                ArrayList<Contact> l3 = contDAO.readAll();
                Address address = new Address(0, "Belarus", "Minsk", "Skripnikova", 7, 154, 220019l, 2);
                int generatedId = addrDAO.create(address);
                address = addrDAO.read(1);
                address.setIndex(null);
                boolean upd = addrDAO.update(address);
                generatedId = addrDAO.create(address);
                boolean del = addrDAO.delete(address);
                del = addrDAO.delete(address);
                address.setContactId(0);
                generatedId = addrDAO.create(address);
            }
            catch (SQLException e)
            {
                req.getSession().setAttribute("error", "Stricted operation with data. Entered data is invalid.");
            }
            catch (ServletException e)
            {
                logger.error(" - Cannot access the application database");
                req.getSession().setAttribute("error", "Application error, cannot access the application database");
                throw new ServletException(e);
            }
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
