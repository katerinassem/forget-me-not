package commands;

import data.AbstractDAOFactory;
import data.DAO;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import transfer.Address;

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
            try {
                Address address = new Address(0, "Belarus", "Minsk", "Skripnikova", 7, 154, 220019l, 2);
                int generatedId = addrDAO.create(address);
                address = addrDAO.read(1);
                address.setIndex(null);
                boolean upd = addrDAO.update(address);
                generatedId = addrDAO.create(address);
                boolean del = addrDAO.delete(address);
                del = addrDAO.delete(address);
                address.setContactId(0);
            }
            catch (Exception e)
            {
                req.getSession().setAttribute("error", "Stricted operation with data. Entered data is invalid.");
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
