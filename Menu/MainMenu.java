package Menu;

import Helper.UtilFunction;
import Helper.Verification;

import java.io.IOException;

public class MainMenu {
    public void menu(String mailId) throws IOException {
        menuLoop:
        while (true) {
            System.out.println("enter ur preference in sign in page");
            UtilFunction.printOptions(Menu.values());
            System.out.println("enter ur preference:");
            int input = Verification.inputVerification(Menu.values().length);
            Menu preference=Menu.values()[input-1];
            switch (preference) {
                case PROFILE_PAGE -> {
                    System.out.println("welcome to profile page");
                    ProfileMenu profile = new ProfileMenu();
                    profile.profileOption(mailId);
                }
                case FEE_PORTAL -> {
                    System.out.println("welcome to fee portal");
                    Fees fees = new Fees();
                    fees.payService(mailId);
                }
                case RESULT_PORTAL -> {
                    System.out.println("welcome to result portal");
                    Result result = new Result();
                    result.results(mailId);
                }
                case SIGN_OUT-> {
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
