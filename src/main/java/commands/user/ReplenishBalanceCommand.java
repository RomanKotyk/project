package commands.user;

import commands.Command;

import entity.Payment;
import entity.Tariff;
import entity.User;
import org.apache.log4j.Logger;
import services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReplenishBalanceCommand implements Command {
    private static final Logger log = Logger.getLogger(ReplenishBalanceCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Commands starts");

        IUserService userService = new UserServiceImpl();

        User user = (User) request.getSession().getAttribute("user");

        user.setBalance(user.getBalance() + Double.parseDouble(request.getParameter("amount_replenish")));

        userService.updateBalance(user);
        if (!user.isStatus()){
            List<Tariff> tariffs = user.getTariffList();
            ITariffService tariffService = new TariffServiceImpl();
            IServiceService serviceService = new ServiceServiceImpl();
            IPaymentService paymentService = new PaymentServiceImpl();
            ArrayList<Payment> payments = paymentService.getAllPayment();
            for (Payment payment : payments){
                Tariff tariff = tariffService.getTariffById(payment.getTariff_id());
                if (Date.valueOf(LocalDate.now()).compareTo(payment.getWrite_off()) == 0 || Date.valueOf(LocalDate.now()).compareTo(payment.getWrite_off()) > 1){
                    if (user.getBalance() - tariff.getPrice() < 0) {
                        break;
                    }else {
                        user.setBalance(user.getBalance() - tariff.getPrice());
                        paymentService.updateWriteOffDate(user, tariff);
                    }
                }
                user.setStatus(true);
                userService.updateStatus(user);
                userService.updateBalance(user);
            }
        }

        request.getSession().setAttribute("user", user);
        log.debug("Commands finished");
        return "controller?action=profile";
    }
}
