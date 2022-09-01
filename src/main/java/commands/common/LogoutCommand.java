package commands.common;

import commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        request.getSession().removeAttribute("user");
        log.debug("Commands finished");
        return "controller?action=main";
    }
}
