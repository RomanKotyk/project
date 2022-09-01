package commands.user;

import commands.Command;
import commands.common.UpdateLanguageCommand;
import dao.DAOFactory;
import dao.UserDAO;
import entity.Tariff;
import entity.User;
import org.apache.log4j.Logger;
import services.IUserService;
import services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCommand implements Command {
    private static final Logger log = Logger.getLogger(ProfileCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        User user = (User) request.getSession().getAttribute("user");

        IUserService userService = new UserServiceImpl();
        user.setTariffList(userService.getSubscriptions(user));

        request.getSession().setAttribute("user", user);
        log.debug("Commands finished");
        return "profile.jsp";
    }
}
