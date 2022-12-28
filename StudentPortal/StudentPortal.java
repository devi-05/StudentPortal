package StudentPortal;

import AccountService.Account;
import Helper.UtilFunction;
import Menu.MainMenu;
import PortalDatabase.Database;
import ProfilePage.Profile;
import Helper.Verification;

import java.io.IOException;

public class StudentPortal implements Portal {
    private final Database db = Database.getInstance();
    private final Account ac = new Account();
    private final Profile profile = new Profile();
    private final MainMenu menuObj = new MainMenu();

    @Override
    public void signUp() throws IOException {
        System.out.println("Enter mailId ( student : [name@student.in] || Admin : [name@admin.in] )");
        String mailId = Verification.mailVerification();
        profile.createProfile(ac.createAccount(mailId));
    }

    @Override
    public void signIn() throws IOException {
        System.out.println("Enter mailID:");
        String mailId = Verification.mailVerification();
        String setStatus = "";
        while (!db.getId(mailId)) {
            System.out.println("MailId doesn't exists!!!enter registered mailId :");
            System.out.println("Do u want to continue in sign in page (y/n)");
            if (Verification.yesOrNoVerification().equals("n")) {
                setStatus = "No";
                break;
            }
            System.out.println("Enter mailId:");
            mailId = Verification.mailVerification();
        }
        if (!setStatus.equals("No")) {
            System.out.println("Enter ur password");
            String signInPassword = Verification.passwordVerification();
            String password = db.getPassword(mailId);
            while (!signInPassword.equals(password)) {
                System.out.println("Password mismatch");
                System.out.println("Do u want to reset password(y/n)");
                String input = Verification.yesOrNoVerification();
                if (input.equals("y")) {
                    ac.forgotPassword(mailId);
                    System.out.println("Enter ur new password:");
                    signInPassword = Verification.passwordVerification();
                    password = db.getPassword(mailId);
                } else {
                    System.out.println("Reenter password");
                    signInPassword = Verification.passwordVerification();

                }
            }
            UtilFunction.getWelcomeMessage(mailId);
            menuObj.menu(mailId);
        }
    }


    @Override
    public void SignOut() {
        menuObj.SignOut();
    }

}
