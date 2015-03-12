package commands;

import business.Service;
import business.bllexception.*;
import business.service.EmailsString;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import session.SendMailFacade;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Катерина on 22.02.2015.
 */
public class SendEmailCommand implements Command
{
    private static Logger logger = Logger.getLogger(SendEmailCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        try {
            Integer[] checkedIds = null;
            String option = req.getParameter("option");
            String emails = null;
            if (StringUtils.isEmpty(option) || StringUtils.equalsIgnoreCase(option, "get")) {
                String[] checked = req.getParameterValues("checkbox");
                if (ArrayUtils.isNotEmpty(checked)) {

                    checkedIds = new Integer[checked.length];

                    for (int i = 0; i < checked.length; i++) {
                        if (StringUtils.isNotEmpty(checked[i])) {
                            Integer checkedId = Integer.parseInt(checked[i]);
                            checkedIds[i] = checkedId;
                        }
                    }

                    Service emailsStringService = new EmailsString();
                    emails = (String)emailsStringService.service(checkedIds);

                    req.setAttribute("emails", emails);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("SendEmail.jsp");
                    dispatcher.forward(req, resp);
                    return;
                }
            }
            SendMailFacade sendMailFacade = new SendMailFacade();
            sendMailFacade.send(req);
            resp.sendRedirect("Front?command=ShowContactsCommand");

        }
        catch (ServiceFatalException e){
            throw new CommandFatalException(e);
        }
        catch (ServiceDataException e){

            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SendEmailCommand\n");
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно выслать письма. Попытайтесь ещё.");
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
        catch (FacadeServiceException e){

            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class SendEmailCommand\n");
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно выслать письма. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch (IOException e)
        {
            throw new ServletException(e);
        }
    }
}
