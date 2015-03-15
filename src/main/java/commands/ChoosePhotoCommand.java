package commands;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import session.StoreFullContactFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class ChoosePhotoCommand implements Command
{
    private  static Logger logger = Logger.getLogger(ChoosePhotoCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        logger.info(" - [ ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp ]");

        try {
            String idString = req.getAttribute("id") == null ? req.getParameter("id") : ((String[])req.getAttribute("id"))[0];
            req.getSession().removeAttribute("infoMessage");
            if(StringUtils.isNotEmpty(idString)) {
                StoreFullContactFacade storeFullContactFacade = new StoreFullContactFacade();
                storeFullContactFacade.store(req, idString);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
            dispatcher.forward(req, resp);
        }
        catch (FacadeServiceException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно сохранить фото!. Попытайтесь ещё.");
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
            logger.error(e);
        }
    }
}
