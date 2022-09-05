package commands.common;

import commands.Command;
import utils.PDFBuilder;
import entity.Tariff;
import services.ITariffService;
import services.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadPDFCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id_tariff = Integer.parseInt(request.getParameter("id_tariff"));

        ITariffService tariffService = new TariffServiceImpl();
        Tariff tariff = tariffService.getTariffById(id_tariff);

        PDFBuilder.tariffPDF(response, tariff);
        return "controller?action=services";
    }
}
