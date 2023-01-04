package Menu;

import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;
import ProfilePage.Profile;

import java.io.IOException;

public class ProfileMenu {
    private final Database db = Database.getInstance();

    public void profileOption(String mailId) throws IOException {
        Profile profile = new Profile();
        int input;
        Enum preference;

        profileMenuLoop:
        while (true) {
            System.out.println("WELCOME TO PROFILE PAGE");
            if (Verification.isStudent(mailId)) {
                StudentProfileMenuOptions[] studentProfileMenuOptions = StudentProfileMenuOptions.values();
                UtilFunction.printOptions(studentProfileMenuOptions);

                System.out.println("Enter ur preference:");
                input = Verification.inputVerification(studentProfileMenuOptions.length);
                preference = studentProfileMenuOptions[input - 1];
            } else {
                AdminProfileMenuOptions[] adminProfileMenuOptions = AdminProfileMenuOptions.values();
                UtilFunction.printOptions(adminProfileMenuOptions);

                System.out.println("Enter ur preference:");
                input = Verification.inputVerification(adminProfileMenuOptions.length);
                preference = adminProfileMenuOptions[input - 1];
            }
            switch (String.valueOf(preference)) {
                case "BACK_TO_MENU_PAGE" -> {
                    break profileMenuLoop;
                }
                case "VIEW_OWN_PROFILE" -> profile.viewProfile(mailId);

                case "EDIT_OWN_PROFILE" -> profile.editProfile(mailId);

                case "VIEW_STUDENT_PROFILE" -> {
                    System.out.println("Enter student mailID:");
                    String studentMailId = Verification.mailVerification();

                    while (!Verification.isStudent(studentMailId)) {
                        System.out.println("Enter student mailId");
                        studentMailId = Verification.mailVerification();
                    }
                    if (!db.getId(studentMailId)) {
                        System.out.println("Student doesn't exist");
                    } else {
                        profile.viewProfile(studentMailId);
                    }
                }
                case "EDIT_STUDENT_PROFILE" -> {
                    System.out.println("Enter student mailID:");
                    if (!db.getId(mailId)) {
                        System.out.println("Student doesn't exist");
                    } else {
                        profile.editProfile(mailId);
                    }
                }


            }

        }

    }
}

