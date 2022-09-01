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
import java.util.ArrayList;
import java.util.Collections;

public class RecordsUsersCommand implements Command {
    private static final Logger log = Logger.getLogger(RecordsUsersCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        int page = 1;
        int recordsPerPage = 6;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        IUserService userService = new UserServiceImpl();
        ArrayList<User> list = userService.getRecords((page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = userService.getAllUsers().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (request.getParameter("a-z") != null){
            Collections.sort(list, (User u1, User u2) ->{
                return u1.getLogin().compareToIgnoreCase(u2.getName());
            });
        }
        if (request.getParameter("z-a") != null){
            Collections.sort(list, (User u1, User u2) ->{
                return u2.getLogin().compareToIgnoreCase(u1.getName());
            });
        }

        request.setAttribute("user_list", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        log.debug("Commands finished");
        return "users.jsp";
    }
}
