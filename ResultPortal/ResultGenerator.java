package ResultPortal;

import Helper.UtilFunction;
import PortalDatabase.Database;
import Helper.Verification;

import java.util.*;

public class ResultGenerator {
    private final Database db = Database.getInstance();

    public void addResults(String mailId) {
        List<String> Subjects = new ArrayList<>();
        List<Integer> Credits = new ArrayList<>();
        List<GradeValues> Grades = new ArrayList<>();
        List<Double> GradePoints = new ArrayList<>();
        System.out.println("Enter semester number:");
        int semNum = Verification.inputVerification(8);
        if (db.getSemesterAdded(mailId) == null) {
            while (semNum != 1) {
                System.out.println("Enter first semester results");
                semNum = Verification.inputVerification(8);
            }
        }
        if (db.getSemesterAdded(mailId) != null) {
            while ((db.getSemesterAdded(mailId) + 1) != (semNum)) {
                System.out.println("Enter correct semester number");
                semNum = Verification.inputVerification(8);
            }
        }
        if (!db.getSemNumber(mailId, semNum)) {
            db.addSemesters(mailId, semNum);

            System.out.println("Enter number of subjects of that semester:");
            int subNum = Verification.inputVerification(8);
            int totalCreditsSum = 0;
            for (int i = 0; i < subNum; i++) {

                System.out.println("Enter subject name:");
                String subject = Verification.semesterSubjectVerification();
                Subjects.add(subject);

                System.out.println("Enter credits: [1 to 5]");
                int subCredit = Verification.inputVerification(5);
                totalCreditsSum += subCredit;
                Credits.add(subCredit);

                System.out.println("Enter grades:");
                GradeValues[] gradeValues=GradeValues.values();
                UtilFunction.printOptions(gradeValues);
                int gradePreference = Verification.inputVerification(gradeValues.length);
                GradeValues grades = gradeValues[gradePreference - 1];
                Grades.add(grades);
                GradePoints.add((double) (subCredit * grades.getPoint()));
            }
            db.addTotalCredits(mailId, semNum, totalCreditsSum);
            double grade = 0;
            for (Double gradePoint : GradePoints) {
                grade += gradePoint;
            }
            db.addTotalCreditWithGrades(mailId, semNum, grade);
            Formatter fmt = new Formatter();
            fmt.format("%17s%17s%17s\n", "subjects", "credits", "grades");
            for (int i = 0; i < Subjects.size(); i++) {
                fmt.format("%17s%17s%17s\n", Subjects.get(i), Credits.get(i), Grades.get(i));
            }
            db.addEntireSemResults(mailId, semNum, fmt);
            double gpa = (grade) / ((double) totalCreditsSum);
            db.addGpa(mailId, semNum, Double.parseDouble(String.format("%.2f", gpa)));
        } else {
            System.out.println("Results were added!!!!");
            addResults(mailId);
        }
    }

    public void calculateCgpa(String mailId) {
        double cgpaTotalCredits = db.getTotalCredits(mailId);
        double cgpaTotalGradePoints = db.getTotalGradePoints(mailId);
        double cgpa = cgpaTotalGradePoints / cgpaTotalCredits;
        db.addCgpa(mailId, Double.parseDouble(String.format("%.2f", cgpa)));

    }

    public Formatter semResult(String mailId, int semNumber) {
        return db.getSemResult(mailId, semNumber);
    }

    public Double retrieveGpa(String mailId, int semNumber) {
        return db.getSemesterGpa(mailId, semNumber);
    }

    public Map<Integer, Formatter> entireSemResults(String mailId) {
        return db.getEntireSemResult(mailId);
    }

}


