package services;

import entity.Payment;
import entity.Tariff;
import entity.User;

import java.util.ArrayList;

public interface IPaymentService {
    ArrayList<Payment> getAllPayment();
    void updateWriteOffDate(User user, Tariff tariff);
    Payment getPaymentByUserTariff(User user, Tariff tariff);
}
