package utils;

import commands.admin.RecordsUsersCommand;
import entity.Payment;
import entity.Tariff;
import entity.User;
import org.apache.log4j.Logger;
import services.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncCommand {
    private static final Logger log = Logger.getLogger(AsyncCommand.class);
    public static void startCommand() {
        log.debug("Commands starts");
        Callable<Void> thread = () -> {
            Date previouseDate = Date.valueOf(LocalDate.now());
            IUserService userService = new UserServiceImpl();
            ITariffService tariffService = new TariffServiceImpl();
            IPaymentService paymentService = new PaymentServiceImpl();
            while (true) {
                Date currentDate = Date.valueOf(LocalDate.now());
                if (currentDate.compareTo(previouseDate) == 0) {
                    ArrayList<Payment> payments = paymentService.getAllPayment();
                    for (Payment payment : payments) {
                        if (payment.getWrite_off().compareTo(currentDate) == 0) {
                            User user = userService.getUserById(payment.getUser_id());
                            Tariff tariff = tariffService.getTariffById(payment.getTariff_id());
                            if (user.getBalance() - tariff.getPrice() < 0) {
                                user.setStatus(false);
                                userService.updateStatus(user);
                            } else {
                                double user_balance = user.getBalance() - tariff.getPrice();
                                user.setBalance(user_balance);
                                userService.updateBalance(user);
                                paymentService.updateWriteOffDate(user, tariff);
                                log.debug("Payment - OK: User: " + user.getId() + " Tariff: " + tariff.getId());
                            }
                        }
                    }
                }
                Thread.sleep(10_000);
                previouseDate = currentDate;
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(thread);
        service.shutdown();
    }
}
