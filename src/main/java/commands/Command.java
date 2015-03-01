package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Катерина on 20.02.2015.
 */

public interface Command
{
    void process(HttpServletRequest req, HttpServletResponse resp);
}
