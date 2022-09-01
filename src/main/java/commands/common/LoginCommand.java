package commands.common;

import commands.Command;
import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import services.IUserService;
import services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        IUserService userService = new UserServiceImpl();
        User user = userService.checkLoginUser(login, password);

        String result_page = (user == null) ? "login.jsp" : "controller?action=main";
        if (user == null){
            request.getSession().setAttribute("notExists", "notExists");
        }
        else {
            request.getSession().setAttribute("user", user);
            request.getSession().removeAttribute("notExists");
        }
        log.debug("Commands finished");
        return result_page;
    }
}
