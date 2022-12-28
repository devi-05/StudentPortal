package Menu;

import ResultPortal.ExamResultManagement;
import Verification.Verification;

import java.util.Arrays;
import java.util.List;

public class Result {
    public void results(String mailId) {
        ExamResultManagement resultManagement = new ExamResultManagement();
        List<String> studentResultOptions = Arrays.asList("back to menu page","view semester result", "view entire sem result", "calculate cgpa");
        List<String> adminResultOptions = Arrays.asList("back to menu page","view semester result", "view entire sem result", "calculate cgpa", "add result");
        int inputPreference;
        resultPageMenuLoop:while (true) {
            if (Verification.isStudent(mailId)) {
                Verification.printOptions(studentResultOptions);
                System.out.println("enter ur preference:");
                inputPreference = Verification.inputVerification(4);
            } else {
                Verification.printOptions(adminResultOptions);
                System.out.println("enter ur preference:");
                inputPreference = Verification.inputVerification(5);
            }
            switch (inputPreference) {
                case 1 -> { break resultPageMenuLoop;}
                case 2 -> resultManagement.viewCurrentSemResults();
                case 3 -> resultManagement.viewEntireSemResult();
                case 4 -> resultManagement.retrieveCgpa();
                case 5 -> resultManagement.addResult();
            }
        }
    }
}