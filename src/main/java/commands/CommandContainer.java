package commands;

import commands.admin.*;
import commands.common.*;
import commands.user.ProfileCommand;
import commands.user.ReplenishBalanceCommand;
import commands.user.SubscribeCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Holder for all commands
 *
 * @author R.Kotyk
 *
 */

public class CommandContainer {
    private static final Logger log = Logger.getLogger(CommandContainer.class);
    private static CommandContainer container = new CommandContainer();

    private Map<String, Command> commands = new HashMap<String, Command>();

    private CommandContainer() {

    }

    public static CommandContainer commandContainer() {
        if (container == null) {
            container = new CommandContainer();
        }
        return container;
    }

    {
        //common commands
        commands.put("main", new MainCommand());
        commands.put("services", new AllServicesTariffsCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("language", new UpdateLanguageCommand());
        commands.put("no_command", new NoCommand());
        commands.put("download_pdf", new DownloadPDFCommand());

        //user commands
        commands.put("replenish_balance_user", new ReplenishBalanceCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("subscribe", new SubscribeCommand());

        //admin commands
        commands.put("add_user", new AddUserCommand());
        commands.put("delete_user", new DeleteUserCommand());
        commands.put("status_user", new StatusUserCommand());
        commands.put("delete_tariff", new DeleteTariffCommand());
        commands.put("add_tariff", new AddTariffCommand());
        commands.put("edit_tariff", new EditTariffCommand());
        commands.put("users", new RecordsUsersCommand());


        commands.put(
                "get_records", new RecordsUsersCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name
     * @return Command object.
     */

    public Command getCommand(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null || !commands.containsKey(action)) {
            log.trace("Command not found, name --> " + action);
            return commands.get("no_command");
        }
        return commands.get(action);
    }
}
