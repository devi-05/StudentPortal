package Menu;

import FeePortal.PaymentService;
import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;

public class Fees {
    public void payService(String mailId) {
        Database db = Database.getInstance();
        PaymentService payService = new PaymentService();
        int inputPreference;
        Enum preference;
        feeMenuLoop:
        while (true) {
            System.out.println("WELCOME TO FEE PAGE");
            if (!Verification.isStudent(mailId)) {
                AdminFeeMenu[] adminFeeMenus = AdminFeeMenu.values();
                UtilFunction.printOptions(adminFeeMenus);
                System.out.println("Enter ur preference");
                inputPreference = Verification.inputVerification(adminFeeMenus.length);
                preference = adminFeeMenus[inputPreference - 1];
            } else {
                StudentFeeMenu[] studentFeeMenus = StudentFeeMenu.values();
                UtilFunction.printOptions(studentFeeMenus);
                System.out.println("Enter ur preference");
                inputPreference = Verification.inputVerification(studentFeeMenus.length);
                preference = studentFeeMenus[inputPreference - 1];
            }
            switch (String.valueOf(preference)) {
                case "BACK_TO_MENU_PAGE" -> {
                    break feeMenuLoop;
                }
                case "VIEW_BALANCE" -> payService.viewBalance(mailId);
                case "PAY" -> payService.pay(mailId);
                case "UPDATE_STUDENT_FEES" -> {
                    System.out.println("Enter Student mailId");
                    String studentMailId = Verification.mailVerification();
                    if (Verification.isStudent(studentMailId)) {
                        if (db.getId(studentMailId)) {
                            db.setFeeBalance(studentMailId, (db.getFeesBalance(studentMailId) + db.getTotalFees(studentMailId)));
                            System.out.println("Fees Updated!!");
                        } else {
                            System.out.println("Student doesn't exist");
                        }
                    } else {
                        System.out.println("Enter student mailId!!");
                    }
                }
            }
        }
    }
}
