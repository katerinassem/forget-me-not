package business;

import data.AbstractDAOFactory;
import transfer.*;

/**
 * Created by Катерина on 04.03.2015.
 */
public abstract class AbstractBLLFactory {

    protected AbstractDAOFactory daoFactory;

    public AbstractBLLFactory(AbstractDAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public abstract Business<Address> getAddressBusiness();
    public abstract Business<Attachment> getAttachmentBusiness();
    public abstract Business<Contact> getContactBusiness();
    public abstract Business<Telephone> getTelephoneBusiness();
}
