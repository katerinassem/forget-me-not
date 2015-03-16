import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import commands.commandexception.CommandFatalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import upload.UploadException;
import upload.UploadHelper;

@WebServlet("/upload/*")
public class FrontServlet extends HttpServlet
{
    private static Logger logger = Logger.getLogger(FrontServlet.class);

    static
    {
        new DOMConfigurator().doConfigure("log\\log4j.xml", LogManager.getLoggerRepository());
    }
    private final CommandHelper commandHelper = new CommandHelper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            cleanSessionAttributes(req.getSession());
            commandHelper.dispatchRequest(req, resp);
        }
        catch (UploadException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно загрузить данные на сервер.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
        catch (ServletException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка приложения! Приносим извинения за причиненные неудобства.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
        catch (CommandFatalException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка приложения! Приносим извинения за причиненные неудобства.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            cleanSessionAttributes(req.getSession());
            commandHelper.dispatchRequest(req, resp);
        }
        catch (ServletException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка приложения! Приносим извинения за причиненные неудобства.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
        catch (UploadException e){
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка! Невозможно загрузить данные на сервер.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
        catch (CommandFatalException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка приложения! Приносим извинения за причиненные неудобства.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void cleanSessionAttributes(HttpSession session){

        session.removeAttribute("errorMessage");
        session.removeAttribute("contacts");

    }
}