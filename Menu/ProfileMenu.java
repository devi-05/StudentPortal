package Menu;

import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;
import ProfilePage.Profile;

import java.io.IOException;
import java.util.Arrays;

public class ProfileMenu {
    Database db = Database.getInstance();

    public void profileOption(String mailId) throws IOException {
        Profile profile = new Profile();
        int input;
        profileMenuLoop:
        while (true) {
            if (Verification.isStudent(mailId)) {
                UtilFunction.printOptions(Arrays.copyOf(ProfileMenuOptions.values(), ProfileMenuOptions.values().length - 2));
                System.out.println("enter ur preference:");
                input = Verification.inputVerification(ProfileMenuOptions.values().length-2);
            } else {
                UtilFunction.printOptions(ProfileMenuOptions.values());
                System.out.println("enter ur preference:");
                input = Verification.inputVerification(ProfileMenuOptions.values().length);
            }
            ProfileMenuOptions preference=ProfileMenuOptions.values()[input-1];
            switch (preference) {
                case BACK_TO_MENU_PAGE -> {
                    break profileMenuLoop;
                }
                case VIEW_OWN_PROFILE -> profile.viewProfile(mailId);
                case EDIT_OWN_PROFILE -> profile.editProfile(mailId);
                case VIEW_STUDENT_PROFILE -> {
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
                case EDIT_STUDENT_PROFILE -> {
                    System.out.println("enter student mailID:");
                    String studentMailId = Verification.mailVerification();

                    if (!Verification.isStudent(studentMailId)) {
                        System.out.println("enter student mailId");
                        studentMailId = Verification.mailVerification();
                    }
                    if (!db.getId(studentMailId)) {
                        System.out.println("student doesn't exist");
                    } else {
                        profile.editProfile(studentMailId);
                    }
                }


            }

        }

    }
}

