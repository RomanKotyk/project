package services;

import dao.DAOFactory;
import dao.PaymentDAO;
import entity.Payment;
import entity.Tariff;
import entity.User;

import java.util.ArrayList;

public class PaymentServiceImpl implements IPaymentService{
    private PaymentDAO paymentDAO;
    public PaymentServiceImpl(){
        this.paymentDAO = DAOFactory.getInstance().getPaymentDAO();
    }
    public PaymentServiceImpl(PaymentDAO paymentDAO){
        this.paymentDAO = paymentDAO;
    }
    @Override
    public ArrayList<Payment> getAllPayment() {
        return this.paymentDAO.getAllPayment();
    }
    @Override
    public void updateWriteOffDate(User user, Tariff tariff) {
        this.paymentDAO.updateWriteOffDate(user, tariff);
    }
    @Override
    public Payment getPaymentByUserTariff(User user, Tariff tariff){
        return this.paymentDAO.getPaymentByUserTariff(user, tariff);
    }

}
