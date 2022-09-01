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

public class DeleteUserCommand implements Command {
    private static final Logger log = Logger.getLogger(DeleteUserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("user_id")));

        IUserService userService = new UserServiceImpl();
        userService.deleteUser(user);

        log.debug("Commands finished");
        return "controller?action=users";
    }
}
