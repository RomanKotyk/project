package services;

import dao.DAOFactory;
import dao.ServiceDAO;
import entity.Service;

import java.util.ArrayList;

public class ServiceServiceImpl implements IServiceService{
    private ServiceDAO serviceDAO;
    public ServiceServiceImpl(){
        this.serviceDAO = DAOFactory.getInstance().getServiceDAO();
    }

    public ServiceServiceImpl(ServiceDAO serviceDAO){
        this.serviceDAO = serviceDAO;
    }
    @Override
    public ArrayList<Service> getAllServices() {
        return this.serviceDAO.getAllServices();
    }

    @Override
    public boolean addService(Service service) {
        return this.serviceDAO.addService(service);
    }

    @Override
    public Service getServiceByName(String name) {
        return this.serviceDAO.getServiceByName(name);
    }
}
