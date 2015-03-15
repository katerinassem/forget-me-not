package commands;

import business.Service;
import business.bllexception.*;
import business.service.EmailsString;
import business.service.ResolveTemplate;
import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
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

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        
        req.getSession().removeAttribute("infoMessage");
        try {
            String checkedIds = null;
            Integer[] checkedIdsInteger = null;
            String option = req.getParameter("option");
            String emails = null;
            ArrayList<String> templateNames = new ArrayList<String>();
            ArrayList<String> templateContents = new ArrayList<String>();
            if (StringUtils.isEmpty(option) || StringUtils.equalsIgnoreCase(option, "get")) {
                String[] checked = req.getParameterValues("checkbox");
                if (ArrayUtils.isNotEmpty(checked)) {

                    checkedIds = "";
                    checkedIdsInteger = new Integer[checked.length];
                    StringBuilder sb = new StringBuilder(checkedIds);

                    for (int i = 0; i < checked.length; i++) {
                        if (StringUtils.isNotEmpty(checked[i])) {
                            Integer checkedId = Integer.parseInt(checked[i]);
                            sb.append(checkedId);
                            sb.append(";");
                            checkedIdsInteger[i] = checkedId;
                        }
                    }

                    checkedIds = sb.toString();

                    Service emailsStringService = new EmailsString();
                    emails = (String)emailsStringService.service(checkedIdsInteger);

                    req.setAttribute("emails", emails);
                    req.setAttribute("checkedIds", checkedIds);
                    templateNames = getTemplateNames();
                    templateContents = getTemplateContents();
                    req.setAttribute("templateNames", templateNames);
                    req.setAttribute("templateContents", templateContents);
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

            logger.error(e);
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

            logger.error(e);
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

    private ArrayList<String> getTemplateNames() throws ServiceFatalException, ServiceDataException{

        logger.info(" - [ENTERING help METHOD getTemplateNames(), NO PARAMETERS]");

        ArrayList<String> result = new ArrayList<String>();
        ResolveTemplate resolver = new ResolveTemplate();
        ST template = null;
        for(int i = 0; i < 2; i++) {
            template = (ST) resolver.service(i);
            if(template != null) {
                result.add(template.getName());
            }
        }
        return  result;
    }

    private ArrayList<String> getTemplateContents() throws ServiceFatalException, ServiceDataException{

        logger.info(" - [ENTERING help METHOD getTemplateContents(), NO PARAMETERS]");

        ArrayList<String> result = new ArrayList<String>();
        ResolveTemplate resolver = new ResolveTemplate();
        ST template = null;
        for(int i = 0; i < 2; i++) {
            template = (ST) resolver.service(i);
            if(template != null) {
                result.add(template.render());
            }
        }
        return  result;
    }
}
