package commands;

import business.AbstractBLLFactory;
import business.Business;
import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import business.bllexception.ServiceFatalException;
import business.factory.KateBllFactory;
import commands.commandexception.CommandFatalException;
import data.AbstractDAOFactory;
import data.DAO;
import data.factories.MySQLDAOFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import transfer.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 22.02.2015.
 */
public class CreateEditTelephoneCommand implements Command
{
    private static Logger logger = Logger.getLogger(CreateEditTelephoneCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException{

        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("CreateEditTelephone.jsp");
            dispatcher.forward(req, resp);
        }
        catch (IOException e)
        {
            logger.error(e + " - in method process(HttpServletRequest req, HttpServletResponse resp), class CreateEditTelephoneCommand\n");
        }
    }
}
