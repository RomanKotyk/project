package commands.admin;

import commands.Command;
import entity.User;
import org.apache.log4j.Logger;
import services.IUserService;
import services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusUserCommand implements Command {
    private static final Logger log = Logger.getLogger(StatusUserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        int id_user = Integer.parseInt(request.getParameter("user_id"));

        IUserService userService = new UserServiceImpl();
        User user = userService.getUserById(id_user);

        if (user.isStatus()){
            user.setStatus(false);
        }
        else{
            user.setStatus(true);
        }

        userService.updateStatus(user);

        log.debug("Commands finished");
        return "controller?action=users";
    }
}
