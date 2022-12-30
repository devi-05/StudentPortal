package Database;

public interface AccountDatabase {
    public String getPassword(String mailId);
    public void addIdAndPassword(String mailId,String password);
}
