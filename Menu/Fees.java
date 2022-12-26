package Menu;

import FeePortal.PaymentService;
import Verification.Verification;

public class Fees {
    public void payService(String mailId, String user) {
        PaymentService payService = new PaymentService();
        if (user.equals("edu")) {
            boolean a=true;
            while (a) {
                System.out.println("""
                        1.pay
                        2.view balance
                        3.back""");
                int inp = Verification.inputVerification(3);
                switch (inp) {
                    case 1 -> payService.pay(mailId);
                    case 2 -> payService.viewBalance();
                    case 3 -> a = false;
                }
            }
        } else {
            boolean a = true;
            while (a) {
                System.out.println("1.view student balance" + "\n2.back");
                int inp = Verification.inputVerification(2);
                switch (inp) {
                    case 1:
                        payService.viewBalance();
                    case 2:
                        a = false;
                }
            }
        }
    }
}
