package commands.admin;

import commands.Command;
import dao.DAOFactory;
import dao.ServiceDAO;
import dao.TariffDAO;
import entity.Service;
import entity.Tariff;
import org.apache.log4j.Logger;
import services.IServiceService;
import services.ITariffService;
import services.ServiceServiceImpl;
import services.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTariffCommand implements Command {
    private static final Logger log = Logger.getLogger(AddTariffCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        String tariffName = request.getParameter("name_tariff");
        String tariffDescription = request.getParameter("description_tariff");
        int tariffPrice = Integer.parseInt(request.getParameter("price_tariff"));

        Tariff tariff = new Tariff();
        tariff.setName(tariffName);
        tariff.setDescription(tariffDescription);
        tariff.setPrice(tariffPrice);

        String serviceName = request.getParameter("service_name_add");

        IServiceService serviceService = new ServiceServiceImpl();
        Service service = serviceService.getServiceByName(serviceName);

        tariff.setService_id(service.getId());

        ITariffService tariffService = new TariffServiceImpl();
        tariffService.addTarrif(tariff);

        log.debug("Commands finished");
        return "controller?action=services";
    }
}
