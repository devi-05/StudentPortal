package Menu;

import FeePortal.PaymentService;
import Verification.Verification;

public class Fees {
    public void payService(String mailId) {
        PaymentService payService = new PaymentService();
        if (Verification.isStudent(mailId)) {
            studentFeePageMenuLoop:
            while (true) {
                System.out.println("""
                        1.pay
                        2.view balance
                        3.back to menu page""");
                int inp = Verification.inputVerification(3);
                switch (inp) {
                    case 1 -> payService.pay(mailId);
                    case 2 -> payService.viewBalance(mailId);
                    case 3 -> {
                        break studentFeePageMenuLoop;
                    }
                }
            }
        } else {
            adminFeePageMenuLoop:
            while (true) {
                System.out.println("1.view student balance" + "\n2.back to menu page");
                int inp = Verification.inputVerification(2);
                switch (inp) {
                    case 1:
                        payService.viewBalance(mailId);
                    case 2:
                        break adminFeePageMenuLoop;
                }
            }
        }
    }
}
