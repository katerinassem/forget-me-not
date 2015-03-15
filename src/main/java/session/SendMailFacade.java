package session;

import business.AbstractBLLFactory;
import business.Business;
import business.Service;
import business.bllexception.*;
import business.factory.KateBllFactory;
import business.service.SendMail;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Катерина on 12.03.2015.
 */
public class SendMailFacade {

    private static Logger logger = Logger.getLogger(SendMailFacade.class);

    public void send(HttpServletRequest req) throws FacadeServiceException, FacadeFatalException{

        logger.info(" - [ENTERING METHOD send(HttpServletRequest req), PARAMETERS: HttpServletRequest req]");

        String checkedTemplate = req.getParameter("checkedTemplate");
        String ids = req.getParameter("checkedIds");
        String emails = req.getParameter("emails");
        String subject = req.getParameter("subject");
        String letter = req.getParameter("letter");
        if(StringUtils.isEmpty(ids) || StringUtils.isEmpty(emails) || StringUtils.isEmpty(subject) || (StringUtils.isEmpty(letter) && StringUtils.isEmpty("template"))){
            req.getSession().setAttribute("infoMessage", "Для отправки нужно письма нужно указать получателей, тему, текст письма(или выбрать шаблон)!");
            return;
        }

        String[] allEmails = StringUtils.split(emails, ";");
        String[] allIdsStrings = StringUtils.split(ids, ";");
        Integer[] allIds = new Integer[allIdsStrings.length];
        for (int i = 0; i < allIdsStrings.length; i++) {
            allIds[i] = Integer.parseInt(allIdsStrings[i]);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(allEmails);
        params.add(subject);
        params.add(letter);
        params.add(allIds);
        Integer templateId = null;
        if(StringUtils.isNotEmpty(checkedTemplate)){
            templateId = Integer.parseInt(checkedTemplate);
        }
        params.add(templateId);
        try {
            Service sendMailService = new SendMail();
            Integer count = (Integer) sendMailService.service(params);
            req.getSession().setAttribute("infoMessage", "Выслано писем: " + count);
        }
        catch (ServiceDataException e){
            throw new FacadeServiceException(e);
        }
        catch (ServiceFatalException e){
            throw new FacadeFatalException(e);
        }
    }
}
