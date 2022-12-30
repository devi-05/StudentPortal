package ResultPortal;

import Database.PortalDatabase;
import Helper.Verification;
import Database.MailIdDatabase;
import Database.ResultDatabase;

public class ExamResultManagement {
    private final ResultGenerator resultGenerator = new ResultGenerator();
    private final ResultDatabase db = PortalDatabase.getInstance();
    private final MailIdDatabase dbId= PortalDatabase.getInstance();

    protected void addResult() {
        System.out.println("Enter student mailId");
        String mailId = Verification.mailVerification();
        if (Verification.isStudent(mailId)) {
            if (!dbId.getId(mailId)) {
                System.out.println("Student doesn't exist");
            } else {
                resultGenerator.addResults(mailId);
            }
        } else {
            System.out.println("Enter student mailId");
        }

    }

    public void viewCurrentSemResults() {
        System.out.println("Enter student mailId");
        String mailId = Verification.mailVerification();
        if (Verification.isStudent(mailId)) {
            if (!dbId.getId(mailId)) {
                System.out.println("Student doesn't exist");
            } else {
                System.out.println("Enter semester number");
                int semNum = Verification.inputVerification(8);
                if (db.getSemNumber(mailId, semNum)) {
                    System.out.println(resultGenerator.semResult(mailId, semNum));
                    System.out.println("GPA OF " + semNum + ":" + resultGenerator.retrieveGpa(mailId, semNum));
                } else {
                    System.out.println("Results are not updated");
                }
            }
        } else {
            System.out.println("Enter student mail Id");
            viewCurrentSemResults();
        }
    }

    public void viewEntireSemResult() {

        System.out.println("Enter mailId:");
        String mailId = Verification.mailVerification();
        if (Verification.isStudent(mailId)) {
            if (!dbId.getId(mailId)) {
                System.out.println("Student doesn't exist");
            } else {
                if (db.getMailForResults(mailId)) {
                    for (int i = 1; i <= resultGenerator.entireSemResults(mailId).size(); i++) {
                        System.out.println(i + ":" + resultGenerator.entireSemResults(mailId).get(i));
                    }
                } else {
                    System.out.println("Results are not updated");
                }
            }
        } else {
            System.out.println("Enter student mailId");
            viewEntireSemResult();
        }
    }

    public void retrieveCgpa() {
        System.out.println("Enter mail id:");
        String mailId = Verification.mailVerification();
        if (Verification.isStudent(mailId)) {
            if (!dbId.getId(mailId)) {
                System.out.println("Student doesn't exist");
            } else {
                if (db.getEntireSemResult(mailId) != null) {
                    resultGenerator.calculateCgpa(mailId);
                    System.out.println(db.getCgpa(mailId));
                } else {
                    System.out.println("Results are not updated");
                }
            }
        } else {
            System.out.println("Enter student mailId");
        }
    }

}
