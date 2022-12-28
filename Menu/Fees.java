package Menu;

import FeePortal.PaymentService;
import Helper.UtilFunction;
import Helper.Verification;

import java.util.Arrays;

public class Fees {
    public void payService(String mailId) {
        PaymentService payService = new PaymentService();
        int inputPreference;
        feeMenuLoop:
        while (true) {
            if (!Verification.isStudent(mailId)) {
                UtilFunction.printOptions(Arrays.copyOf(FeeMenu.values(), FeeMenu.values().length - 1));
                System.out.println("enter ur preference");
                inputPreference = Verification.inputVerification(FeeMenu.values().length-1);
            } else {
                UtilFunction.printOptions(FeeMenu.values());
                System.out.println("enter ur preference");
                inputPreference = Verification.inputVerification(FeeMenu.values().length);
            }
            FeeMenu preference=FeeMenu.values()[inputPreference-1];
            switch (preference) {
                case BACK_TO_MENU_PAGE -> {
                    break feeMenuLoop;
                }
                case VIEW_BALANCE -> payService.viewBalance(mailId);
                case PAY -> payService.pay(mailId);
            }
        }
    }
}
