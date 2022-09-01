package commands.admin;

import commands.Command;
import dao.DAOFactory;
import dao.TariffDAO;
import dao.UserDAO;
import entity.Tariff;
import org.apache.log4j.Logger;
import services.ITariffService;
import services.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTariffCommand implements Command {
    private static final Logger log = Logger.getLogger(DeleteTariffCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        int id_tariff = Integer.parseInt(request.getParameter("id_tariff"));
        Tariff tariff = new Tariff();
        tariff.setId(id_tariff);

        ITariffService tariffService = new TariffServiceImpl();
        tariffService.deleteTariff(tariff);

        log.debug("Commands finished");
        return "controller?action=services";
    }
}
