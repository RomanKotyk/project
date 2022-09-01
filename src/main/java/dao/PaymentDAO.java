package dao;

import entity.Payment;
import entity.Tariff;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data access object for Payment entities
 * */
public class PaymentDAO extends AbstractDAO{
    private String GET_ALL_PAYMENT = "SELECT * FROM subcribers";
    private String UPDATE_WRITE_OFF_DATE = "UPDATE subcribers SET write_off = DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH) WHERE user_id = ? and tariff_id = ?";
    private static PaymentDAO instance;

    public static PaymentDAO getInstance(){
        if (instance == null){
            instance = new PaymentDAO();
        }
        return instance;
    }

    /**
     * show all payments
     * @return all payments
     * */
    public ArrayList<Payment> getAllPayment(){
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_PAYMENT)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Payment payment = new Payment();
                payment.setUser_id(rs.getInt(1));
                payment.setTariff_id(rs.getInt(2));
                payment.setWrite_off(rs.getDate(3));
                paymentArrayList.add(payment);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return paymentArrayList;
    }

    /**
     * To update write-off subscription date
     * @param user subscriber
     * @param tariff tariff for which user is subscribed
     * */
    public void updateWriteOffDate(User user, Tariff tariff){
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WRITE_OFF_DATE)){
             statement.setInt(1, user.getId());
             statement.setInt(2, tariff.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
