package session;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import business.service.ShowList;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.http.HttpSession;

/**
 * Created by Катерина on 06.03.2015.
 */
public class SaveContactFacade {

    private static Logger logger = Logger.getLogger(ShowList.class);

    public void save(HttpSession session, Contact contact) throws FacadeFatalException, FacadeServiceException {

        logger.info(" - [ENTERING METHOD show(HttpSession session, Integer page), PARAMETERS: HttpSession session, Contact contact = " + contact + "]");
    }
}
