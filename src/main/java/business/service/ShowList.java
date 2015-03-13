package business.service;

import business.AbstractBLLFactory;
import business.Business;
import business.Service;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;
import business.factory.KateBllFactory;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import transfer.Contact;

import java.util.ArrayList;

/**
 * Created by Катерина on 05.03.2015.
 */
public class ShowList implements Service {

    private static Logger logger = Logger.getLogger(ShowList.class);

    @Override
    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        logger.info(" - [ENTERING METHOD service(Object params), PARAMETERS: Object params = " + params + "]");

        ArrayList<Contact> contacts = null;
        try {
            AbstractDAOFactory daoFactory = new MySQLDAOFactory();
            AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
            Business<Contact> contactBO = bllFactory.getContactBusiness();
            contacts = contactBO.readAllObjects();
        } catch (BLLDataException e) {
            throw new ServiceDataException(e);
        } catch (BLLFatalException e) {
            throw new ServiceFatalException(e);
        }
        return contacts;
    }
}
