package testServices;

import dao.ServiceDAO;
import dao.TariffDAO;
import org.junit.Before;
import org.mockito.Mockito;
import services.IServiceService;
import services.ITariffService;
import services.ServiceServiceImpl;
import services.TariffServiceImpl;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

public class testServiceService {
    private ServiceDAO serviceDAO;
    private IServiceService serviceService;

    @Before
    public void setUp() {
        serviceDAO = Mockito.mock(ServiceDAO.class);
        serviceService = new ServiceServiceImpl(serviceDAO);
    }
}
