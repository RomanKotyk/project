package testServices;

import dao.DAOFactory;
import dao.TariffDAO;
import dao.UserDAO;
import entity.Service;
import entity.Tariff;
import org.junit.Before;
import org.junit.Test;
import services.ITariffService;
import services.IUserService;
import services.TariffServiceImpl;
import services.UserServiceImpl;
import org.junit.*;
import org.mockito.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class testTariffService {
    private TariffDAO tariffDAO = Mockito.mock(TariffDAO.class);
    private ITariffService tariffService;

    @Before
    public void setUp(){
        tariffService = new TariffServiceImpl(tariffDAO);
    }

    @Test
    public void testGetAllTariffs(){
        ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());

        when(tariffDAO.getAllTariffs()).thenReturn(tariffs);
        Assert.assertTrue(tariffService.getAllTariffs().size() == 3);
    }

    @Test
    public void testGetTariffsOfService(){
        ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());

        Service service = new Service();
        when(tariffDAO.getTariffsOfService(service)).thenReturn(tariffs);
        Assert.assertTrue(tariffService.getTariffsOfService(service).size() == 3);
    }

    @Test
    public void testGetTariffById(){
        when(tariffDAO.getTariffById(100)).thenReturn(new Tariff());
        Tariff tariff = tariffService.getTariffById(100);
        Assert.assertNotNull(tariff);
    }

    @Test
    public void testGetTariffByName(){
        when(tariffDAO.getTariffByName("test")).thenReturn(new Tariff());
        Tariff tariff = tariffService.getTariffByName("test");
        Assert.assertNotNull(tariff);
    }

    @Test
    public void testAddTariff(){
        when(tariffDAO.addTarrif(any(Tariff.class))).thenReturn(true);
        Tariff tariff = new Tariff();

        Assert.assertTrue(tariffService.addTarrif(tariff));
        verify(tariffDAO).addTarrif(any(Tariff.class));
        verify(tariffDAO, never()).deleteTariff(any(Tariff.class));
    }

    @Test
    public void testDeleteTariff(){
        Tariff tariff = new Tariff();
        tariffService.deleteTariff(tariff);

        verify(tariffDAO, timeout(1)).deleteTariff(any(Tariff.class));

    }


}
