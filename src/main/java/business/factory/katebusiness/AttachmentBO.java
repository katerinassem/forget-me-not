package business.factory.katebusiness;

import business.Business;
import data.AbstractDAOFactory;
import org.apache.log4j.Logger;
import transfer.Attachment;

/**
 * Created by Катерина on 04.03.2015.
 */
public class AttachmentBO extends Business<Attachment>{

    private static Logger logger = Logger.getLogger(AddressBO.class);

    public AttachmentBO(AbstractDAOFactory daoFactory) {
        super(daoFactory);
        logger.info(" [ENTERING AttachmentBO CONSTRUCTOR]");
        entityDAO = daoFactory.getAttachmentDAO();
    }

}
