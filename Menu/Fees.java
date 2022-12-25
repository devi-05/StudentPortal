package Menu;

import FeePortal.PaymentService;
import Verification.Verification;

public class Fees {
    public void payService(String mailId, String user) {
        PaymentService payService = new PaymentService();
        if (user.equals("edu")) {
            System.out.println("1.pay" + "\n2.view balance");
            int inp = Verification.inputVerification(2);
            switch (inp) {
                case 1 -> payService.pay(mailId);
                case 2 -> payService.viewBalance();
            }
        } else {
            System.out.println("1.view student balance" + "\n2.back");
            int inp = Verification.inputVerification(2);
            switch (inp) {
                case 1:
                    payService.viewBalance();
                case 2:
                    break;
            }

        }
    }
}
