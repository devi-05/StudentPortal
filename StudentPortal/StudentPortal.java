package StudentPortal;

import AccountService.Account;
import Menu.MainMenu;
import PortalDatabase.Database;
import ProfilePage.Profile;
import Verification.Verification;

import java.io.IOException;

public class StudentPortal implements Portal {
    Database db = Database.getInstance();
    Account ac = new Account();
    Profile profile = new Profile();
    MainMenu menuObj = new MainMenu();

    @Override
    public void signUp() throws IOException {
        System.out.println("enter mailId ( student : [name@student.in] || Admin : [name@admin.in] )");
        String mailId = Verification.mailVerification();
        profile.createProfile(ac.createAccount(mailId));
    }

    @Override
    public void signIn() throws IOException {
        System.out.println("enter mailID:");
        String mailId = Verification.mailVerification();
        String setStatus = "";
        while (!db.getId(mailId)) {
            System.out.println("MailId doesn't exists!!!enter registered mailId :");
            System.out.println("do u want to continue in sign in page (y/n)");
            if (Verification.yesOrNoVerification().equals("n")) {
                setStatus = "No";
                break;
            }
            System.out.println("enter mailId:");
            mailId = Verification.mailVerification();
        }
        if (!setStatus.equals("No")) {
            System.out.println("enter ur password");
            String signInPassword = Verification.passwordVerification();
            String password = db.getPassword(mailId);
            while (!signInPassword.equals(password)) {
                System.out.println("password mismatch");
                System.out.println("do u want to reset password(y/n)");
                String input = Verification.yesOrNoVerification();
                if (input.equals("y")) {
                    ac.forgotPassword(mailId);
                    System.out.println("enter ur new password:");
                    signInPassword = Verification.passwordVerification();
                    password = db.getPassword(mailId);
                } else {
                    System.out.println("reenter password");
                    signInPassword = Verification.passwordVerification();

                }
            }
            menuObj.menu(mailId);
        }
    }


    @Override
    public void SignOut() {
        menuObj.SignOut();
    }

}
