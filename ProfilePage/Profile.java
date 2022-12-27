package ProfilePage;

import AccountService.Account;
import Menu.MainMenu;
import PortalDatabase.Database;
import PortalUsers.Admin;
import PortalUsers.Student;
import Verification.Verification;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Profile {
    Database db = Database.getInstance();
    Account ac = new Account();
    MainMenu menuObj = new MainMenu();

    public void createProfile(String mailId) throws IOException {
        if (mailId != null) {
            System.out.println("enter your details to create your profile");
            int rollNum = 1;
            System.out.println("enter name (note: name should match this e.g John-D or John.D):");
            String name = Verification.nameVerification();
            System.out.println("enter phone number");
            String phoneNumber = Verification.phoneNumVerification();
            System.out.println("enter address");
            String address = Verification.addressVerification();
            System.out.println("enter blood group:");
            List<String>bloodGroupList= Arrays.asList("O_positive", "O_negative","A_positive","A_negative", "B_positive", "B_negative", "AB_negative");
            Verification.printOptions(bloodGroupList);
            int bloodGroupPreference = Verification.inputVerification(7);
            String bloodGrpPreference=bloodGroupList.get(bloodGroupPreference-1);
            BloodGroup bloodGroup = BloodGroup.valueOf(bloodGrpPreference);
            if (Verification.isStudent(mailId)) {
                String rollNumber = "s" + rollNum++;
                System.out.println("enter department :");
                List<String>departmentList= Arrays.asList("MECHANICAL", "CIVIL","ECE","EEE", "AUTO_MOBILE", "CHEMICAL", "MECHATRONICS","BIOTECHNOLOGY","BIOMEDICAL","FOOD_TECHNOLOGY","COMPUTER_SCIENCE","INFORMATION_TECHNOLOGY");
                Verification.printOptions(departmentList);
                int deptOption = Verification.inputVerification(12);
                String dept=departmentList.get(deptOption-1);
                Department department = Department.valueOf(dept);
                int joiningYear = Verification.StudentJoiningYear();
                long transportFees = 40000;
                long miscellaneousFees = 45000;
                long totalFees = transportFees + miscellaneousFees;
                List<String>modeOfJoiningList= Arrays.asList("COUNSELING", "MANAGEMENT");
                System.out.println("enter mode of joining:");
                Verification.printOptions(modeOfJoiningList);
                int modePreference = Verification.inputVerification(2);
                String mode=modeOfJoiningList.get(modePreference-1);
                Modes modeOfJoining = Modes.valueOf(mode);
                totalFees += modeOfJoining.getFees();
                List<String>residentialList= Arrays.asList("DAY_SCHOLAR", "HOSTELER");
                System.out.println("enter your residential status");
                Verification.printOptions(residentialList);
                int resStatusPreference = Verification.inputVerification(2);
                String resStatus=residentialList.get(resStatusPreference-1);
                ResidentialStatus residentialStatus = ResidentialStatus.valueOf(resStatus);
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
            System.out.println("created ur profile successfully");
            System.out.println("do you want to continue and move to menu page(y/n)");
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
            System.out.println("choose category which u want to edit:");
            System.out.println("""
                    1.address
                    2.phone Number
                    3.back to profile page""");
            int preference = Verification.inputVerification(3);
            switch (preference) {
                case 1:
                    String attribute = "address";
                    System.out.println("enter ur new address");
                    String newAddress = Verification.addressVerification();
                    db.editData(mailId, attribute, newAddress);
                    break;
                case 2:
                    attribute = "phoneNumber";
                    System.out.println("enter ur new phone number");
                    String newPhoneNumber = Verification.phoneNumVerification();
                    db.editData(mailId, attribute, newPhoneNumber);
                    break;
                case 3:
                    break editProfileMenuLoop;

            }
        }
    }
}
