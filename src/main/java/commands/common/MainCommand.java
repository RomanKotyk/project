package commands.common;

import commands.Command;
import entity.Tariff;
import org.apache.log4j.Logger;
import services.ITariffService;
import services.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MainCommand implements Command {
    private static final Logger log = Logger.getLogger(MainCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        ITariffService tariffService = new TariffServiceImpl();
        ArrayList<Tariff> tariffArrayList = tariffService.getBestTariffs();
        request.setAttribute("best_tariffs", tariffArrayList);
        log.debug("Commands finished");
        return "main.jsp";
    }
}
