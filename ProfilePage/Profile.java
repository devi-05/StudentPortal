package ProfilePage;

import Helper.UtilFunction;
import Menu.MainMenu;
import PortalDatabase.Database;
import PortalUsers.Admin;
import PortalUsers.Student;
import Helper.Verification;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Profile {
    private final Database db = Database.getInstance();
    private final MainMenu menuObj = new MainMenu();

    public void createProfile(String mailId) throws IOException {
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
            UtilFunction.printOptions(BloodGroup.values());
            int bloodGroupPreference = Verification.inputVerification(BloodGroup.values().length);
            BloodGroup bloodGroup = BloodGroup.values()[bloodGroupPreference - 1];
            if (Verification.isStudent(mailId)) {
                String rollNumber = "S" + rollNum++;
                System.out.println("Enter department :");
                UtilFunction.printOptions(Department.values());
                int deptOption = Verification.inputVerification(Department.values().length);
                Department department = Department.values()[deptOption - 1];
                int joiningYear = Verification.StudentJoiningYear();
                long transportFees = 40000;
                long miscellaneousFees = 45000;
                long totalFees = transportFees + miscellaneousFees;
                System.out.println("Enter mode of joining:");
                UtilFunction.printOptions(Modes.values());
                int modePreference = Verification.inputVerification(Modes.values().length);
                Modes modeOfJoining = Modes.values()[modePreference - 1];
                totalFees += modeOfJoining.getFees();
                System.out.println("Enter your residential status");
                UtilFunction.printOptions(ResidentialStatus.values());
                int resStatusPreference = Verification.inputVerification(ResidentialStatus.values().length);
                ResidentialStatus residentialStatus = ResidentialStatus.values()[resStatusPreference - 1];
                totalFees += residentialStatus.getFees();
                long feesPaid = 0;
                Student newStudent = new Student(mailId, name, bloodGroup, address, phoneNumber, rollNumber, department, joiningYear, modeOfJoining, residentialStatus, modeOfJoining.getFees(), residentialStatus.getFees(), transportFees, miscellaneousFees, totalFees, feesPaid);
                db.addNewStudent(mailId, newStudent);
            } else {
                String employeeId = "A" + rollNum++;
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatterDateAndYear = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
                String dateOfJoining = date.format(formatterDateAndYear);
                Admin newAdmin = new Admin(mailId, name, bloodGroup, address, phoneNumber, employeeId, dateOfJoining);
                db.addNewAdmin(mailId, newAdmin);
            }
            System.out.println("Created ur profile successfully");
            System.out.println("Do you want to continue and move to menu page(y/n)");
            if (Verification.yesOrNoVerification().equals("y")) {
                menuObj.menu(mailId);
            }
        }

    }

    public void viewProfile(String mailId) {

        System.out.println(db.getUserData(mailId));
    }

    public void editProfile(String mailId) throws IOException {
        editProfileMenuLoop:
        while (true) {
            System.out.println("Choose category which u want to edit:");
            UtilFunction.printOptions(EditProfileOptions.values());
            int input = Verification.inputVerification(EditProfileOptions.values().length);
            EditProfileOptions preference=EditProfileOptions.values()[input-1];
            switch (preference) {
                case ADDRESS:
                    String attribute = "address";
                    System.out.println("Enter ur new address");
                    String newAddress = Verification.addressVerification();
                    db.editData(mailId, attribute, newAddress);
                    break;
                case PHONE_NUMBER:
                    attribute = "phoneNumber";
                    System.out.println("Enter ur new phone number");
                    String newPhoneNumber = Verification.phoneNumVerification();
                    db.editData(mailId, attribute, newPhoneNumber);
                    break;
                case BACK_TO_PROFILE_PAGE:
                    break editProfileMenuLoop;

            }
        }
    }
}
