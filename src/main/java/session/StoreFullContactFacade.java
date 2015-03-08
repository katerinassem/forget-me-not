package session;

import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
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
        if(StringUtils.isNotEmpty(idString)){
            id = Integer.parseInt(idString);
        }
        contact.setId(id);

        String firstName = req.getParameter("firstName");
        if(StringUtils.isNotEmpty(firstName)){
            contact.setFirstName(firstName);
        }

        String secondName = req.getParameter("secondName");
        if(StringUtils.isNotEmpty(secondName)){
            contact.setSecondName(secondName);
        }

        String nameByFather = req.getParameter("nameByFather");
        if(StringUtils.isNotEmpty(nameByFather)){
            contact.setNameByFather(nameByFather);
        }

        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        if(StringUtils.isNotEmpty(day) && StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(year)){
            if(StringUtils.isNumeric(day) && StringUtils.isNumeric(month) && StringUtils.isNumeric(year)){
                contact.setDateOfBirth(new DateTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0));
            }
        }
        String checkedSex = req.getParameterValues("checkedSex")[0];

        if(StringUtils.isNotEmpty(checkedSex)){
            Sex sex = Sex.valueOf(checkedSex);
            contact.setSex(sex);
        }

        String sitizenship = req.getParameter("sitizenship");
        if(StringUtils.isNotEmpty(sitizenship)){
            contact.setSitizenship(sitizenship);
        }

        String webSite = req.getParameter("webSite");
        if(StringUtils.isNotEmpty(webSite)){
            contact.setWebSite(webSite);
        }

        String email = req.getParameter("email");
        if(StringUtils.isNotEmpty(email)){
            contact.setEmail(email);
        }

        String company = req.getParameter("company");
        if(StringUtils.isNotEmpty(company)){
            contact.setCompany(company);
        }

        Address address = new Address();
        String addressId = req.getParameter("addressId");
        if(StringUtils.isNotEmpty(addressId)){
            address.setId(Integer.parseInt(addressId));
        }

        String country = req.getParameter("country");
        if(StringUtils.isNotEmpty(country)){
            address.setCountry(country);
        }

        String city = req.getParameter("city");
        if(StringUtils.isNotEmpty(city)){
            address.setCity(city);
        }

        String street = req.getParameter("street");
        if(StringUtils.isNotEmpty(street)){
            address.setStreet(street);
        }

        String building = req.getParameter("building");
        if(StringUtils.isNotEmpty(building)){
            if(StringUtils.isNumeric(building)){
                address.setBuilding(Integer.parseInt(building));
            }
        }

        String apartment = req.getParameter("apartment");
        if(StringUtils.isNotEmpty(apartment)) {
            if (StringUtils.isNumeric(apartment)) {
                address.setApartment(Integer.parseInt(apartment));
            }
        }
        String index = req.getParameter("index");
        if(StringUtils.isNotEmpty(index)) {
            if (StringUtils.isNumeric(index)) {
                address.setIndex(Long.parseLong(index));
            }
        }

        String[] telephoneIds = req.getParameterValues("telephoneIds");
        String[] countryCodes = req.getParameterValues("countryCodes");
        String[] operatorCodes = req.getParameterValues("operatorCodes");
        String[] telephoneNumbers = req.getParameterValues("telephoneNumbers");
        String[] telephoneTypes = req.getParameterValues("telephoneTypes");
        String[] telephoneComments = req.getParameterValues("telephoneComments");
        ArrayList<Telephone> telephones = null;
        if(ArrayUtils.isNotEmpty(telephoneIds)){
            telephones = new ArrayList<Telephone>();
            for(int i = 0; i < telephoneIds.length; i++){
                Telephone telephone = new Telephone();
                if(StringUtils.isNotEmpty(telephoneIds[i])){
                    telephone.setId(Integer.parseInt(telephoneIds[i]));
                }

                if(StringUtils.isNotEmpty(countryCodes[i])){
                    telephone.setCountryCode(Short.parseShort(countryCodes[i]));
                }

                if(StringUtils.isNotEmpty(operatorCodes[i])){
                    telephone.setOperatorCode(Short.parseShort(operatorCodes[i]));
                }

                if(StringUtils.isNotEmpty(telephoneNumbers[i])){
                    telephone.setTelephoneNumber(Long.parseLong(telephoneNumbers[i]));
                }

                if(StringUtils.isNotEmpty(telephoneTypes[i])){
                    telephone.setType(TelephoneType.valueOf(telephoneTypes[i]));
                }

                if(StringUtils.isNotEmpty(telephoneComments[i])){
                    telephone.setComment(telephoneComments[i]);
                }
                telephones.add(telephone);
            }
        }

        String[] attachmentIds = req.getParameterValues("attachmentIds");
        String[] fileNames = req.getParameterValues("fileNames");
        String[] formattedUploadDates = req.getParameterValues("formattedUploadDates");
        String[] attachmentComments = req.getParameterValues("attachmentComments");
        ArrayList<Attachment> attachments = null;

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
        String attachmentId = req.getParameter("attachmentId");

        UploadHelper uploadHelper = UploadHelper.getInstance();
        try {
            uploadHelper.upload(req);
        }catch (Exception e){
            logger.error(e);
        }
            ///FILE!!!!!!!!!!!!!!!!!!!

        String fileName = req.getParameter("fileName");
        String uploadDateEntered = req.getParameter("uploadDate");
        String attachmentComment = req.getParameter("attachmentComment");

        Attachment someAttachment = new Attachment();
        if(StringUtils.isNotEmpty(attachmentId)){
            someAttachment.setId(Integer.parseInt(attachmentId));
            someAttachment.setUploadDate(format.parseDateTime(uploadDateEntered));
        }
        else {
            someAttachment.setUploadDate(DateTime.now());
        }

        if(StringUtils.isNotEmpty(fileName)){
            someAttachment.setFileName(fileName);
        }

        if(StringUtils.isNotEmpty(attachmentComment)){
            someAttachment.setComment(attachmentComment);
        }

        if(ArrayUtils.isNotEmpty(attachmentIds)){
            attachments = CollectionUtils.isEmpty(attachments) ? new ArrayList<Attachment>() : attachments;
            for(int i = 0; i < attachmentIds.length; i++){
                Attachment attachment = new Attachment();

                if(StringUtils.isNotEmpty(attachmentIds[i])){
                    attachment.setId(Integer.parseInt(attachmentIds[i]));
                }

                if(StringUtils.isNotEmpty(fileNames[i])){
                    attachment.setFileName(fileNames[i]);
                }

                if(StringUtils.isNotEmpty(formattedUploadDates[i])){
                    attachment.setUploadDate(format.parseDateTime(formattedUploadDates[i]));
                }

                if(StringUtils.isNotEmpty(attachmentComments[i])){
                    attachment.setComment(attachmentComments[i]);
                }
                attachments.add(attachment);
            }
        }

    }
}
