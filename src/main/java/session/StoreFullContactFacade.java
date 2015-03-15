package session;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import business.factory.KateBllFactory;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import transfer.*;
import upload.UploadHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Катерина on 07.03.2015.
 */
public class StoreFullContactFacade {

    private static Logger logger = Logger.getLogger(StoreFullContactFacade.class);

    public void store(HttpServletRequest req, String idString) throws FacadeFatalException, FacadeServiceException {

        logger.info(" - [ENTERING METHOD store(HttpRequest req), PARAMETERS: HttpRequest req]");

        Contact contact = new Contact();
        Integer id = null;
        if (StringUtils.isNotEmpty(idString)) {
            id = Integer.parseInt(idString);
        }

        contact.setId(id);

        String firstName = getAttributeString(req, "firstName");
        if (StringUtils.isNotEmpty(firstName)) {
            contact.setFirstName(firstName);
        }

        String secondName = getAttributeString(req, "secondName");
        if (StringUtils.isNotEmpty(secondName)) {
            contact.setSecondName(secondName);
        }

        String nameByFather = getAttributeString(req, "nameByFather");
        if (StringUtils.isNotEmpty(nameByFather)) {
            contact.setNameByFather(nameByFather);
        }

        String day = getAttributeString(req, "day");
        String month = getAttributeString(req, "month");
        String year = getAttributeString(req, "year");
        if (StringUtils.isNotEmpty(day) && StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(year)) {
            if (StringUtils.isNumeric(day) && StringUtils.isNumeric(month) && StringUtils.isNumeric(year)) {
                contact.setDateOfBirth(new DateTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0));
            }
        }
        String checkedSex = getAttributeString(req, "checkedSex");

        if (StringUtils.isNotEmpty(checkedSex)) {
            Sex sex = Sex.valueOf(checkedSex);
            contact.setSex(sex);
        }

        String sitizenship = getAttributeString(req, "sitizenship");
        if (StringUtils.isNotEmpty(sitizenship)) {
            contact.setSitizenship(sitizenship);
        }

        String webSite = getAttributeString(req, "webSite");
        if (StringUtils.isNotEmpty(webSite)) {
            contact.setWebSite(webSite);
        }

        String email = getAttributeString(req, "email");
        if (StringUtils.isNotEmpty(email)) {
            contact.setEmail(email);
        }

        String company = getAttributeString(req, "company");
        if (StringUtils.isNotEmpty(company)) {
            contact.setCompany(company);
        }

        Address address = new Address();
        String addressId = getAttributeString(req, "addressId");
        if (StringUtils.isNotEmpty(addressId)) {
            address.setId(Integer.parseInt(addressId));
        }

        String country = getAttributeString(req, "country");
        if (StringUtils.isNotEmpty(country)) {
            address.setCountry(country);
        }

        String city = getAttributeString(req, "city");
        if (StringUtils.isNotEmpty(city)) {
            address.setCity(city);
        }

        String street = getAttributeString(req, "street");
        if (StringUtils.isNotEmpty(street)) {
            address.setStreet(street);
        }

        String building = getAttributeString(req, "building");
        if (StringUtils.isNotEmpty(building)) {
            if (StringUtils.isNumeric(building)) {
                address.setBuilding(Integer.parseInt(building));
            }
        }

        String apartment = getAttributeString(req, "apartment");
        if (StringUtils.isNotEmpty(apartment)) {
            if (StringUtils.isNumeric(apartment)) {
                address.setApartment(Integer.parseInt(apartment));
            }
        }
        String index = getAttributeString(req, "index");
        if (StringUtils.isNotEmpty(index)) {
            if (StringUtils.isNumeric(index)) {
                address.setIndex(Long.parseLong(index));
            }
        }
        address.setContactId(id);
        contact.setAddressId(address.getId());
        contact.setAddress(address);

        String[] deletedTelephones = req.getAttribute("deletedTelephones") == null ? null : (String[])req.getAttribute("deletedTelephones");
        String[] telephoneIds = req.getAttribute("telephoneIds") == null ? null : (String[])req.getAttribute("telephoneIds");
        String[] countryCodes = req.getAttribute("countryCodes") == null ? null : (String[])req.getAttribute("countryCodes");
        String[] operatorCodes = req.getAttribute("operatorCodes") == null ? null : (String[])req.getAttribute("operatorCodes");
        String[] telephoneNumbers = req.getAttribute("telephoneNumbers") == null ? null : (String[])req.getAttribute("telephoneNumbers");
        String[] telephoneTypes = req.getAttribute("telephoneTypes") == null ? null : (String[])req.getAttribute("telephoneTypes");
        String[] telephoneComments = req.getAttribute("telephoneComments") == null ? null : (String[])req.getAttribute("telephoneComments");
        ArrayList<Telephone> telephones = null;
        if (ArrayUtils.isNotEmpty(telephoneIds)) {
            telephones = new ArrayList<Telephone>();
            for (int i = 0; i < telephoneIds.length; i++) {
                Telephone telephone = new Telephone();
                telephone.setContactId(id);
                if (StringUtils.isNotEmpty(telephoneIds[i])) {
                    telephone.setId(Integer.parseInt(telephoneIds[i]));
                }

                if (StringUtils.isNotEmpty(countryCodes[i])) {
                    telephone.setCountryCode(Integer.parseInt(countryCodes[i]));
                }

                if (StringUtils.isNotEmpty(operatorCodes[i])) {
                    telephone.setOperatorCode(Integer.parseInt(operatorCodes[i]));
                }

                if (StringUtils.isNotEmpty(telephoneNumbers[i])) {
                    telephone.setTelephoneNumber(Long.parseLong(telephoneNumbers[i]));
                }

                if (StringUtils.isNotEmpty(telephoneTypes[i])) {
                    telephone.setType(TelephoneType.valueOf(telephoneTypes[i]));
                }

                if (StringUtils.isNotEmpty(telephoneComments[i])) {
                    telephone.setComment(telephoneComments[i]);
                }
                telephones.add(telephone);
            }
            if(ArrayUtils.isNotEmpty(deletedTelephones)){
                for(int i = 0; i < deletedTelephones.length; i++){
                    if(StringUtils.isNotEmpty(deletedTelephones[i])) {
                        for(Telephone telephone : telephones) {
                            if(telephone.getId() != null && telephone.getId() == Integer.parseInt(deletedTelephones[i])) {
                                telephone.setDeleted(true);
                            }
                        }
                    }
                }
            }
        }


        contact.setTelephones(telephones);

        String[] deletedAttachments = req.getAttribute("deletedAttachments") == null ? null : (String[])req.getAttribute("deletedAttachments");
        String[] attachmentIds = req.getAttribute("attachmentIds") == null ? null : (String[])req.getAttribute("attachmentIds");
        String[] fileNames = req.getAttribute("fileNames") == null ? null : (String[])req.getAttribute("fileNames");
        String[] formattedUploadDates = req.getAttribute("formattedUploadDates") == null ? null : (String[])req.getAttribute("formattedUploadDates");
        String[] attachmentComments = req.getAttribute("attachmentComments") == null ? null : (String[])req.getAttribute("attachmentComments");

        String attachmentId = getAttributeString(req, "attachmentId") == null ? null : getAttributeString(req, "attachmentId");
        String fileName = null;
        if(req.getAttribute("fileName") != null) {
            fileName = (String)req.getAttribute("fileName");
            fileName = StringUtils.isEmpty(fileName) ? null : fileName;
        }
        String uploadDateEntered = getAttributeString(req, "uploadDate") == null ? null : getAttributeString(req, "uploadDate");
        String attachmentComment = getAttributeString(req, "attachmentComment") == null ? null : getAttributeString(req, "attachmentComment");

        Attachment someAttachment = null;

        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm:ss, dd.MM.YYYY");
        if (StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(uploadDateEntered)) {
            someAttachment = new Attachment();
            someAttachment.setContactId(id);
            if (StringUtils.isNotEmpty(attachmentId)) {
                someAttachment.setId(Integer.parseInt(attachmentId));
            }
            someAttachment.setUploadDate(format.parseDateTime(uploadDateEntered));
            someAttachment.setFileName(fileName);
            if (StringUtils.isNotEmpty(attachmentComment)) {
                someAttachment.setComment(attachmentComment);
            }
        }

        ArrayList<Attachment> attachments = null;

        if (ArrayUtils.isNotEmpty(attachmentIds)) {
            attachments = new ArrayList<Attachment>();
            for (int i = 0; i < attachmentIds.length; i++) {
                Attachment localAttachment = new Attachment();
                localAttachment.setContactId(id);

                if (StringUtils.isNotEmpty(attachmentIds[i])) {
                    localAttachment.setId(Integer.parseInt(attachmentIds[i]));
                }

                if (StringUtils.isNotEmpty(fileNames[i])) {
                    localAttachment.setFileName(fileNames[i]);
                }

                if (StringUtils.isNotEmpty(formattedUploadDates[i])) {
                    localAttachment.setUploadDate(format.parseDateTime(formattedUploadDates[i]));
                }

                if (StringUtils.isNotEmpty(attachmentComments[i])) {
                    localAttachment.setComment(attachmentComments[i]);
                }
                attachments.add(localAttachment);
            }
            if(ArrayUtils.isNotEmpty(deletedAttachments)){
                for(int i = 0; i < deletedAttachments.length; i++){
                    if(StringUtils.isNotEmpty(deletedAttachments[i])) {
                        for(Attachment attachment : attachments) {
                            if(attachment.getId() != null && attachment.getId() == Integer.parseInt(deletedAttachments[i])) {
                                attachment.setDeleted(true);
                            }
                        }
                    }
                }
            }
        }

        if (CollectionUtils.isNotEmpty(attachments)) {
            contact.setAttachments(attachments);
            if (someAttachment != null) {
                contact.getAttachments().add(someAttachment);
            }
        } else if (someAttachment != null) {
            contact.getAttachments().add(someAttachment);
        }

        if(someAttachment != null) {
            saveNewAttachment(someAttachment);
        }

        if(StringUtils.isNotEmpty(getAttributeString(req, "photoUrl"))){
            contact.setPhotoUrl(getAttributeString(req, "photoUrl"));
        }
        else if(StringUtils.isNotEmpty(fileName) && StringUtils.isEmpty(uploadDateEntered)) {
            contact.setPhotoUrl(fileName);
        }

        req.getSession().setAttribute("contact", contact);

    }

    private void saveNewAttachment(Attachment attachment) throws FacadeServiceException, FacadeFatalException{

        try {
            logger.info(" - [ ENTERING METHOD saveNewAttachment(Attachment attachment), PARAMETERS: Attachment attachment = " + attachment + "]");
            AbstractDAOFactory daoFactory = new MySQLDAOFactory();
            AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
            Business<Attachment> attachmentBusiness = bllFactory.getAttachmentBusiness();
            attachmentBusiness.createObject(attachment);
        }
        catch (BLLDataException e){
            throw new FacadeServiceException(e);
        }
        catch (BLLFatalException e){
            throw new FacadeFatalException(e);
        }
    }

    private String getAttributeString(HttpServletRequest req, String attribute){

        if(req.getAttribute(attribute) == null){
            return null;
        }
        return ((String[])req.getAttribute(attribute))[0];
    }
}