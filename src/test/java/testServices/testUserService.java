package testServices;
import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import services.IUserService;
import services.UserServiceImpl;


public class testUserService {

    private UserDAO userDAO;
    private IUserService userService;

    @Before
    public void setUp() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAO);
    }


    @Test
    public void testGetUserByIdNotNull() {
        when(userDAO.getUserById(77)).thenReturn(new User());

        User user = userService.getUserById(77);
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetUserByLoginNotNull(){
        when(userDAO.getUserByLogin("roman")).thenReturn(new User());

        User user = userService.getUserByLogin("roman");
        Assert.assertNotNull(user);
    }

    @Test
    public void testCheckLoginUserTrue(){
        when(userDAO.getUserByLoginPassword("roman", "1234")).thenReturn(new User());

        User user = userService.checkLoginUser("roman", "1234");
        Assert.assertTrue(user!=null);
    }


}
