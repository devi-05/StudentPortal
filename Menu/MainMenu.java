package Menu;

import Helper.UtilFunction;
import Helper.Verification;

import java.io.IOException;

public class MainMenu {
    public void menu(String mailId) throws IOException {
        menuLoop:
        while (true) {
            System.out.println("Enter ur preference in sign in page");
            Menu[] menus = Menu.values();
            UtilFunction.printOptions(menus);
            System.out.println("Enter ur preference:");
            int input = Verification.inputVerification(menus.length);
            Menu preference = menus[input - 1];
            switch (preference) {
                case PROFILE_PAGE -> {
                    ProfileMenu profile = new ProfileMenu();
                    profile.profileOption(mailId);
                }
                case FEE_PORTAL -> {
                    Fees fees = new Fees();
                    fees.payService(mailId);
                }
                case RESULT_PORTAL -> {
                    Result result = new Result();
                    result.results(mailId);
                }
                case SIGN_OUT -> {
                    SignOut();
                    break menuLoop;
                }
            }
        }
    }

    public void SignOut() {
        System.out.println("Signing out....");
    }
}
