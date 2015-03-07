package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.ServiceFatalException;
import business.factory.KateBllFactory;
import commands.commandexception.CommandFatalException;
import data.AbstractDAOFactory;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class CreateEditContactCommand implements Command
{
    private static Logger logger = Logger.getLogger(SendEmailCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        logger.info(" - [ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp]");
        try {
            String opt = req.getParameter("option");
            String idString = req.getParameter("id");
            if(StringUtils.isNotEmpty(opt) && StringUtils.equalsIgnoreCase(opt, "editmore")){
                //  Пересохраняем данные с формы(+ изменившиеся)
                //  Не в базу!
                Integer id = Integer.parseInt(idString);
                String firstName = req.getParameter("firstName");
                String secondName = req.getParameter("secondName");
                String nameByFather = req.getParameter("nameByFather");
                String day = req.getParameter("day");
                String month = req.getParameter("month");
                String year = req.getParameter("year");
                String checkedSex = req.getParameterValues("checkedSex")[0];
                String sitizenships = req.getParameter("sitizenship");
                String webSite = req.getParameter("webSite");
                String email = req.getParameter("email");
                String company = req.getParameter("company");

                String country = req.getParameter("country");
                String city = req.getParameter("city");
                String street = req.getParameter("street");
                String building = req.getParameter("building");
                String apartment = req.getParameter("apartment");
                String index = req.getParameter("index");

                String[] telephoneIds = req.getParameterValues("telephoneIds");
                String[] fullNumbers = req.getParameterValues("fullNumbers");
                String[] telephoneTypes = req.getParameterValues("telephoneTypes");
                String[] telephoneComments = req.getParameterValues("telephoneComments");

                String[] attachmentIds = req.getParameterValues("attachmentIds");
                String[] uniqueNames = req.getParameterValues("uniqueNames");
                String[] fileNames = req.getParameterValues("fileNames");
                String[] formattedUploadDates = req.getParameterValues("formattedUploadDates");
                String[] attachmentComments = req.getParameterValues("attachmentComments");


                String attachmentId = req.getParameter("attachmentId");


                ///FILE!!!!!!!!!!!!!!!!!!!

                DateTime uploadDate = DateTime.now();
                String attachmentComment = req.getParameter("attachmentComment");
                //store(req);
            }
            else {
                req.getSession().removeAttribute("contact");
            }
            if(StringUtils.isNotEmpty(opt) && StringUtils.equalsIgnoreCase(opt, "edit")){
                if(StringUtils.isNotEmpty(idString)){
                    int id = Integer.parseInt(idString);
                    AbstractDAOFactory daoFactory = new MySQLDAOFactory();
                    AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
                    Business<Contact> contactDAO = bllFactory.getContactBusiness();
                    Contact contact = contactDAO.getFullObjectById(id);
                    if(contact == null){
                        throw new BLLDataException("Запрашиваемый контакт не найден.");
                    }
                    req.getSession().setAttribute("contact", contact);
                }
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditContact.jsp");
            dispatcher.forward(req, resp);
        }
        catch (BLLDataException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditContactCommand\n");
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно отобразить данные. Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
        catch (BLLFatalException e){
            throw new CommandFatalException(e);
        }
        catch (IOException e)
        {
           logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditContactCommand\n");
        }
    }
}
