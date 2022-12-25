package ProfilePage;

import AccountService.Account;
import PortalDatabase.Database;
import PortalUsers.*;
import Verification.Verification;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Profile {
    Database db=Database.getInstance();
    Account ac=new Account();
    public void createProfile(String mailId) throws IOException {
        if(mailId!=null) {
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
            int bg = Verification.inputVerification(7);
            List<BloodGroup> bgList = Arrays.asList(BloodGroup.values());
            BloodGroup bloodGroup = bgList.get(bg-1);
            String user = Verification.getUser(mailId);
            if (user.equals("edu")) {
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
                List<Department> deptList = Arrays.asList(Department.values());
                Department department = deptList.get(dept-1);
                System.out.println("enter your joining year eg:2019");
                int joiningYear = Verification.yearVerification();
                long transportFees = 40000;
                long miscellaneousFees = 45000;
                long totalFees = transportFees + miscellaneousFees;
                System.out.println("enter mode of joining:");
                System.out.println("""
                        1)COUNSELING
                        2)MANAGEMENT""");
                int mode = Verification.inputVerification(2);
                List<Modes> modesList = Arrays.asList(Modes.values());
                Modes modeOfJoining = modesList.get(mode-1);
                totalFees += modeOfJoining.getFees();
                System.out.println("enter your residential status");
                System.out.println("1.Day scholar" + "\n2.Hosteler");
                int resStatus = Verification.inputVerification(2);
                List<ResidentialStatus> resStatusList = Arrays.asList(ResidentialStatus.values());
                ResidentialStatus residentialStatus = resStatusList.get(resStatus-1);
                totalFees += residentialStatus.getFees();
                long feesPaid = 0;
                Student newStudent = new Student(mailId, name, bloodGroup, address, phoneNumber, rollNumber, department, joiningYear, modeOfJoining, residentialStatus,modeOfJoining.getFees(),residentialStatus.getFees(), transportFees,miscellaneousFees,totalFees, feesPaid);
                db.addNewStudent(mailId, newStudent);
            } else if (user.equals("admin")) {
                String employeeId = "A" + rollNum++;
                System.out.println("enter date of joining[format:dd-month-yyyy,eg:09-jun-2022]");
                String dateOfJoining = Verification.empDojVerification();
                Admin newAdmin = new Admin(mailId, name, bloodGroup, address, phoneNumber, employeeId, dateOfJoining);
                db.addNewAdmin(mailId, newAdmin);
            }
            System.out.println("created ur profile successfully");
        }

    }
    public void viewProfile(String mailId,String user){

        System.out.println(db.getUserData(user,mailId));
    }
    public void editProfile(String mailId,String user) throws IOException {
        System.out.println("choose category which u want to edit:");
        System.out.println("1.address"+"\n2.phone Number");
        int preference=Verification.inputVerification(2);
        switch (preference) {
            case 1:
                String attribute="address";
                System.out.println("enter ur new address");
                String newAddress=Verification.addressVerification();
                db.editData(user,mailId, attribute,newAddress);
                break;
            case 2:
                 attribute="phoneNumber";
                System.out.println("enter ur new phone number");
                String newPhoneNumber=Verification.phoneNumVerification();
                db.editData(user,mailId, attribute,newPhoneNumber);
                break;


        }
    }

}
