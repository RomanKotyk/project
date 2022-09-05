package testServices;

import dao.PaymentDAO;
import dao.TariffDAO;
import entity.Payment;
import entity.Tariff;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import services.IPaymentService;
import services.ITariffService;
import services.PaymentServiceImpl;
import services.TariffServiceImpl;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class testPaymentService {
    private PaymentDAO paymentDAO;
    private IPaymentService paymentService;

    @Before
    public void setUp() {
        paymentDAO = Mockito.mock(PaymentDAO.class);
        paymentService = new PaymentServiceImpl(paymentDAO);
    }

    @Test
    public void testGetAllPayment(){
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment());
        payments.add(new Payment());
        payments.add(new Payment());

        when(paymentDAO.getAllPayment()).thenReturn(payments);
        Assert.assertTrue(paymentService.getAllPayment().size() == 3);
    }

    @Test
    public void testGetPayment(){
        User user = new User();
        Tariff tariff = new Tariff();
        when(paymentDAO.getPaymentByUserTariff(user, tariff)).thenReturn(new Payment());
        Payment payment = paymentService.getPaymentByUserTariff(user, tariff);
        Assert.assertNotNull(payment);
    }
}
