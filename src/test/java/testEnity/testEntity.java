package testEnity;

import entity.Payment;
import entity.Service;
import entity.Tariff;
import entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class testEntity {
    private Tariff tariff;
    private Service service;
    private User user;
    private Payment payment;


    @Test
    public void testTariffEntity(){
        tariff = new Tariff();
        Assert.assertNotNull(tariff);

        tariff.setName("test");
        Assert.assertTrue(tariff.getName().equalsIgnoreCase("test"));

        tariff.setId(1);
        Assert.assertTrue(tariff.getId()==1);

        tariff.setDescription("testTariff");
        Assert.assertTrue(tariff.getDescription().equalsIgnoreCase("testTariff"));

        tariff.setPrice(100);
        Assert.assertTrue(tariff.getPrice()==100);

        tariff.setService_id(1);
        Assert.assertTrue(tariff.getService_id()==1);

        tariff.setBest(false);
        Assert.assertFalse(tariff.isBest());

        service = new Service();
        tariff.setService(service);
        Assert.assertNotNull(tariff.getService());

        Assert.assertTrue(tariff.toString().equalsIgnoreCase(String.valueOf(tariff.getId())));

    }

    @Test
    public void testPaymanetEntity(){
        payment = new Payment();
        Assert.assertNotNull(payment);

        payment.setUser_id(1);
        Assert.assertTrue(payment.getUser_id()==1);

        payment.setTariff_id(1);
        Assert.assertTrue(payment.getTariff_id()==1);

        payment.setWrite_off(Date.valueOf(LocalDate.now()));
        Assert.assertTrue(payment.getWrite_off().equals(Date.valueOf(LocalDate.now())));
    }

    @Test
    public void testServiceEntity(){
        service = new Service();
        Assert.assertNotNull(service);

        service.setId(1);
        Assert.assertTrue(service.getId()==1);

        service.setDescription("testService");
        Assert.assertTrue(service.getDescription().equalsIgnoreCase("testService"));

        service.setName("test");
        Assert.assertTrue(service.getName().equalsIgnoreCase("test"));

        ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());

        service.setTariffList(tariffs);
        Assert.assertTrue(tariffs == service.getTariffList());
    }

}
