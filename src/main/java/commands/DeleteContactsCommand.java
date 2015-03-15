package commands;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import session.DeleteContactsFacade;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class DeleteContactsCommand implements Command
{
    private static Logger logger = Logger.getLogger(DeleteContactsCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETRES: HttpServletRequest req, HttpServletResponse resp]");
        req.getSession().removeAttribute("infoMessage");

        try
        {
            String[] checked = req.getParameterValues("checkbox");
            if(ArrayUtils.isNotEmpty(checked)) {
                Integer[] checkedIds = new Integer[checked.length];
                for (int i = 0; i < checked.length; i++) {
                    if (StringUtils.isNotEmpty(checked[i])) {
                        Integer checkedId = Integer.parseInt(checked[i]);
                        checkedIds[i] = checkedId;
                    }
                }
                DeleteContactsFacade deleteContactsFacade = new DeleteContactsFacade();
                deleteContactsFacade.delete(req.getSession(), checkedIds);
            }
            else {
                req.setAttribute("infoMessage", "Не выбраны контакты для удаления.");
            }
            req.getSession().removeAttribute("contact");
            resp.sendRedirect("Front?command=ShowContactsCommand");
            //RequestDispatcher dispatcher = req.getRequestDispatcher("ContactList.jsp");
            //dispatcher.forward(req, resp);
        }
        catch (FacadeFatalException e){
            throw new CommandFatalException(e);
        }
        catch (FacadeServiceException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно удалить данные. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch(IOException e)
        {
            throw new ServletException(e);
        }
    }
}
