package dao;

import entity.Service;
import entity.Tariff;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Data access object for Tariff entities
 * */
public class TariffDAO extends AbstractDAO{
    private String GET_ALL_TARIFFS = "SELECT * FROM tariffs";
    private String ADD_TARIFF = "INSERT INTO tariffs(name, description, price, service_id) VALUES (?,?,?,?)";
    private String GET_TARIFF_BY_NAME = "SELECT * FROM tariffs WHERE name = ?";
    private String GET_TARIFFS_OF_SERVICE = "SELECT * FROM tariffs WHERE service_id = ?";
    private String DELETE_TARIFF = "DELETE FROM tariffs WHERE id = ?";
    private String UPDATE_TARIFF = "UPDATE tariffs SET name = ?, description = ?, price = ?  WHERE id = ?";
    private String GET_TARIFF_BY_ID = "SELECT * FROM tariffs WHERE id = ?";
    private String GET_BEST_TARIFF = "SELECT * FROM tariffs, services WHERE tariffs.best = TRUE AND tariffs.service_id = services.id";
    private static TariffDAO instance;

    public static TariffDAO getInstance(){
        if (instance == null){
            instance = new TariffDAO();
        }
        return instance;
    }

    /**
     * shows all tariffs
     * @return all tariffs
     * */
    public ArrayList<Tariff> getAllTariffs() {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_TARIFFS)){

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                tariffs.add(tariff);
            }

            return tariffs;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Add new tariff
     * @param tariff Tariff
     * @return is it added
     * */
    public boolean addTarrif(Tariff tariff){
        Tariff exists = getInstance().getTariffByName(tariff.getName());
        if(exists != null) {
            return false;
        }
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_TARIFF)){
            statement.setString(1, tariff.getName());
            statement.setString(2, tariff.getDescription());
            statement.setInt(3, tariff.getPrice());
            statement.setInt(4, tariff.getService_id());

            statement.executeUpdate();
            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM tariffs");
            rs.next();
            tariff.setId(rs.getInt(1));

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * shows tariff with given name
     * @param name tariff name
     * @return Tariff with given name
     * */
    public Tariff getTariffByName(String name){
        Tariff tariff = null;
        try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_TARIFF_BY_NAME)){
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
            }
            return tariff;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * shows tariffs with given service
     * @param service service
     * @return ArrayList of tariff entities
     * */
    public ArrayList<Tariff> getTariffsOfService(Service service){
        ArrayList<Tariff> tariffs = new ArrayList<>();
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_TARIFFS_OF_SERVICE);){
            statement.setInt(1, service.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                tariffs.add(tariff);
            }
            return tariffs;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * To delete tariff
     * @param tariff Tariff entity
     * */
    public void deleteTariff(Tariff tariff){
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_TARIFF)){
            statement.setInt(1, tariff.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * To update tariff info
     * @param tariff Tariff entity
     * */
    public void updateTariff(Tariff tariff){
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_TARIFF)){

            statement.setString(1, tariff.getName());
            statement.setString(2, tariff.getDescription());
            statement.setInt(3, tariff.getPrice());
            statement.setInt(4, tariff.getId());

            statement.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * shows tariff with given id
     * @param id tariff id
     * @return Tariff entity
     * */
    public Tariff getTariffById(int id){
        Tariff tariff = null;
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_TARIFF_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                tariff.setService_id(rs.getInt(5));
                return tariff;
            }
            return tariff;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * shows the best tariffs
     * @return ArrayList of Tariff entities
     * */
    public ArrayList<Tariff> getBestTariffs(){
        ArrayList<Tariff> tariffArrayList = new ArrayList<>();
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BEST_TARIFF)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setDescription(rs.getString(3));
                tariff.setPrice(rs.getInt(4));
                tariff.setService_id(rs.getInt(5));
                Service service = new Service();
                service.setId(rs.getInt(7));
                service.setName(rs.getString(8));
                service.setDescription(rs.getString(9));
                tariff.setService(service);
                tariffArrayList.add(tariff);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tariffArrayList;
    }

}
