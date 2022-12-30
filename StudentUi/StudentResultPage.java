package StudentUi;

import Helper.UtilFunction;
import Helper.Verification;
import ResultPortal.ExamResultManagement;

public class StudentResultPage {
    public void getStudentResultPage() {
        ExamResultManagement examResultMgt = new ExamResultManagement();
        StudentResultMenuLoop:
        while (true) {
            UtilFunction.printOptions(StudentResultMenu.values());
            int input = Verification.inputVerification(StudentResultMenu.values().length);
            StudentResultMenu preference = StudentResultMenu.values()[input-1];
            switch (preference) {
                case VIEW_SEMESTER_RESULT -> examResultMgt.viewCurrentSemResults();
                case VIEW_ENTIRE_SEM_RESULT -> examResultMgt.viewEntireSemResult();
                case CALCULATE_CGPA -> examResultMgt.retrieveCgpa();
                case BACK_TO_MENU_PAGE -> {
                    break StudentResultMenuLoop;
                }
            }
        }
    }
}