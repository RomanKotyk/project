package services;

import entity.Tariff;
import entity.User;

import java.util.ArrayList;

public interface IUserService {
    boolean addUser(User user);
    void deleteUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
    User checkLoginUser(String login, String password);
    ArrayList<User> getAllUsers();
    void updateStatus(User user);
    ArrayList<Tariff> getSubscriptions(User user);
    boolean subscribe(User user, Tariff tariff);
    void updateBalance(User user);
    ArrayList<User> getRecords(int start, int perPage);
}
