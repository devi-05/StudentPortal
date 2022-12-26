package Menu;

import Verification.Verification;

import java.io.IOException;

public class MainMenu {
    public void menu(String mailId) throws IOException {
        boolean a = true;
        while (a) {
            System.out.println("enter ur preference in sign in page");
            System.out.println("""
                    1.Profile page
                    2.Fee portal
                    3.Result portal
                    4.SignOut""");
            int preference = Verification.inputVerification(4);
            switch (preference) {
                case 1 -> {
                    System.out.println("welcome to profile page");
                    ProfileMenu profile = new ProfileMenu();
                    profile.profileOption(mailId);
                }
                case 2 -> {
                    System.out.println("welcome to fee portal");
                    Fees fees = new Fees();
                    fees.payService(mailId);
                }
                case 3 -> {
                    System.out.println("welcome to result portal");
                    Result result = new Result();
                    result.results(mailId);
                }
                case 4 -> {
                    SignOut();
                    a=false;
                }
            }
        }
    }
    public void SignOut() {
        System.out.println("Signing out....");
    }
}
