package Menu;

import ResultPortal.ExamResultManagement;
import Verification.Verification;

import java.util.Arrays;
import java.util.List;

public class Result {
    public void results(String mailId) {
        ExamResultManagement resultManagement = new ExamResultManagement();
        List<String> studentResultOptions = Arrays.asList("view semester result", "view entire sem result", "calculate cgpa");
        List<String> adminResultOptions = Arrays.asList("view semester result", "view entire sem result", "calculate cgpa", "add result");
        int inputPreference;
        while (true) {
            if (Verification.isStudent(mailId)) {
                Verification.printOptions(studentResultOptions);
                System.out.println("enter ur preference:");
                inputPreference = Verification.inputVerification(3);
            } else {
                Verification.printOptions(adminResultOptions);
                System.out.println("enter ur preference:");
                inputPreference = Verification.inputVerification(4);
            }
            switch (inputPreference) {
                case 1 -> resultManagement.viewCurrentSemResults();
                case 2 -> resultManagement.viewEntireSemResult();
                case 3 -> resultManagement.retrieveCgpa();
                case 4 -> resultManagement.addResult();
            }
            System.out.println("do u want to exit and move menu page (y/n)");
            String preference = Verification.yesOrNoVerification();
            if (preference.equals("y")) {
                break;
            }
        }
    }
}