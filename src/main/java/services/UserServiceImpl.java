package services;

import dao.DAOFactory;
import dao.UserDAO;
import entity.Tariff;
import entity.User;

import java.util.ArrayList;

public class UserServiceImpl implements IUserService{
    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    @Override
    public boolean addUser(User user) {
        return this.userDAO.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userDAO.deleteUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    @Override
    public User checkLoginUser(String login, String password) {
        return this.userDAO.getUserByLoginPassword(login, password);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public void updateStatus(User user) {
        this.userDAO.updateStatus(user);
    }

    @Override
    public ArrayList<Tariff> getSubscriptions(User user) {
        return this.userDAO.getTariffOfUser(user);
    }

    @Override
    public boolean subscribe(User user, Tariff tariff) {
        return this.userDAO.addSubcribe(user, tariff);
    }

    @Override
    public void updateBalance(User user) {
        this.userDAO.updateBalance(user);
    }

    @Override
    public ArrayList<User> getRecords(int start, int perPage) {
        return this.userDAO.getRecords(start, perPage);
    }
}
