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
import data.DAO;
import data.factories.MySQLDAOFactory;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import transfer.Contact;

import java.util.ArrayList;

/**
 * Created by Катерина on 11.03.2015.
 */
public class SearchList implements Service{

    private static Logger logger = Logger.getLogger(ShowList.class);

    @Override
    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        logger.info(" - [ENTERING METHOD service(Object params), PARAMETERS: Object params = " + params + "]");

        AbstractDAOFactory daoFactory = new MySQLDAOFactory();
        AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
        Business<Contact> contactBusiness = bllFactory.getContactBusiness();
        ArrayList<Contact> contacts = null;

        //  search
        try {
            ArrayList<Object> paramsArray = (ArrayList<Object>)params;
            Contact contact = (Contact)paramsArray.get(0);
            DateTime beforeDateParam = (DateTime)paramsArray.get(1);
            contacts = contactBusiness.searchAllObjects(contact, beforeDateParam);
        }
        catch (BLLDataException e){
            throw  new ServiceDataException(e);
        }
        catch (BLLFatalException e){
            throw new ServiceFatalException(e);
        }
        return contacts;
    }

}
