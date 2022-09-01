package services;

import dao.DAOFactory;
import dao.TariffDAO;
import entity.Service;
import entity.Tariff;

import java.util.ArrayList;

public class TariffServiceImpl implements ITariffService{
    private TariffDAO tariffDAO = DAOFactory.getInstance().getTariffDAO();
    @Override
    public ArrayList<Tariff> getAllTariffs() {
        return this.tariffDAO.getAllTariffs();
    }

    @Override
    public boolean addTarrif(Tariff tariff) {
        return this.tariffDAO.addTarrif(tariff);
    }

    @Override
    public Tariff getTariffByName(String name) {
        return this.tariffDAO.getTariffByName(name);
    }

    @Override
    public ArrayList<Tariff> getTariffsOfService(Service service) {
        return this.tariffDAO.getTariffsOfService(service);
    }

    @Override
    public void deleteTariff(Tariff tariff) {
        this.tariffDAO.deleteTariff(tariff);
    }

    @Override
    public void updateTariff(Tariff tariff) {
        this.tariffDAO.updateTariff(tariff);
    }

    @Override
    public Tariff getTariffById(int id) {
        return this.tariffDAO.getTariffById(id);
    }

    @Override
    public ArrayList<Tariff> getBestTariffs() {
        return this.tariffDAO.getBestTariffs();
    }
}
