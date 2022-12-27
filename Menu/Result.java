package Menu;

import ResultPortal.ExamResultManagement;
import Verification.Verification;

public class Result {
    public void results(String mailId) {
        ExamResultManagement resultManagement = new ExamResultManagement();
        if (!Verification.isStudent(mailId)) {
            studentResultPageMenuLoop:
            while (true) {
                System.out.println("""
                        1.add result
                        2.view semester result
                        3.view entire sem result
                        4.calculate cgpa
                        5.back to menu page""");
                int input = Verification.inputVerification(5);
                switch (input) {
                    case 1 -> resultManagement.addResult();
                    case 2 -> resultManagement.viewCurrentSemResults();
                    case 3 -> resultManagement.viewEntireSemResult();
                    case 4 -> resultManagement.retrieveCgpa();
                    case 5 -> {
                        break studentResultPageMenuLoop;
                    }
                }
            }
        } else {
            adminResultPageMenuLoop:
            while (true) {
                System.out.println("""
                        1.view semester result
                        2.view entire sem result
                        3.calculate cgpa
                        4.back to menu page""");
                int input = Verification.inputVerification(4);
                switch (input) {
                    case 1 -> resultManagement.viewCurrentSemResults();
                    case 2 -> resultManagement.viewEntireSemResult();
                    case 3 -> resultManagement.retrieveCgpa();
                    case 4 -> {
                        break adminResultPageMenuLoop;
                    }
                }
            }
        }
    }
}