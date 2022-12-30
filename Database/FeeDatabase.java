package Database;
import ProfilePage.Department;
public interface FeeDatabase {
    public long getTotalFees(String mailId);
    public long getFeesPaid(String mailId);
    public long getResidentialStatusFees(String mailId);
    public long getModeOfJoiningFees(String mailId);
    public long getTransportFees(String mailId);
    public long getMiscellaneousFees(String mailId);
    public void updateFees(String mailId, long totalFees, long feesPaid);
    public String getName(String mailId);
    public String getRollNum(String mailId);
    public Department getDepartment(String mailId);
}
