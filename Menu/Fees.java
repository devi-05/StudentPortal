package Menu;

import FeePortal.PaymentService;
import Helper.UtilFunction;
import Helper.Verification;

public class Fees {
    public void payService(String mailId) {
        PaymentService payService = new PaymentService();
        int inputPreference;
        Enum preference;
        feeMenuLoop:
        while (true) {
            System.out.println("Welcome to fee portal");
            if (!Verification.isStudent(mailId)) {
                AdminFeeMenu[] adminFeeMenus=AdminFeeMenu.values();
                UtilFunction.printOptions(adminFeeMenus);
                System.out.println("Enter ur preference");
                inputPreference = Verification.inputVerification(adminFeeMenus.length);
                 preference=adminFeeMenus[inputPreference-1];
            } else {
                StudentFeeMenu[] studentFeeMenus=StudentFeeMenu.values();
                UtilFunction.printOptions(studentFeeMenus);
                System.out.println("Enter ur preference");
                inputPreference = Verification.inputVerification(studentFeeMenus.length);
                preference=studentFeeMenus[inputPreference-1];
            }
            switch (String.valueOf(preference)) {
                case "BACK_TO_MENU_PAGE" -> {
                    break feeMenuLoop;
                }
                case "VIEW_BALANCE" -> payService.viewBalance(mailId);
                case "PAY" -> payService.pay(mailId);
            }
        }
    }
}
