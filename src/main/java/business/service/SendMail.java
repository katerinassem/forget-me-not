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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.stringtemplate.v4.ST;
import transfer.Contact;

import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Катерина on 12.03.2015.
 */
public class SendMail implements Service {

    private static Logger logger = Logger.getLogger(SendMail.class);

    @Override
    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        logger.info(" - [ENTERING METHOD service(Object params), PARAMETERS: Object params = " + params + "]");

        ArrayList<Object> obj = (ArrayList<Object>) params;
        if (obj == null) {
            logger.info(" - [NOTHING TO SEND]");
            return null;
        }

        String[] emails = null;
        String text = null;
        String subject = null;
        Integer[] ids = null;
        Integer template = null;
        ST templateST = null;
        try {
            emails = (String[]) obj.get(0);
            subject = (String) obj.get(1);
            text = (String) obj.get(2);
            ids = (Integer[]) obj.get(3);
            template = (Integer) obj.get(4);

            if ((StringUtils.isEmpty(text) && template == null) || ArrayUtils.isEmpty(emails) || ArrayUtils.isEmpty(ids) || StringUtils.isEmpty(subject)) {
                logger.info(" - [NOTHING TO SEND]");
                return null;
            }
        } catch (Exception e) {
            logger.info(" - [NOTHING TO SEND]");
            return null;
        }

        if(template != null) {
            ResolveTemplate resolveService = new ResolveTemplate();
            templateST = (ST) resolveService.service(template);
        }

        ResourceBundle bundle = ResourceBundle.getBundle("config");
        String from = bundle.getString("from");
        final String password = bundle.getString("password");
        final String username = from;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "465"); // 465 587
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Integer count = 0;
        for (int i = 0; i < emails.length; i++) {

            String to = emails[i];
            logger.info(" - [CONNECTING TO MAIL SERVER..]");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    username, password);
                        }
                    });
            if (templateST != null) {
                text = formLetterFor(template, templateST, ids[i]);
                if(StringUtils.isEmpty(text)){
                    continue;
                }
            }

            try {
                logger.info(" - [SENDING EMAIL to " + to + " ...]");
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(text);

                // send message
                Transport.send(message);
                count++;
                logger.info(" - [SUCCESS!!! ]");
            } catch (AddressException e) {
                logger.error(e);
            } catch (MessagingException e) {
                logger.error(e);
            }
        }
        return count;
    }

    private String formLetterFor(int templateId, ST template, int recipientId){

        logger.info(" - [ENTERING help METHOD formLetterFor(int templateId, ST template, int recipientId), PARAMETERS:int templateId = " + templateId + ", ST template = " + template.toString() +", int recipientId = " + recipientId +"]");

        String letter = "";
        String firstName = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.YYYY");
        DateTime now = DateTime.now();
        String date = dateTimeFormatter.print(now);
        String address = "";
        try {
            AbstractDAOFactory daoFactory = new MySQLDAOFactory();
            AbstractBLLFactory bllFactory = new KateBllFactory(daoFactory);
            Business<Contact> contactDAO = bllFactory.getContactBusiness();
            Contact contact = null;
            if ((contact = contactDAO.getFullObjectById(recipientId)) != null) {
                firstName = contact.getFirstName();
                if((contact.getAddress()) != null) {
                    address = contact.getAddress().toString();
                }
            }

            template.add("firstName", firstName);
            template.add("date", date);
            template.add("address", address);
            letter = template.render();
            return letter;
        }
        catch (BLLDataException e) {
            logger.error(e);
            return null;
        }
        catch (BLLFatalException e) {
            logger.error(e);
            return  null;
        }
    }
}