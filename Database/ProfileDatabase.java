package Database;

import PortalUsers.Admin;
import PortalUsers.Student;

public interface ProfileDatabase {
    public void addNewStudent(String mailId, Student s);
    public void addNewAdmin(String mailId, Admin a);
    public Object getUserData(String mailId);
    public void editData(String mailId, String attribute, String newInput);

}
