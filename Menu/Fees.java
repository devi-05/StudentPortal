package Menu;

import FeePortal.PaymentService;
import Verification.Verification;

import java.util.Arrays;
import java.util.List;

public class Fees {
    public void payService(String mailId) {
        PaymentService payService = new PaymentService();
        int inputPreference;
        feeMenuLoop:
        while (true) {
            if (Verification.isStudent(mailId)) {
                List<String> studentFeesOption = Arrays.asList("back to menu page", "view balance", "pay");
                Verification.printOptions(studentFeesOption);
                System.out.println("enter ur preference");
                inputPreference = Verification.inputVerification(3);
            } else {
                List<String> adminFeesOption = Arrays.asList("back to menu page", "view student balance");
                Verification.printOptions(adminFeesOption);
                System.out.println("enter ur preference");
                inputPreference = Verification.inputVerification(2);
            }
            switch (inputPreference) {
                case 1 -> {
                    break feeMenuLoop;
                }
                case 2 -> payService.viewBalance(mailId);
                case 3 -> payService.pay(mailId);
            }
        }
    }
}
