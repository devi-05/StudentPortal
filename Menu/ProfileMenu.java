package Menu;

import PortalDatabase.Database;
import ProfilePage.Profile;
import Verification.Verification;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProfileMenu {
    Database db = Database.getInstance();

    public void profileOption(String mailId) throws IOException {
        Profile profile = new Profile();
        int input;
        List<String> studentProfileMenuList = Arrays.asList("back to menu page", "view profile", "edit profile");
        List<String> adminProfileMenuList = Arrays.asList("back to menu page", "view own profile", "edit own profile", "view student profile");
        profileMenuLoop:
        while (true) {
            if (Verification.isStudent(mailId)) {
                Verification.printOptions(studentProfileMenuList);
                System.out.println("enter ur preference:");
                input = Verification.inputVerification(3);
            } else {
                Verification.printOptions(adminProfileMenuList);
                System.out.println("enter ur preference:");
                input = Verification.inputVerification(4);
            }
            switch (input) {
                case 1 -> {
                    break profileMenuLoop;
                }
                case 2 -> profile.viewProfile(mailId);
                case 3 -> profile.editProfile(mailId);
                case 4 -> {
                    System.out.println("enter student mailID:");
                    String studentMailId = Verification.mailVerification();

                    if (!Verification.isStudent(studentMailId)) {
                        System.out.println("enter student mailId");
                        studentMailId = Verification.mailVerification();
                    }
                    if (!db.getId(studentMailId)) {
                        System.out.println("student doesn't exist");
                    } else {
                        profile.viewProfile(studentMailId);
                    }
                }

            }

        }

    }
}

