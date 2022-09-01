package services;

import entity.Service;

import java.util.ArrayList;

public interface IServiceService {
    ArrayList<Service> getAllServices();
    boolean addService(Service service);
    Service getServiceByName(String name);

}
