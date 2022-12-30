package StudentUi;

import FeePortal.PaymentService;
import Helper.UtilFunction;
import Helper.Verification;

public class StudentFeePage extends PaymentService {
    public void getStudentFeePage(String mailId) {
        StudentFeePage paymentService = new StudentFeePage();
        StudentFeeMenuLoop:
        while (true) {
            System.out.println("Welcome to fee page");
            UtilFunction.printOptions(StudentFeeMenu.values());
            System.out.println("Enter option");
            int input = Verification.inputVerification(StudentFeeMenu.values().length);
            StudentFeeMenu preference = StudentFeeMenu.values()[input-1];
            switch (preference) {
                case PAY -> paymentService.pay(mailId);
                case VIEW_BALANCE -> paymentService.viewBalance(mailId);
                case BACK_TO_MENU_PAGE -> {break StudentFeeMenuLoop;}
            }
        }
    }
}