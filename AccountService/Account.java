package AccountService;

import PortalDatabase.Database;
import Verification.Verification;

public class Account {
    Database db = Database.getInstance();
    private String password;

    public String createAccount(String mailId) {

        if (db.getId(mailId)) {
            System.out.println("already existing user");
            System.out.println("please sign in!!!!");
        } else {
            System.out.println("enter password: ");
            password = Verification.passwordVerification();
            System.out.println("Re-enter to confirm your password: ");
            String rePassword = Verification.passwordVerification();
            while (!password.equals(rePassword)) {
                System.out.println("enter above entered password!!");
                rePassword = Verification.passwordVerification();
            }
            db.addIdAndPassword(mailId, password);
            System.out.println("signed up successfully!!!");
            return mailId;
        }
        return null;
    }

    public void forgotPassword(String mailId) {
        password = db.getPassword(mailId);
        System.out.println("enter ur new password");
        String newPassword = Verification.passwordVerification();
        while (password.equals(newPassword)) {
            System.out.println("password entered above is previously entered password ");
            newPassword = Verification.passwordVerification();
        }
        db.addIdAndPassword(mailId, newPassword);
        System.out.println("successfully changed ur password!!!");
    }
}
