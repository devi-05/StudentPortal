package Database;

import java.util.Formatter;
import java.util.Map;

public interface ResultDatabase {
    public void addTotalCredits(String mailId, int semNum, int credits);
    public double getTotalCredits(String mailId);
    public double getTotalGradePoints(String mailId);
    public void addTotalCreditWithGrades(String mailId, int semNum, Double grades);
    public void addEntireSemResults(String mailId, int semNum, Formatter fmt);
    public void addSemesters(String mailId, int sem);
    public Integer getSemesterAdded(String mailId);
    public void addGpa(String mailId, int semNum, double gpa);
    public void addCgpa(String mailId, double cgpa);
    public Formatter getSemResult(String mailId, int semNum);
    public Map<Integer, Formatter> getEntireSemResult(String mailId);
    public Double getSemesterGpa(String mailId, int semNum);
    public Double getCgpa(String mailId);
    public boolean getSemNumber(String mailId, int semNum);
    public boolean getMailForResults(String mailId);
}
