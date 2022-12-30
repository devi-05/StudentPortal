package StudentUi;

import Helper.UtilFunction;
import Helper.Verification;
import Menu.Menu;
import StudentPortal.StudentPortal;

import java.io.IOException;

public class StudentMainMenuPage {
    public void getStudentMainMenuPage(String mailId) throws IOException {
        StudentPortal studentPortal=new StudentPortal();
        StudentProfilePage studentProfilePage=new StudentProfilePage();
        StudentFeePage studentFeePage=new StudentFeePage();
        StudentResultPage studentResultPage = new StudentResultPage();
        StudentMainMenuLoop:while (true){
            System.out.println("Welcome to main page");
            UtilFunction.printOptions(Menu.values());
            System.out.println("Enter option");
            int input= Verification.inputVerification(Menu.values().length);
            Menu preference = Menu.values()[input-1];
            switch (preference){
                case PROFILE_PAGE -> studentProfilePage.getStudentProfilePage(mailId);
                case FEE_PORTAL -> studentFeePage.getStudentFeePage(mailId);
                case RESULT_PORTAL -> studentResultPage.getStudentResultPage();
                case SIGN_OUT -> {
                    studentPortal.SignOut();
                    break StudentMainMenuLoop;}
            }
        }
    }
}
