package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author R.Kotyk
 *
 */

public interface Command {

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);

}
