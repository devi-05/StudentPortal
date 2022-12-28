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
        List<String> studentProfileMenuList = Arrays.asList("view profile", "edit profile");
        List<String> adminProfileMenuList = Arrays.asList("view profile", "edit profile", "view student profile");
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
            System.out.println("""
                    1.view profile
                    2.edit profile
                    3.View student profile""");
            switch (input) {
                case 1 -> profile.viewProfile(mailId);
                case 2 -> profile.editProfile(mailId);
                case 3 -> {
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
            System.out.println("do u want to exit and move to menu page(y/n)");
            String preference = Verification.yesOrNoVerification();
            if (preference.equals("y")) {
                break;
            }
        }

    }
}

