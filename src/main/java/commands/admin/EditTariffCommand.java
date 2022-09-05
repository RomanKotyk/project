package commands.admin;

import commands.Command;
import entity.Tariff;
import org.apache.log4j.Logger;
import services.ITariffService;
import services.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTariffCommand implements Command{
    private static final Logger log = Logger.getLogger(EditTariffCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");
        ITariffService tariffService = new TariffServiceImpl();

        int tariff_id = Integer.parseInt(request.getParameter("id_tariff"));
        Tariff tariff = tariffService.getTariffById(tariff_id);

        String tariffNewName = request.getParameter("name_tariff");
        String tariffNewDescription = request.getParameter("name_tariff");
        int tariffNewPrice = Integer.parseInt(request.getParameter("price_tariff"));

        tariff.setName(tariffNewName);
        tariff.setDescription(tariffNewDescription);
        tariff.setPrice(tariffNewPrice);

        tariffService.updateTariff(tariff);

        log.debug("Commands finished");
        return "controller?action=services";
    }
}
