package Menu;

import Helper.UtilFunction;
import Helper.Verification;
import ResultPortal.ExamResultManagement;

public class Result {
    public void results(String mailId) {
        ExamResultManagement resultManagement = new ExamResultManagement();
        int inputPreference;
        Enum preference;
        resultPageMenuLoop:
        while (true) {
            System.out.println("Welcome to result portal");
            if (Verification.isStudent(mailId)) {
                StudentResultMenu[] studentResultMenus=StudentResultMenu.values();
                UtilFunction.printOptions(studentResultMenus);
                System.out.println("Enter ur preference:");
                inputPreference = Verification.inputVerification(studentResultMenus.length);
                preference=StudentResultMenu.values()[inputPreference-1];

            } else {
                AdminResultMenu[] adminResultMenus=AdminResultMenu.values();
                UtilFunction.printOptions(adminResultMenus);
                System.out.println("Enter ur preference:");
                inputPreference = Verification.inputVerification(adminResultMenus.length);
                preference=AdminResultMenu.values()[inputPreference-1];
            }
            switch (String.valueOf(preference)) {
                case "BACK_TO_MENU_PAGE" -> {
                    break resultPageMenuLoop;
                }
                case "VIEW_SEMESTER_RESULT" -> resultManagement.viewCurrentSemResults();
                case "VIEW_ENTIRE_SEM_RESULT"-> resultManagement.viewEntireSemResult();
                case "CALCULATE_CGPA"-> resultManagement.retrieveCgpa();
                case "ADD_RESULT" -> resultManagement.addResult();
            }
        }
    }
}