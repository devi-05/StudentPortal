package AccountService;

import PortalDatabase.Database;
import Helper.Verification;

public class Account {
    private final Database db = Database.getInstance();
    private String password;

    public String createAccount(String mailId) {

        if (db.getId(mailId)) {
            System.out.println("Already existing user");
            System.out.println("Please sign in!!!!");
        } else {
            System.out.println("Enter password: ");
            password = Verification.passwordVerification();
            System.out.println("Re-enter to confirm your password: ");
            String rePassword = Verification.passwordVerification();
            while (!password.equals(rePassword)) {
                System.out.println("Enter above entered password!!");
                rePassword = Verification.passwordVerification();
            }
            db.addIdAndPassword(mailId, password);
            System.out.println("Signed up successfully!!!");
            return mailId;
        }
        return null;
    }

    public void forgotPassword(String mailId) {
        password = db.getPassword(mailId);
        System.out.println("Enter ur new password");
        String newPassword = Verification.passwordVerification();
        while (password.equals(newPassword)) {
            System.out.println("Password entered above is previously entered password ");
            newPassword = Verification.passwordVerification();
        }
        db.addIdAndPassword(mailId, newPassword);
        System.out.println("successfully changed ur password!!!");
    }
}
