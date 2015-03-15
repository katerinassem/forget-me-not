package commands;

import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import upload.DownloadException;
import upload.UploadHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Катерина on 16.03.2015.
 */
public class DownloadImageCommand implements Command {

    private  static Logger logger = Logger.getLogger(ChoosePhotoCommand.class);

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws CommandFatalException, ServletException {
        String fileName = req.getParameter("fileName");
        String contactId = req.getParameter("contactId");
        Integer id = null;
        logger.info(" - [ ENTERING METHOD process(HttpServletRequest req, HttpServletResponse resp), PARAMETERS: HttpServletRequest req, HttpServletResponse resp, ATTRIBUTES: fileName = " + String.valueOf(fileName) + ", contactId = " + contactId + " ]");

        UploadHelper uploadHelper = UploadHelper.getInstance();
        try {
            if(StringUtils.isNotEmpty(contactId)){
                id = Integer.parseInt(contactId);
            }
            uploadHelper.downloadImage(fileName, id, resp);
        }
        catch (DownloadException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно загрузить файл! Попытайтесь ещё.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            try {
                dispatcher.forward(req, resp);
            }
            catch (IOException e1){
                throw new ServletException();
            }
        }
    }
}
