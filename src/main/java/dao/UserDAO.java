package dao;

import entity.Tariff;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserDAO extends AbstractDAO{
    private String ADD_USER= "INSERT INTO users(login, name, surname, password, admin, balance) VALUES (?,?,?,?,?,?)";
    private String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private String CHECK_LOGIN_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
    private String GET_ALL_USERS = "SELECT * FROM users";
    private String UPDATE_STATUS = "UPDATE users SET status = ? WHERE id = ?";
    private String GET_TARIFFS_OF_USER = "SELECT * FROM tariffs, subcribers WHERE tariffs.id = subcribers.tariff_id AND subcribers.user_id = ?";
    private String ADD_SUBSCRIBE = "INSERT INTO subcribers (user_id, tariff_id, write_off) VALUES (?,?, DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH))";
    private String GET_SUBSCRIPTION = "SELECT tariffs.id, tariffs.name, tariffs.description, tariffs.price FROM tariffs INNER JOIN subcribers ON tariffs.id = subcribers.tariff_id AND subcribers.user_id= ?";
    private String UPDATE_BALANCE = "UPDATE users SET balance = ? WHERE id = ?";
    private String GET_RECORDS = "SELECT * FROM users LIMIT";
    private static UserDAO instance;
    public static UserDAO getInstance(){
        if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public boolean addUser(User user){

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isAdmin());
            statement.setDouble(6, user.getBalance());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM users");
            rs.next();
            user.setId(rs.getInt(1));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("added");
        return true;

    }

    public void deleteUser(User user){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USER)){
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int id){
        User user = null;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setAdmin(rs.getBoolean(6));
                user.setBalance(rs.getDouble(7));
                user.setStatus(rs.getBoolean(8));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User getUserByLogin(String login){
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setAdmin(rs.getBoolean(6));
                user.setBalance(rs.getDouble(7));
                user.setStatus(rs.getBoolean(8));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User getUserByLoginPassword(String login, String password){
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_LOGIN_USER)){
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setAdmin(rs.getBoolean(6));
                user.setBalance(rs.getDouble(7));
                user.setStatus(rs.getBoolean(8));
                getSubscription(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)){

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setAdmin(rs.getBoolean(6));
                user.setBalance(rs.getDouble(7));
                user.setStatus(rs.getBoolean(8));
                users.add(user);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return users;
    }

    public void updateStatus(User user){
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)){
            statement.setBoolean(1, user.isStatus());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Tariff> getTariffOfUser(User user){
        ArrayList<Tariff> tariffArrayList = new ArrayList<>();
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_TARIFFS_OF_USER)){
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                tariff.setService_id(rs.getInt(5));
                tariffArrayList.add(tariff);
            }
            return tariffArrayList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public boolean addSubcribe(User user, Tariff tariff){
        if (user.isStatus() == false) return false;
        if (user.getBalance() < tariff.getPrice()) return false;

        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_SUBSCRIBE)){
            statement.setInt(1, user.getId());
            statement.setInt(2, tariff.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    public void getSubscription(User user){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_SUBSCRIPTION)){
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                user.getTariffList().add(tariff);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void updateBalance(User user){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE)){
            statement.setDouble(1, user.getBalance());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getRecords(int start, int perPage){
        ArrayList<User> users = new ArrayList<User>();
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users LIMIT " + start + ", " + perPage);){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setAdmin(rs.getBoolean(6));
                user.setBalance(rs.getDouble(7));
                user.setStatus(rs.getBoolean(8));
                users.add(user);
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return users;
    }

}
