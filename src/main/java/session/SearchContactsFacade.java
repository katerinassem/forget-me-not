package session;

import business.Service;
import business.bllexception.FacadeFatalException;
import business.bllexception.FacadeServiceException;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;
import business.service.SearchList;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import transfer.Address;
import transfer.Contact;
import transfer.Sex;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Катерина on 11.03.2015.
 */
public class SearchContactsFacade {

    private static Logger logger = Logger.getLogger(SearchContactsFacade.class);

    public void search(HttpServletRequest req)throws FacadeFatalException, FacadeServiceException{

        Service searchListService = new SearchList();
        Contact searchContact = new Contact();
        DateTime beforeDateParam = null;
        String firstName = req.getParameter("firstName");
        if (StringUtils.isNotEmpty(firstName)) {
            searchContact.setFirstName(firstName);
        }

        String secondName = req.getParameter("secondName");
        if (StringUtils.isNotEmpty(secondName)) {
            searchContact.setSecondName(secondName);
        }

        String nameByFather = req.getParameter("nameByFather");
        if (StringUtils.isNotEmpty(nameByFather)) {
            searchContact.setNameByFather(nameByFather);
        }

        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        if (StringUtils.isNotEmpty(day) && StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(year)) {
            if (StringUtils.isNumeric(day) && StringUtils.isNumeric(month) && StringUtils.isNumeric(year)) {
                searchContact.setDateOfBirth(new DateTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0));
            }
        }

        String day1 = req.getParameter("day1");
        String month1 = req.getParameter("month1");
        String year1 = req.getParameter("year1");
        if (StringUtils.isNotEmpty(day1) && StringUtils.isNotEmpty(month1) && StringUtils.isNotEmpty(year1)) {
            if (StringUtils.isNumeric(day1) && StringUtils.isNumeric(month1) && StringUtils.isNumeric(year1)) {
                beforeDateParam = new DateTime(Integer.parseInt(year1), Integer.parseInt(month1), Integer.parseInt(day1), 0, 0);
            }
        }

        String[] checked = req.getParameterValues("checkedSex");
        if(ArrayUtils.isNotEmpty(checked)) {
            String checkedSex = checked[0];

            if (StringUtils.isNotEmpty(checkedSex)) {
                Sex sex = Sex.valueOf(checkedSex);
                searchContact.setSex(sex);
            }
        }

        String sitizenship = req.getParameter("sitizenship");
        if (StringUtils.isNotEmpty(sitizenship)) {
            searchContact.setSitizenship(sitizenship);
        }

        Address address = new Address();
        String addressId = req.getParameter("addressId");
        if (StringUtils.isNotEmpty(addressId)) {
            address.setId(Integer.parseInt(addressId));
        }

        String country = req.getParameter("country");
        if (StringUtils.isNotEmpty(country)) {
            address.setCountry(country);
        }

        String city = req.getParameter("city");
        if (StringUtils.isNotEmpty(city)) {
            address.setCity(city);
        }

        String street = req.getParameter("street");
        if (StringUtils.isNotEmpty(street)) {
            address.setStreet(street);
        }

        String building = req.getParameter("building");
        if (StringUtils.isNotEmpty(building)) {
            if (StringUtils.isNumeric(building)) {
                address.setBuilding(Integer.parseInt(building));
            }
        }

        String apartment = req.getParameter("apartment");
        if (StringUtils.isNotEmpty(apartment)) {
            if (StringUtils.isNumeric(apartment)) {
                address.setApartment(Integer.parseInt(apartment));
            }
        }
        String index = req.getParameter("index");
        if (StringUtils.isNotEmpty(index)) {
            if (StringUtils.isNumeric(index)) {
                address.setIndex(Long.parseLong(index));
            }
        }

        searchContact.setAddress(address);

        ArrayList<Contact> contacts = null;
        req.getSession().removeAttribute("contacts");
        try {
            ArrayList<Object> allParams = new ArrayList<Object>();
            allParams.add(searchContact);
            allParams.add(beforeDateParam);
            Object obj = searchListService.service(allParams);
            if(obj != null) {
                contacts = (ArrayList<Contact>) obj;
                req.getSession().setAttribute("contacts", contacts);
            }
        }
        catch (ServiceDataException e){
            throw new FacadeServiceException(e);
        }
        catch (ServiceFatalException e){
            throw new FacadeFatalException(e);
        }

    }
}
