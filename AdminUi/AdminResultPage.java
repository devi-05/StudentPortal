package AdminUi;

import Helper.UtilFunction;
import Helper.Verification;
import ResultPortal.ExamResultManagement;

public class AdminResultPage extends ExamResultManagement {
    public void getAdminResultPage(){
        AdminResultPage adminResultPage=new AdminResultPage();
        AdminResultMenuLoop:while (true){
            UtilFunction.printOptions(AdminResultMenu.values());
            int input= Verification.inputVerification(AdminResultMenu.values().length);
            AdminResultMenu preference=AdminResultMenu.values()[input-1];
            switch (preference){
                case ADD_RESULT -> adminResultPage.addResult();
                case VIEW_SEMESTER_RESULT -> adminResultPage.viewCurrentSemResults();
                case VIEW_ENTIRE_SEM_RESULT -> adminResultPage.viewEntireSemResult();
                case CALCULATE_CGPA -> adminResultPage.retrieveCgpa();
                case BACK_TO_MENU_PAGE -> {
                    break AdminResultMenuLoop;
                }
            }
        }
    }
}
