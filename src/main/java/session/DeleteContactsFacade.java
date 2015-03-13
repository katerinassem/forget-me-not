package session;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.*;
import business.factory.KateBllFactory;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.http.HttpSession;

/**
 * Created by Катерина on 06.03.2015.
 */
public class DeleteContactsFacade {

    private static Logger logger = Logger.getLogger(DeleteContactsFacade.class);

    public void delete(HttpSession session, Integer[] checkedIds) throws FacadeFatalException, FacadeServiceException {

        logger.info(" - [ENTERING METHOD delete(HttpSession session, Integer[] checkedIds), PARAMETERS: HttpSession session, Integer[] checkedIds]");
        int i = 0;
        if(ArrayUtils.isEmpty(checkedIds)){
            session.setAttribute("infoMessage", "Не выбраны контакты для удаленияю.");
            return;
        }
        boolean success = true;
        AbstractDAOFactory daoFactory = new MySQLDAOFactory();
        AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
        try {
            Business<Contact> contactDAO = bllFactory.getContactBusiness();
            for( Integer checkedId : checkedIds) {
                if (checkedId != null) {
                    if(contactDAO.deleteObjectById(checkedId) != true){
                        success = false;
                    }
                    else {i++;}
                }
            }
            if(success != true){
                throw new BLLDataException("Cannot delete contact(s)");
            }
            session.setAttribute("infoMessage", "Удалено контактов: " + i);
        }
        catch (BLLDataException e){
            throw new FacadeServiceException(e);
        }
        catch (BLLFatalException e){
            throw new FacadeFatalException(e);
        }
    }
}

