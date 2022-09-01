package entity;

import java.sql.Date;
/**
 * Payment entity
 *
 * @author R.Kotyk
 * */
public class Payment {
    private int user_id;
    private int tariff_id;
    private Date write_off;

    /**
     * shows user id of the payment
     * @return user id
     * */
    public int getUser_id() {
        return user_id;
    }

    /**
     * To set a user id of the payment
     * @param user_id user id
     * */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * shows tariff id of the payment
     * @return tariff id
     * */
    public int getTariff_id() {
        return tariff_id;
    }

    /**
     * To set tariff id of the payment
     * @param tariff_id tariff id
     * */
    public void setTariff_id(int tariff_id) {
        this.tariff_id = tariff_id;
    }

    /**
     * shows the date when the payment is due
     * @return payment date
     * */
    public Date getWrite_off() {
        return write_off;
    }

    /**
     * To set the payment date
     * @param write_off payment date
     * */
    public void setWrite_off(Date write_off) {
        this.write_off = write_off;
    }
}
