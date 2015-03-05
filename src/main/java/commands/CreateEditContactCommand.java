package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.ServiceFatalException;
import business.factory.KateBllFactory;
import commands.commandexception.CommandFatalException;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class CreateEditContactCommand implements Command
{
    private static Logger logger = Logger.getLogger(SendEmailCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        try {
            req.getSession().removeAttribute("contact");
            String opt = req.getParameter("option");
            if(StringUtils.isNotEmpty(opt) && StringUtils.equalsIgnoreCase(opt, "edit")){
                String idString = req.getParameter("id");
                if(StringUtils.isNotEmpty(idString)){
                    int id = Integer.parseInt(idString);
                    AbstractDAOFactory daoFactory = new MySQLDAOFactory();
                    AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
                    Business<Contact> contactDAO = bllFactory.getContactBusiness();
                    Contact contact = contactDAO.getFullObjectById(id);
                    req.getSession().setAttribute("contact", contact);
                }
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
            dispatcher.forward(req, resp);
        }
        catch (BLLDataException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditContactCommand\n");
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно отобразить данные. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch (BLLFatalException e){
            throw new CommandFatalException(e);
        }
        catch (IOException e)
        {
           logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditContactCommand\n");
        }
    }
}
