package session;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import org.apache.log4j.Logger;
import transfer.Contact;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Катерина on 07.03.2015.
 */
public class StoreFullContactFacade {

    private static Logger logger = Logger.getLogger(StoreFullContactFacade.class);

    public void store(HttpServletRequest req) throws FacadeFatalException, FacadeServiceException {

        logger.info(" - [ENTERING METHOD store(HttpRequest req), PARAMETERS: HttpRequest req]");
        Contact contact = new Contact();
    }
}
