package AdminUi;

import Helper.UtilFunction;
import Helper.Verification;
import Menu.Menu;
import StudentPortal.StudentPortal;

import java.io.IOException;

public class AdminMainMenuPage {
    public void getAdminMainMenuPage(String mailId) throws IOException {
        StudentPortal studentPortal=new StudentPortal();
        AdminProfilePage adminProfilePage = new AdminProfilePage();
        AdminFeePage adminFeePage = new AdminFeePage();
        AdminResultPage adminResultPage = new AdminResultPage();
        AdminMainMenuLoop:
        while (true) {
            System.out.println("Welcome to main page");
            UtilFunction.printOptions(Menu.values());
            System.out.println("Enter option");
            int input = Verification.inputVerification(Menu.values().length);
            Menu preference = Menu.values()[input-1];
            switch (preference) {
                case PROFILE_PAGE -> adminProfilePage.getAdminProfilePage(mailId);
                case FEE_PORTAL -> adminFeePage.getAdminFeePage(mailId);
                case RESULT_PORTAL -> adminResultPage.getAdminResultPage();
                case SIGN_OUT -> {
                    studentPortal.SignOut();
                    break AdminMainMenuLoop;
                }
            }
        }
    }
}