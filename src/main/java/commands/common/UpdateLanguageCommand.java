package commands.common;

import commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class UpdateLanguageCommand implements Command {

    private static final Logger log = Logger.getLogger(UpdateLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        HttpSession session = request.getSession();

        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        String defaultLocale = "defaultLocale";

        if (request.getParameter("uk") != null) {
            Config.set(session, fmtLocale, "uk");
            session.setAttribute(defaultLocale, "uk");

        } else {
            Config.set(session, fmtLocale, "en");
            session.setAttribute(defaultLocale, "en");
        }
        log.debug("Commands finished");
        return "controller?action=main";
    }
}
