package business.factory;

import business.AbstractBLLFactory;
import business.Business;
import business.factory.katebusiness.AddressBO;
import business.factory.katebusiness.AttachmentBO;
import business.factory.katebusiness.ContactBO;
import business.factory.katebusiness.TelephoneBO;
import data.AbstractDAOFactory;
import transfer.Address;
import transfer.Attachment;
import transfer.Contact;
import transfer.Telephone;

/**
 * Created by Катерина on 04.03.2015.
 */
public class KateBllFactory extends AbstractBLLFactory{

    public KateBllFactory(AbstractDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Business<Address> getAddressBusiness() {
        return new AddressBO(daoFactory);
    }

    @Override
    public Business<Attachment> getAttachmentBusiness() {
        return new AttachmentBO(daoFactory);
    }

    @Override
    public Business<Contact> getContactBusiness() {
        return new ContactBO(daoFactory);
    }

    @Override
    public Business<Telephone> getTelephoneBusiness() {
        return new TelephoneBO(daoFactory);
    }
}
