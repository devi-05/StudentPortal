package Menu;

import PortalDatabase.Database;
import ProfilePage.Profile;
import Verification.Verification;

import java.io.IOException;

public class ProfileMenu {
    Database db = Database.getInstance();

    public void profileOption(String mailId) throws IOException {
        Profile profile = new Profile();
        if (Verification.getUserAsStudent(mailId)) {
            studentProfilePageMenuLoop:
            while (true) {
                System.out.println("1.view profile" + "\n2.edit profile" + "\n3.back to menu page");
                int input = Verification.inputVerification(3);
                switch (input) {
                    case 1 -> profile.viewProfile(mailId);
                    case 2 -> profile.editProfile(mailId);
                    case 3 -> {
                        break studentProfilePageMenuLoop;
                    }
                }
            }
        } else {
            adminProfilePageMenuLoop:
            while (true) {
                System.out.println("""
                        1.view own profile
                        2.edit profile
                        3.view student profile
                        4.Back to menu page""");
                int input = Verification.inputVerification(4);
                switch (input) {
                    case 1 -> profile.viewProfile(mailId);
                    case 2 -> profile.editProfile(mailId);
                    case 3 -> {
                        System.out.println("enter student mailID:");
                        String studentMailId = Verification.mailVerification();

                        if (!Verification.getUserAsStudent(studentMailId)) {
                            System.out.println("enter student mailId");
                            studentMailId = Verification.mailVerification();
                        }
                        if (!db.getId(studentMailId)) {
                            System.out.println("student doesn't exist");
                        } else {
                            profile.viewProfile(studentMailId);
                        }
                    }
                    case 4 -> {
                        break adminProfilePageMenuLoop;
                    }
                }
            }
        }
    }
}
