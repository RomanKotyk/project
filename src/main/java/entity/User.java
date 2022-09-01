package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User entity
 *
 * @author R.Kotyk
 *
 * */

public class User {
    private int id;
    private String login;
    private String surname;
    private String name;
    private String password;
    private boolean isAdmin;
    private List<Tariff> tariffList;
    private double balance;
    private boolean status;

    /**
     * Main user constructor
     *
     * @param login user login
     * @param surname user surname
     * @param name user name
     * */
    public User(String login, String surname, String name) {
        this.login = login;
        this.surname = surname;
        this.name = name;
    }

    /**
     * Empty constructor for initializing list of tariffs
     * */
    public User(){
        tariffList = new ArrayList<>();
    }

    /**
     * shows user login
     * @return user login
     * */
    public String getLogin() {
        return login;
    }

    /**
     *  shows user password
     * @return user password
     * */
    public String getPassword() {
        return password;
    }

    /**
     *  To set user password
     * @param password user password
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *  To set user login
     * @param login user login
     * */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *  shows user id
     * @return user id
     * */
    public int getId() {
        return id;
    }

    /**
     *  To set user id
     * @param id user id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  shows user surname
     * @return user surname
     * */
    public String getSurname() {
        return surname;
    }

    /**
     *  To set user surname
     * @param surname user surname
     * */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *  shows user name
     * @return user name
     * */
    public String getName() {
        return name;
    }

    /**
     *  To set user name
     * @param name user name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  shows is user an admin
     * @return true - if it`s admin, false - else
     * */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     *  To set the user is an admin
     * @param admin is the user admin
     * */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     *  shows user tariffs
     * @return user tariffs
     * */
    public List<Tariff> getTariffList() {
        return tariffList;
    }

    /**
     *  To set user tariffs
     * @param tariffList user tariffs
     * */
    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    /**
     *  shows user balance
     * @return user balance
     * */
    public double getBalance() {
        return balance;
    }

    /**
     *  To set user balance
     * @param balance user balance
     * */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *  shows user status
     * @return false - blocked, true - active
     * */
    public boolean isStatus() {
        return status;
    }

    /**
     * To set user status
     * @param status user status
     * */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     *  Equals two users by their login
     * @param o another user
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    /**
     * @return user hashCode
     * */
    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
