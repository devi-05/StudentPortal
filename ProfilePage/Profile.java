package ProfilePage;

import Helper.UtilFunction;
import Helper.Verification;
import Menu.MainMenu;
import PortalDatabase.Database;

import java.io.IOException;

public class Profile {
    private final Database db = Database.getInstance();

    public void createProfile(String mailId) throws IOException {
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        MainMenu menuObj = new MainMenu();
        userProfileDetails.getUserDetails(mailId);
        System.out.println("Created ur profile successfully");
        System.out.println("Do you want to continue and move to menu page(y/n)");
        if (Verification.yesOrNoVerification().equals("y")) {
            menuObj.menu(mailId);
        }
    }

    public void viewProfile(String mailId) {
        System.out.println(db.getUserData(mailId));
    }

    public void editProfile(String mailId) throws IOException {
        int input;
        Enum preference = null;
        if(Verification.isStudent(mailId)){
            StudentEditProfileOptions[] studentEditProfileOptions=StudentEditProfileOptions.values();
            System.out.println("Choose category which u want to edit:");
            UtilFunction.printOptions(studentEditProfileOptions);
            input = Verification.inputVerification(studentEditProfileOptions.length);
            preference = studentEditProfileOptions[input - 1];
        }
        else {
            mailId=Verification.mailVerification();
            while (!Verification.isStudent(mailId)){
                System.out.println("Enter student mailId");
                mailId=Verification.mailVerification();
            }
            if(!db.getId(mailId)){
                System.out.println("Student doesn't exist");
            }
            else {
            AdminEditProfileOptions[] adminEditProfileOptions=AdminEditProfileOptions.values();
            System.out.println("Choose category which u want to edit:");
            UtilFunction.printOptions(adminEditProfileOptions);
            input=Verification.inputVerification(adminEditProfileOptions.length);
            preference=adminEditProfileOptions[input-1];}
        }
            switch (String.valueOf(preference)) {
                case "ADDRESS":
                    String attribute = "address";
                    System.out.println("Enter ur new address");
                    String newAddress = Verification.addressVerification();
                    db.editData(mailId, attribute, newAddress);
                    System.out.println("Address updated successfully");
                    break;
                case "PHONE_NUMBER":
                    attribute = "phoneNumber";
                    System.out.println("Enter ur new phone number");
                    String newPhoneNumber = Verification.phoneNumVerification();
                    db.editData(mailId, attribute, newPhoneNumber);
                    System.out.println("Phone Number updated successfully");
                    break;
                case "RESIDENTIAL_STATUS":
                    System.out.println("Enter your residential status");
                    ResidentialStatus[] residentialStatuses = ResidentialStatus.values();
                    UtilFunction.printOptions(residentialStatuses);
                    int resStatusPreference = Verification.inputVerification(residentialStatuses.length);
                    ResidentialStatus residentialStatus = residentialStatuses[resStatusPreference - 1];
                    db.setResidentialStatus(mailId,residentialStatus);
                    System.out.println("Residential status updated successfully");
                    break;
                case "BACK_TO_PROFILE_PAGE":
                    break ;

            }
        }
    }

