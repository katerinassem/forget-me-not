package business.factory.katebusiness;

import business.Business;
import data.AbstractDAOFactory;
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

}
