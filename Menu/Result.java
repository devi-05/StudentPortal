package Menu;

import Helper.UtilFunction;
import Helper.Verification;
import ResultPortal.ExamResultManagement;

import java.util.Arrays;

public class Result {
    public void results(String mailId) {
        ExamResultManagement resultManagement = new ExamResultManagement();
        int inputPreference;
        resultPageMenuLoop:
        while (true) {
            System.out.println("Welcome to result portal");
            if (Verification.isStudent(mailId)) {
                UtilFunction.printOptions(Arrays.copyOf(ResultMenu.values(), ResultMenu.values().length - 1));
                System.out.println("Enter ur preference:");
                inputPreference = Verification.inputVerification(ResultMenu.values().length-1);
            } else {
                UtilFunction.printOptions(ResultMenu.values());
                System.out.println("Enter ur preference:");
                inputPreference = Verification.inputVerification(ResultMenu.values().length);
            }
            ResultMenu preference=ResultMenu.values()[inputPreference-1];
            switch (preference) {
                case BACK_TO_MENU_PAGE -> {
                    break resultPageMenuLoop;
                }
                case VIEW_SEMESTER_RESULT -> resultManagement.viewCurrentSemResults();
                case VIEW_ENTIRE_SEM_RESULT-> resultManagement.viewEntireSemResult();
                case CALCULATE_CGPA-> resultManagement.retrieveCgpa();
                case ADD_RESULT -> resultManagement.addResult();
            }
        }
    }
}