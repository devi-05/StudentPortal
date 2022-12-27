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
            System.out.println("""
                    1.O_positive
                    2.O_negative
                    3.A_positive
                    4.A_negative
                    5.B_positive
                    6.B_negative
                    7.AB_negative""");
            int bloodGroupPreference = Verification.inputVerification(7);
            BloodGroup bloodGroup = BloodGroup.values()[bloodGroupPreference - 1];
            if (Verification.getUserAsStudent(mailId)) {
                String rollNumber = "s" + rollNum++;
                System.out.println("enter any alphabet a to l to choose ur department");
                System.out.println("""
                        1)MECHANICAL,
                        2)CIVIL,
                        3)ECE,
                        4)EEE,
                        5)AUTO_MOBILE,
                        6)CHEMICAL,
                        7)MECHATRONICS,
                        8)BIOTECHNOLOGY,
                        9)BIOMEDICAL,
                        10)FOOD_TECHNOLOGY,
                        11)COMPUTER_SCIENCE,
                        12)INFORMATION_TECHNOLOGY;""");
                int dept = Verification.inputVerification(12);
                Department department = Department.values()[dept - 1];
                int joiningYear = Verification.StudentJoiningYear();
                long transportFees = 40000;
                long miscellaneousFees = 45000;
                long totalFees = transportFees + miscellaneousFees;
                System.out.println("enter mode of joining:");
                System.out.println("""
                        1)COUNSELING
                        2)MANAGEMENT""");
                int modePreference = Verification.inputVerification(2);
                Modes modeOfJoining = Modes.values()[modePreference - 1];
                totalFees += modeOfJoining.getFees();
                System.out.println("enter your residential status");
                System.out.println("1.Day scholar" + "\n2.Hosteler");
                int resStatusPreference = Verification.inputVerification(2);
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
