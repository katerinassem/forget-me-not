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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Address;
import transfer.Contact;

/**
 * Created by Катерина on 12.03.2015.
 */
public class EmailsString implements Service {

    private static Logger logger = Logger.getLogger(EmailsString.class);

    @Override
    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        logger.info(" - [ENTERING METHOD service(Object params), PARAMETERS: Object params = " + params + "]");

        Integer[] checkedIds = (Integer[]) params;
        String[] emails = new String[checkedIds.length];
        AbstractDAOFactory daoFactory = new MySQLDAOFactory();
        AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
        Business<Contact> contactDAO = bllFactory.getContactBusiness();
        for (int i = 0; i < checkedIds.length; i++) {
            Contact contact = null;
            if (checkedIds[i] != null) {
                try {
                    if ((contact = contactDAO.getFullObjectById(checkedIds[i])) != null && StringUtils.isNotEmpty(contact.getEmail())) {
                        emails[i] = contact.getEmail();
                    }
                } catch (BLLDataException e) {
                    logger.info(e);
                } catch (BLLFatalException e) {
                    logger.info(e);

                }
            }
        }
        String emailsString = "";
        StringBuilder sb = new StringBuilder(emailsString);
        for (int i = 0; i < emails.length; i++) {
            if(StringUtils.isNotEmpty(emails[i])) {
                sb.append(emails[i]);
                sb.append(";");
            }
        }
        emailsString = sb.toString();
        return emailsString;
    }
}
