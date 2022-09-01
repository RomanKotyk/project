package testCommands;

import commands.Command;
import commands.admin.AddUserCommand;
import commands.common.LoginCommand;
import commands.common.LogoutCommand;
import entity.User;
import org.junit.*;
import services.IUserService;
import services.UserServiceImpl;

import javax.servlet.http.HttpSession;

/**
 * Unit test for Commands
 *
 * @author R.Kotyk
 *
 * */
public class testCommands {
    RequestWrapper requestWrapper;
    RensponseWrapper rensponseWrapper;

    /**
     * Init new RequestWrapper, RensponseWrapper object before each test
     * */
    @Before
    public void init()
    {
        requestWrapper = new RequestWrapper();
        rensponseWrapper = new RensponseWrapper();

    }

    @Test
    public void testAddUserCommand(){
        requestWrapper.addParam("login_user", "roman4ik");
        requestWrapper.addParam("name_user", "Roman");
        requestWrapper.addParam("surname_user", "Kotyk");
        requestWrapper.addParam("password_user", "11111111");
        requestWrapper.addParam("balance_user", "0");
        requestWrapper.addParam("user_role", "User");

        IUserService userService = new UserServiceImpl();
        int oldSize = userService.getAllUsers().size();

        Command command = new AddUserCommand();
        command.execute(requestWrapper, rensponseWrapper);

        int newSize = userService.getAllUsers().size();

        User user = userService.getUserByLogin(requestWrapper.getParameter("login_user"));
        userService.deleteUser(user);

        Assert.assertEquals(oldSize + 1, newSize);

    }

    @Test
    public void testLoginCommand(){
        requestWrapper.addParam("login", "RomanKotyk");
        requestWrapper.addParam("password", "password");

        Command command = new LoginCommand();
        command.execute(requestWrapper, rensponseWrapper);

        HttpSession session = requestWrapper.getSession();
        User user = (User)session.getAttribute("user");

        Assert.assertNotNull(user);
    }

    @Test
    public void testLogoutCommand(){
        HttpSession session = requestWrapper.getSession();
        session.setAttribute("user", new User());

        Command command = new LogoutCommand();
        command.execute(requestWrapper, rensponseWrapper);

        User user = (User) session.getAttribute("user");

        Assert.assertNull(user);
    }


}
