package StudentUi;

import Helper.UtilFunction;
import Helper.Verification;
import ProfilePage.Profile;

import java.io.IOException;

public class StudentProfilePage {
    public void getStudentProfilePage(String mailId) throws IOException {
        Profile profile = new Profile();
        studentProfileMenuLoop:
        while (true) {
            System.out.println("Welcome to profile page");
            UtilFunction.printOptions(StudentProfileMenu.values());
            System.out.println("Enter option:");
            int input = Verification.inputVerification(StudentProfileMenu.values().length);
            StudentProfileMenu preference = StudentProfileMenu.values()[input-1];
            switch (preference) {
                case EDIT_OWN_PROFILE -> profile.editProfile(mailId);
                case VIEW_OWN_PROFILE -> profile.viewProfile(mailId);
                case BACK_TO_MENU_PAGE -> {break studentProfileMenuLoop;}
            }
        }
    }
}