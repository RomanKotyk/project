package services;

import entity.Service;
import entity.Tariff;

import java.util.ArrayList;

public interface ITariffService {
    ArrayList<Tariff> getAllTariffs();
    boolean addTarrif(Tariff tariff);
    Tariff getTariffByName(String name);
    ArrayList<Tariff> getTariffsOfService(Service service);
    void deleteTariff(Tariff tariff);
    void updateTariff(Tariff tariff);
    Tariff getTariffById(int id);
    ArrayList<Tariff> getBestTariffs();
}
