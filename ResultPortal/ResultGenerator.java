package ResultPortal;

import PortalDatabase.Database;
import Verification.Verification;

import java.util.*;

public class ResultGenerator {
    Database db=Database.getInstance();
    Map<Integer, Double> totalGradePoints=new HashMap<>();
    public void addResults(String mailId) {
        List<String> Subjects = new ArrayList<>();
        List<Integer> Credits = new ArrayList<>();
        List<GradeValues> Grades = new ArrayList<>();
        List<Double> GradePoints = new ArrayList<>();
        System.out.println("enter semester number:");
        int semNum = Verification.inputVerification(8);
        if(db.getSemesterAdded(mailId)==null){
            while (semNum!=1){
                System.out.println("enter first semester results");
                semNum=Verification.inputVerification(8);
            }
        }
        if(db.getSemesterAdded(mailId)!= null) {
            while ((db.getSemesterAdded(mailId)+1)!=(semNum)) {
                System.out.println("enter correct semester number");
                semNum = Verification.inputVerification(8);
            }
        }
            if (!db.getSemNumber(mailId,semNum)){
                db.addSemesters(mailId,semNum);
            System.out.println("enter number of subjects of that semester:");
            int subNum = Verification.inputVerification(8);
            int totalCreditsSum = 0;
            for (int i = 0; i < subNum; i++) {
                System.out.println("enter subject name:");
                String subject = Verification.semesterSubjectVerification();
                Subjects.add(subject);
                System.out.println("enter credits: [1 to 5]");
                int subCredit = Verification.inputVerification(5);
                totalCreditsSum += subCredit;
                Credits.add(subCredit);
                System.out.println("enter grades:");
                System.out.println("1.O   2.A   3.B   4.C   5.D");
                int grade = Verification.inputVerification(5);
                List <GradeValues> gradeValList=Arrays.asList(GradeValues.values());
                Grades.add(gradeValList.get(grade-1));
                GradePoints.add((double) (subCredit* gradeValList.get(grade-1).getPoint()));
            }
            db.addTotalCredits(mailId, semNum,totalCreditsSum);
            double grade = 0;
            for (int i = 0; i < GradePoints.size(); i++) {
                grade += GradePoints.get(i);
            }
            //totalGradePoints.put(semNum, grade);
            db.addTotalCreditWithGrades(mailId, semNum,grade);
            Formatter fmt = new Formatter();
            fmt.format("%17s%17s%17s\n", "subjects", "credits", "grades");
            for (int i = 0; i < Subjects.size(); i++) {
                fmt.format("%17s%17s%17s\n", Subjects.get(i), Credits.get(i), Grades.get(i));
            }
            db.addEntireSemResults(mailId, semNum,fmt);
            double gpa = (grade) / ((double) totalCreditsSum);
                System.out.println(gpa);
            db.addGpa(mailId, semNum,Double.parseDouble(String.format("%.2f", gpa)));
        }
            else {
                System.out.println("results were added!!!!");
                addResults(mailId);
            }
    }
        public void CalculateCgpa (String mailId){
        //TODO get
            double cgpaTotalCredits = db.getTotalCredits(mailId);
            double cgpaTotalGradePoints = db.getTotalGradePoints(mailId);
            double cgpa = cgpaTotalGradePoints / cgpaTotalCredits;
            db.addCgpa(mailId, Double.parseDouble(String.format("%.2f", cgpa)));
        }
        public Formatter semResult (String mailId, int semNumber){
            return db.getSemResult(mailId, semNumber);
        }
        public Double retrieveGpa (String mailId,int semNumber){
            return db.getSemesterGpa(mailId, semNumber);
        }

        public Map<Integer, Formatter> entireSemResults (String mailId){
            return db.getEntireSemResult(mailId);
        }

    }


