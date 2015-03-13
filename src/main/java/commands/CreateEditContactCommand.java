package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.*;
import business.factory.KateBllFactory;
import commands.commandexception.CommandFatalException;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import session.StoreFullContactFacade;
import transfer.Contact;
import upload.UploadHelper;

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
            String opt = req.getParameter("option") == null ? ((String[])req.getAttribute("option"))[0] : req.getParameter("option");
            String idString = req.getParameter("id") == null ? ((String[])req.getAttribute("id"))[0] : req.getParameter("id");
            if(StringUtils.isNotEmpty(opt) && StringUtils.equalsIgnoreCase(opt, "editmore")){

                //  Пересохраняем данные с формы(+ изменившиеся)
                //  Не в базу!
                //  В базу только если был загружен файл, сохраняем ТОЛЬКО его
                StoreFullContactFacade storeFullContactFacade = new StoreFullContactFacade();
                storeFullContactFacade.store(req, idString);
                RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
                dispatcher.forward(req, resp);
                return;
            }
            else {
                req.getSession().removeAttribute("contact");
            }
            if(StringUtils.isNotEmpty(opt) && StringUtils.equalsIgnoreCase(opt, "edit")){
                if(StringUtils.isNotEmpty(idString)){
                    int id = Integer.parseInt(idString);
                    AbstractDAOFactory daoFactory = new MySQLDAOFactory();
                    AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
                    Business<Contact> contactDAO = bllFactory.getContactBusiness();
                    Contact contact = contactDAO.getFullObjectById(id);
                    if(contact == null){
                        throw new BLLDataException("Запрашиваемый контакт не найден.");
                    }
                    req.getSession().setAttribute("contact", contact);
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
                dispatcher.forward(req, resp);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
                dispatcher.forward(req, resp);
            }
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
        catch (FacadeServiceException e)
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
        catch (FacadeFatalException e){
            throw new CommandFatalException(e);
        }
        catch (IOException e)
        {
           logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditContactCommand\n");
        }
    }
}
