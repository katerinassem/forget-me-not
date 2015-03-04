package business.factory.katebusiness;

import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import org.apache.log4j.Logger;
import transfer.Contact;

/**
 * Created by Катерина on 04.03.2015.
 */
public class ContactBO extends Business<Contact>{

    private static Logger logger = Logger.getLogger(AddressBO.class);

    public ContactBO(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        logger.info(" [ENTERING AddressBO CONSTRUCTOR]");
        entityDAO = daoFactory.getContactDAO();
    }

    @Override
    public Contact getFullObjectById(int id) throws BLLDataException, BLLFatalException {
        return super.getFullObjectById(id);
    }
}
