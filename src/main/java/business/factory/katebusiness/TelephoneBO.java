package business.factory.katebusiness;

import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.log4j.Logger;
import transfer.Telephone;

/**
 * Created by Катерина on 04.03.2015.
 */
public class TelephoneBO extends Business<Telephone> {

    private static Logger logger = Logger.getLogger(AddressBO.class);

    public TelephoneBO(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        logger.info(" [ENTERING TelephoneBO CONSTRUCTOR]");
        entityDAO = daoFactory.getTelephoneDAO();
    }

    @Override
    public int createObject(Telephone object) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: createObject(Telephone object), PARAMETERS: Telephone object = " + object + "]");
        int result = -1;
        if(!object.isDeleted()) {
            result = super.createObject(object);
        }
        return result;
    }

    @Override
    public boolean updateObject(Telephone object) throws BLLDataException, BLLFatalException {

        logger.info(" - [ENTERING METHOD: createObject(Telephone object), PARAMETERS: Telephone object = " + object + "]");
        boolean result = false;
        if(!object.isDeleted()) {
            result = super.updateObject(object);
        }
        else {
            super.deleteObjectById(object.getId());
        }
        return result;
    }

}
