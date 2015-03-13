package business.service;

import business.Service;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Катерина on 13.03.2015.
 */
public class ShowSearchResults implements Service {

    private static Logger logger = Logger.getLogger(ShowList.class);

    @Override

    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        logger.info(" - [ENTERING METHOD service(Object params), PARAMETERS: Object params = " + params + "]");

        ArrayList<Contact> contacts = null;
        HttpSession session = (HttpSession)params;
        try {
            contacts = (ArrayList<Contact>) session.getAttribute("contactsSearch");
        }
        catch (ClassCastException e){
            return new ServiceDataException(e);
        }
        return contacts;
    }
}
