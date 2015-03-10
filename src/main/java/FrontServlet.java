import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import commands.commandexception.CommandFatalException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

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
            req.getSession().removeAttribute("error");
            commandHelper.dispatchRequest(req, resp);
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
            req.getSession().removeAttribute("error");
            commandHelper.dispatchRequest(req, resp);
        }
        catch (CommandFatalException e)
        {
            logger.error(e);
            req.getSession().setAttribute("errorMessage", "Ошибка приложения! Приносим извинения за причиненные неудобства.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}