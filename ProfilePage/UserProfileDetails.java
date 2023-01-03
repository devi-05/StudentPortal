package ProfilePage;

import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;
import PortalUsers.Admin;
import PortalUsers.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserProfileDetails {
    private final Database db = Database.getInstance();

    public void getUserDetails(String mailId) throws IOException {
        if (mailId != null) {
            System.out.println("Enter your details to create your profile");
            int rollNum = 1;

            System.out.println("Enter name (note: name should match this e.g John-D or John.D):");
            String name = Verification.nameVerification();

            System.out.println("Enter phone number");
            String phoneNumber = Verification.phoneNumVerification();

            System.out.println("Enter address");
            String address = Verification.addressVerification();

            System.out.println("Enter blood group:");
            BloodGroup[] bloodGroups=BloodGroup.values();
            UtilFunction.printOptions(bloodGroups);
            int bloodGroupPreference = Verification.inputVerification(bloodGroups.length);
            BloodGroup bloodGroup = bloodGroups[bloodGroupPreference - 1];

            if (Verification.isStudent(mailId)) {
                String rollNumber = "S" + rollNum++;

                System.out.println("Enter department :");
                Department[] departments=Department.values();
                UtilFunction.printOptions(departments);
                int deptOption = Verification.inputVerification(departments.length);
                Department department = departments[deptOption - 1];
                int joiningYear = Verification.StudentJoiningYear();
                long transportFees = 40000;
                long miscellaneousFees = 45000;
                long totalFees = transportFees + miscellaneousFees;

                System.out.println("Enter mode of joining:");
                Modes[] modes=Modes.values();
                UtilFunction.printOptions(modes);
                int modePreference = Verification.inputVerification(modes.length);
                Modes modeOfJoining = modes[modePreference - 1];
                totalFees += modeOfJoining.getFees();

                System.out.println("Enter your residential status");
                ResidentialStatus[] residentialStatuses=ResidentialStatus.values();
                UtilFunction.printOptions(residentialStatuses);
                int resStatusPreference = Verification.inputVerification(residentialStatuses.length);
                ResidentialStatus residentialStatus = residentialStatuses[resStatusPreference - 1];
                totalFees += residentialStatus.getFees();
                long feesPaid = 0;
                Student newStudent = new Student(mailId, name, bloodGroup, address, phoneNumber, rollNumber, department, joiningYear, modeOfJoining, residentialStatus, modeOfJoining.getFees(), residentialStatus.getFees(), transportFees, miscellaneousFees, totalFees, feesPaid);
                db.addNewStudent(mailId, newStudent);
            }
            else {
                String employeeId = "A" + rollNum++;
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatterDateAndYear = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
                String dateOfJoining = date.format(formatterDateAndYear);
                Admin newAdmin = new Admin(mailId, name, bloodGroup, address, phoneNumber, employeeId, dateOfJoining);
                db.addNewAdmin(mailId, newAdmin);
            }
        }
    }
}
