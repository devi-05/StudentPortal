package Menu;

import AdminUi.AdminMainMenuPage;
import Helper.Verification;
import StudentUi.StudentMainMenuPage;

import java.io.IOException;

public class MainMenu {
    public void menu(String mailId) throws IOException {
        StudentMainMenuPage studentMainMenuPage = new StudentMainMenuPage();
        AdminMainMenuPage adminMainMenuPage = new AdminMainMenuPage();
        if (Verification.isStudent(mailId)) {
            studentMainMenuPage.getStudentMainMenuPage(mailId);
        } else {
            adminMainMenuPage.getAdminMainMenuPage(mailId);
        }
    }
}