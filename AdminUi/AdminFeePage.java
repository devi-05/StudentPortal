package AdminUi;

import FeePortal.PaymentService;
import Helper.UtilFunction;
import Helper.Verification;

public class AdminFeePage  {
    PaymentService paymentService=new PaymentService();
    public void getAdminFeePage(String mailId) {
        AdminFeeMenuLoop:
        while (true) {
            UtilFunction.printOptions(AdminFeeMenu.values());
            int input = Verification.inputVerification(AdminFeeMenu.values().length);
            AdminFeeMenu preference = AdminFeeMenu.values()[input-1];
            switch (preference) {
                case VIEW_BALANCE -> paymentService.viewBalance(mailId);
                case BACK_TO_MENU_PAGE -> {
                    break AdminFeeMenuLoop;
                }
            }

        }
    }
}
