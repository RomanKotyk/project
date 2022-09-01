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
 * Data access object for Service entities
 * */
public class ServiceDAO extends AbstractDAO{
    private String GET_ALL_SERVICES = "SELECT * FROM services";
    private String ADD_SERVICE = "SELECT INTO services(name, decription) VALUES (?,?)";
    private String GET_SERVICE_BY_NAME = "SELECT * FROM services WHERE name = ?";
    private static ServiceDAO instance;

    public static ServiceDAO getInstance(){
        if (instance == null){
            instance = new ServiceDAO();
        }
        return instance;
    }

    /**
     * shows all services
     * @return all services
     * */
    public ArrayList<Service> getAllServices(){
        ArrayList<Service> services = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_SERVICES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Service service = new Service();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setDescription(rs.getString(3));
                services.add(service);
            }
            return services;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Add new service
     * @param service Service
     * */
    public boolean addService(Service service){
        if (instance.getServiceByName(service.getName()) != null){
            return false;
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_SERVICE)){
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());

            statement.executeUpdate();
            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM services");
            rs.next();
            service.setId(rs.getInt(1));

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * shows service with given name
     * @param name service name
     * @return service
     * */
    public Service getServiceByName(String name){
        Service service = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SERVICE_BY_NAME)){
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                service = new Service();
                service.setId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setDescription(rs.getString(3));
            }
            return service;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
