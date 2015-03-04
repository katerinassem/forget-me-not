package business.factory.katebusiness;

import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import data.factories.mysql.MySQLConnector;
import org.apache.log4j.Logger;
import transfer.Attachment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
