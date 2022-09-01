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
import java.util.ArrayList;

public class AllTariffsCommand implements Command {
    private static final Logger log = Logger.getLogger(AllTariffsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        IUserService userService = new UserServiceImpl();

        User user = (User)request.getSession().getAttribute("user");
        ArrayList<Tariff> tariffArrayList = userService.getSubscriptions(user);
        request.setAttribute("user_tariffs", tariffArrayList);

        log.debug("Commands finished");
        return "profile.jsp";

    }
}
