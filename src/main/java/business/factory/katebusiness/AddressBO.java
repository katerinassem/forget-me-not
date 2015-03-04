package business.factory.katebusiness;

import business.Business;
import data.AbstractDAOFactory;
import org.apache.log4j.Logger;
import transfer.Address;

/**
 * Created by Катерина on 04.03.2015.
 */
public class AddressBO extends Business<Address> {

    private static Logger logger = Logger.getLogger(AddressBO.class);

    public AddressBO(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        logger.info(" [ENTERING AddressBO CONSTRUCTOR]");
        entityDAO = daoFactory.getAddressDAO();
    }

}
