package PortalDatabase;

import Helper.Verification;
import PortalUsers.Admin;
import PortalUsers.Student;
import ProfilePage.Department;
import ProfilePage.ResidentialStatus;
import ProfilePage.UserProfileDetails;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Database {
    private static Database db = null;
    private final Map<String, String> mailAndPassword = new HashMap<>();
    private final Map<String, Student> studentDetails = new HashMap<>();
    private final Map<String, Admin> adminDetails = new HashMap<>();
    private final Map<String, Map<Integer, Integer>> totalCreditsDatabase = new HashMap<>();
    private final Map<String, Integer> semesterAdded = new HashMap<>();
    private final Map<String, Map<Integer, Double>> totalCreditsWithPoints = new HashMap<>();
    private final Map<String, Map<Integer, Formatter>> wholeSemesterResult = new HashMap<>();
    private final Map<String, Map<Integer, Double>> studentGpaDatabase = new HashMap<>();
    private final Map<String, Double> studentCgpaDatabase = new HashMap<>();

    private Database() {
    }

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public void addNewStudent(String mailId, Student s) {
        studentDetails.put(mailId, s);
    }

    public void addNewAdmin(String mailId, Admin a) {
        adminDetails.put(mailId, a);
    }

    public void addIdAndPassword(String mailId, String password) {
        mailAndPassword.put(mailId, password);
    }

    public boolean getId(String mailId) {
        return mailAndPassword.containsKey(mailId);
    }

    public String getPassword(String mailId) {
        return mailAndPassword.get(mailId);
    }

    public Object getUserData(String mailId) {
        if (Verification.isStudent(mailId)) {
            return studentDetails.get(mailId);
        } else {
            return adminDetails.get(mailId);
        }
    }

    public void editData(String mailId, String attribute, String newInput) {
        if (Verification.isStudent(mailId)) {
            if (attribute.equals("address")) {
                studentDetails.get(mailId).setAddress(newInput);
            } else if (attribute.equals("phoneNumber")) {
                studentDetails.get(mailId).setPhoneNumber(newInput);
            }

        } else {
            if (attribute.equals("address")) {
                adminDetails.get(mailId).setAddress(newInput);
            } else if (attribute.equals("phoneNumber")) {
                adminDetails.get(mailId).setPhoneNumber(newInput);
            }
        }
    }

    public String getName(String mailId) {
        return studentDetails.get(mailId).getName();
    }

    public String getRollNum(String mailId) {
        return studentDetails.get(mailId).getRollNumber();
    }

    public Department getDepartment(String mailId) {
        return studentDetails.get(mailId).getDepartment();
    }

    public void updateFees(String mailId, long feesPaid, long feeBalance) {
        studentDetails.get(mailId).setFeesPaid(feesPaid);
        studentDetails.get(mailId).setFeeBalance(feeBalance);
    }

    public void setFeeBalance(String mailId, long feeBalance) {
        studentDetails.get(mailId).setFeeBalance(feeBalance);
    }

    public long getModeOfJoiningFees(String mailId) {
        return studentDetails.get(mailId).getModeOfJoiningFees();
    }

    public void setResidentialStatus(String mailId, ResidentialStatus residentialStatus) {
        studentDetails.get(mailId).setResidentialStatus(residentialStatus);
        updateResidentialFees(mailId);
    }

    public void updateResidentialFees(String mailId) {
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        long totalFees = userProfileDetails.getCalculatedTotalFees(db.getModeOfJoiningFees(mailId), db.getTransportFees(mailId), db.getMiscellaneousFees(mailId));
        studentDetails.get(mailId).setTotalFees(totalFees);
//        long oldFeesBalance = studentDetails.get(mailId).getFeeBalance();
//        studentDetails.get(mailId).setFeeBalance(oldFeesBalance + studentDetails.get(mailId).getTotalFees());

    }

    public long getResidentialStatusFees(String mailId) {
        return studentDetails.get(mailId).getResidentialStatusFees();
    }

    public long getTransportFees(String mailId) {
        return studentDetails.get(mailId).getTransportFees();
    }

    public long getMiscellaneousFees(String mailId) {
        return studentDetails.get(mailId).getMiscellaneousFees();
    }

    public long getTotalFees(String mailId) {
        return studentDetails.get(mailId).getTotalFees();
    }

    public long getFeesPaid(String mailId) {
        return studentDetails.get(mailId).getFeesPaid();
    }

    public long getFeesBalance(String mailId) {
        return studentDetails.get(mailId).getFeeBalance();
    }


    public void addTotalCredits(String mailId, int semNum, int credits) {
        Map<Integer, Integer> totalCredits = new HashMap<>();
        if (totalCreditsDatabase.get(mailId) != null) {
            Map<Integer, Integer> creditMap = totalCreditsDatabase.get(mailId);
            creditMap.put(semNum, credits);
            totalCreditsDatabase.put(mailId, creditMap);
        } else {
            totalCredits.put(semNum, credits);
            totalCreditsDatabase.put(mailId, totalCredits);
        }
    }

    public double getTotalCredits(String mailId) {
        int j = 0;
        for (int i = 1; i <= totalCreditsDatabase.get(mailId).size(); i++) {
            j += totalCreditsDatabase.get(mailId).get(i);
        }
        return j;
    }

    public double getTotalGradePoints(String mailId) {
        int j = 0;

        for (int i = 1; i <= totalCreditsWithPoints.get(mailId).size(); i++) {
            j += totalCreditsWithPoints.get(mailId).get(i);
        }
        return j;

    }

    public void addTotalCreditWithGrades(String mailId, int semNum, Double grades) {
        Map<Integer, Double> totalGradePoints = new HashMap<>();
        if (totalCreditsWithPoints.get(mailId) != null) {
            Map<Integer, Double> map = totalCreditsWithPoints.get(mailId);
            map.put(semNum, grades);
            totalCreditsWithPoints.put(mailId, map);

        } else {
            totalGradePoints.put(semNum, grades);
            totalCreditsWithPoints.put(mailId, totalGradePoints);
        }

    }

    public void addEntireSemResults(String mailId, int semNum, Formatter fmt) {
        Map<Integer, Formatter> formatter = new HashMap<>();
        if (wholeSemesterResult.get(mailId) == null) {
            formatter.put(semNum, fmt);
            wholeSemesterResult.put(mailId, formatter);
        }
        if (!wholeSemesterResult.get(mailId).containsKey(semNum)) {
            Map<Integer, Formatter> map = wholeSemesterResult.get(mailId);
            map.put(semNum, fmt);
            wholeSemesterResult.put(mailId, map);
        }
    }

    public void addSemesters(String mailId, int sem) {
        semesterAdded.put(mailId, sem);
    }

    public Integer getSemesterAdded(String mailId) {

        return semesterAdded.get(mailId);

    }

    public void addGpa(String mailId, int semNum, double gpa) {
        HashMap<Integer, Double> hash = new HashMap<>();
        if (studentGpaDatabase.get(mailId) != null) {
            Map<Integer, Double> gpaMap = studentGpaDatabase.get(mailId);
            gpaMap.put(semNum, gpa);
            studentGpaDatabase.put(mailId, gpaMap);
        } else {
            hash.put(semNum, gpa);
            studentGpaDatabase.put(mailId, hash);
        }
    }

    public void addCgpa(String mailId, double cgpa) {
        studentCgpaDatabase.put(mailId, cgpa);
    }

    public Formatter getSemResult(String mailId, int semNum) {
        return wholeSemesterResult.get(mailId).get(semNum);
    }

    public Map<Integer, Formatter> getEntireSemResult(String mailId) {
        return wholeSemesterResult.get(mailId);
    }

    public Double getSemesterGpa(String mailId, int semNum) {
        return studentGpaDatabase.get(mailId).get(semNum);
    }

    public Double getCgpa(String mailId) {
        return studentCgpaDatabase.get(mailId);
    }

    public boolean getSemNumber(String mailId, int semNum) {
        if (wholeSemesterResult.get(mailId) == null) {
            return false;
        } else {
            return wholeSemesterResult.get(mailId).containsKey(semNum);
        }
    }

    public boolean getMailForResults(String mailId) {
        return wholeSemesterResult.containsKey(mailId);
    }
    public Set<String> getEntireStudentMailId(){
        return studentDetails.keySet();
    }
}