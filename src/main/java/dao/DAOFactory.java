package dao;

import org.apache.log4j.Logger;

public class DAOFactory {
    private static final Logger log = Logger.getLogger(DAOFactory.class);
    private static DAOFactory instance;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Error jdbc driver -> " + e.getMessage());
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
            log.debug("DAOFactory was initialized!");
        }
        return instance;
    }

    public TariffDAO getTariffDAO() {
        return TariffDAO.getInstance();
    }

    public ServiceDAO getServiceDAO() {
        return ServiceDAO.getInstance();
    }

    public UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }

    public PaymentDAO getPaymentDAO(){return PaymentDAO.getInstance();}
}
