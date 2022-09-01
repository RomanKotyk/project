package commands.admin;

import commands.Command;
import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import services.IUserService;
import services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserCommand implements Command {
    private static final Logger log = Logger.getLogger(AddUserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        String userLogin = request.getParameter("login_user");
        String userName = request.getParameter("name_user");
        String userSurname = request.getParameter("surname_user");
        String userPassword = request.getParameter("password_user");
        double userBalance = Double.parseDouble(request.getParameter("balance_user"));

        User user = new User();
        user.setLogin(userLogin);
        user.setName(userName);
        user.setSurname(userSurname);
        user.setPassword(userPassword);
        user.setBalance(userBalance);
        if (request.getParameter("user_role").equals("Admin"))
            user.setAdmin(true);
        else{
            user.setAdmin(false);
        }

        IUserService userService = new UserServiceImpl();
        userService.addUser(user);

        log.debug("Commands finished");
        return "controller?action=users";
    }
}
