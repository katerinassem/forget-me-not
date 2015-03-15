package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import business.factory.KateBllFactory;
import commands.commandexception.CommandFatalException;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import session.StoreFullContactFacade;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 06.03.2015.
 */
public class SaveContactCommand implements Command {

    private static Logger logger = Logger.getLogger(ShowContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException {

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        req.getSession().removeAttribute("infoMessage");

        try {
            StoreFullContactFacade storeFullContactFacade = new StoreFullContactFacade();
            String idString = (String[])req.getAttribute("id") == null ? null : ((String[])req.getAttribute("id"))[0];

            storeFullContactFacade.store(req, idString);
            Contact contact = (Contact)req.getSession().getAttribute("contact");
            if(contact == null){
                throw new FacadeServiceException("Nothing to save.");
            }
            AbstractDAOFactory daoFactory = new MySQLDAOFactory();
            AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
            Business<Contact> contactBusiness = bllFactory.getContactBusiness();
            if(contact.getId() == null){
                int res = contactBusiness.createObject(contact);
                if(res == -1){
                    req.getSession().setAttribute("infoMessage", "Не удалось сохранить контакт..");
                }
                else {
                    req.getSession().setAttribute("infoMessage", "Новый контакт успешно сохранён.");
                }
            }
            else{
                boolean res = contactBusiness.updateObject(contact);
                if(res){
                    req.getSession().setAttribute("infoMessage", "Изменения успешно сохранены.");
                }
                else {
                    req.getSession().setAttribute("infoMessage", "Не удалось сохранить изменения..");
                }
            }

            resp.sendRedirect("Front?command=ShowContactsCommand");
        }
        catch (BLLDataException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно сохранить данные. Попытайтесь ещё.");
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
        catch (FacadeFatalException e){
            throw new CommandFatalException(e);
        }
        catch (FacadeServiceException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно сохранить данные. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch (IOException e){
            throw new ServletException(e);
        }
    }
}
