import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        commandHelper.dispatchRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}