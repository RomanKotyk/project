package web.servlets;

import utils.AsyncCommand;
import commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static commands.CommandContainer.commandContainer;

/**
 * Main servlet controller
 *
 * @author R.Kotyk
 *
 * */

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {
        AsyncCommand.startCommand();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(proccessRequest(req, resp)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(proccessRequest(req, resp));
    }

    /**
     * Main method of the this controller
     * */
    private String proccessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Controller starts");

        String commandName = req.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        Command command = commandContainer().getCommand(req, resp);
        log.trace("Obtained command --> " + command);

        String adress = command.execute(req, resp);
        log.trace("address --> " + adress);

        log.debug("Controller finished, address --> " + adress);

        return adress;

    }
}
