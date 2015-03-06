package session;

import business.Service;
import business.bllexception.*;
import business.service.ShowList;
import listhandler.ContactListHandler;
import listhandler.ListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Катерина on 06.03.2015.
 */
public class ShowListFacade {

    private static Logger logger = Logger.getLogger(ShowList.class);

    public void show(HttpSession session, Integer page) throws FacadeFatalException, FacadeServiceException{

        logger.info(" - [ENTERING METHOD show(HttpSession session, Integer page), PARAMETERS: HttpSession session, Integer page = " + page + "]");
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Service showService = new ShowList();
        try {
            ArrayList<Contact> contacts = (ArrayList<Contact>) showService.service(null);
            if (contacts != null) {
                int count = StringUtils.isNotEmpty(bundle.getString("count")) ?
                        Integer.parseInt(bundle.getString("count")) : 1;
                int pageCount = contacts.size() / count;
                if (contacts.size() % count != 0) {
                    pageCount++;
                }
                session.setAttribute("pageCount", pageCount);
                if (page == null || page < 1 || page > pageCount) {
                    page = 1;
                }
                int start = (page - 1) * count;
                ListHandler<Contact> handler = new ContactListHandler();
                contacts = handler.handleList(contacts, start, count);
            }
            session.setAttribute("contacts", contacts);
        }
        catch (ServiceDataException e){
            throw new FacadeServiceException(e);
        }
        catch (ServiceFatalException e){
            throw new FacadeFatalException(e);
        }
    }
}
