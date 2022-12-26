package ResultPortal;

import PortalDatabase.Database;
import Verification.Verification;

public class ExamResultManagement {
    ResultGenerator resultGenerator=new ResultGenerator();
    Database db=Database.getInstance();
    public void addResult(){
        System.out.println("enter mailId");
        String mailId = Verification.mailVerification();
        if(Verification.getUserAsStudent(mailId)) {
            if (!db.getId(mailId)) {
                System.out.println("student doesn't exist");
            } else {
                resultGenerator.addResults(mailId);
            }
        }
        else {
            System.out.println("enter student mailId");
        }

    }
    public void viewCurrentSemResults(){
        System.out.println("enter mailId");
        String mailId = Verification.mailVerification();
        if(Verification.getUserAsStudent(mailId)){
        if (!db.getId(mailId)){
            System.out.println("student doesn't exist");}
        else {
        System.out.println("enter semester number");
        int semNum = Verification.inputVerification(8);
            if(db.getSemNumber(mailId,semNum)){
        System.out.println(resultGenerator.semResult(mailId, semNum));
        System.out.println("GPA OF "+semNum+":"+resultGenerator.retrieveGpa(mailId,semNum));}
        else {
                System.out.println("results are not updated");}
        }
        }
        else {
            System.out.println("enter student mail Id");
             viewCurrentSemResults();
        }
    }
    public void viewEntireSemResult() {

        System.out.println("enter mailId:");
        String mailId = Verification.mailVerification();
        if(Verification.getUserAsStudent(mailId)) {
            if (!db.getId(mailId)) {
                System.out.println("student doesn't exist");
            } else {
                if (db.getMailForResults(mailId)) {
                    for (int i = 1; i <= resultGenerator.entireSemResults(mailId).size(); i++) {
                        System.out.println(i + ":" + resultGenerator.entireSemResults(mailId).get(i));
                    }
                } else {
                    System.out.println("results are not updated");
                }
            }
        }
        else {
            System.out.println("enter student mailId");
            viewEntireSemResult();
        }
    }
    public void  retrieveCgpa(){
        System.out.println("enter mail id:");
        String mailId = Verification.mailVerification();
        if(Verification.getUserAsStudent(mailId)) {
            if (!db.getId(mailId)) {
                System.out.println("student doesn't exist");
            } else {
                if (db.getEntireSemResult(mailId) != null) {
                    resultGenerator.CalculateCgpa(mailId);
                    System.out.println(db.getCgpa(mailId));
                } else {
                    System.out.println("results are not updated");
                }
            }
        }
        else {
            System.out.println("enter student mailId");
        }
    }

}
