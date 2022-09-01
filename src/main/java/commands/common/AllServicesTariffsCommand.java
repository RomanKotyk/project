package commands.common;

import commands.Command;
import dao.DAOFactory;
import dao.ServiceDAO;
import dao.TariffDAO;
import entity.Service;
import entity.Tariff;
import org.apache.log4j.Logger;
import services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;

public class AllServicesTariffsCommand implements Command {
    private static final Logger log = Logger.getLogger(AllServicesTariffsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        ITariffService tariffService = new TariffServiceImpl();
        IServiceService serviceService = new ServiceServiceImpl();


        ArrayList<Service> serviceList = serviceService.getAllServices();
        for (Service service : serviceList) {
            ArrayList<Tariff> tariffArrayList = tariffService.getTariffsOfService(service);
            if (request.getParameter("a-z") != null){
                Collections.sort(tariffArrayList, (Tariff t1, Tariff t2) -> {
                    return t1.getName().compareToIgnoreCase(t2.getName());
                });
            }
            if (request.getParameter("z-a") != null){
                Collections.sort(tariffArrayList, (Tariff t1, Tariff t2) -> {
                    return t2.getName().compareToIgnoreCase(t1.getName());
                });
            }
            if (request.getParameter("expensive-cheap") != null){
                Collections.sort(tariffArrayList, (Tariff t1, Tariff t2) -> {
                    return  -t1.getPrice() + t2.getPrice();
                });
            }
            if (request.getParameter("cheap-expensive") != null){
                Collections.sort(tariffArrayList, (Tariff t1, Tariff t2) -> {
                    return t1.getPrice() - t2.getPrice();
                });
            }

            service.setTariffList(tariffArrayList);
        }
        request.setAttribute("service_tariffs", serviceList);
        log.debug("Commands finished");
        return "services.jsp";

    }
}
