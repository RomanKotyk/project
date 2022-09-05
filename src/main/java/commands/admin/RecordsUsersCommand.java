package commands.admin;

import commands.Command;
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
        String sorting = (request.getParameter("sort") == null) ? (String) request.getAttribute("sort") : request.getParameter("sort");

        ArrayList<User> list = userService.getRecords(sorting,(page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = userService.getAllUsers().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);


        request.setAttribute("user_list", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sort", sorting);
        log.debug("Commands finished");
        return "users.jsp";
    }
}
