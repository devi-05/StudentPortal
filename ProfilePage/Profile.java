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

        UserEditProfileOptions[] userEditProfileOptions = UserEditProfileOptions.values();
        System.out.println("Choose category which u want to edit:");
        UtilFunction.printOptions(userEditProfileOptions);
        int input = Verification.inputVerification(userEditProfileOptions.length);
        UserEditProfileOptions preference = userEditProfileOptions[input - 1];

        switch (preference) {
            case ADDRESS:
                setNewAddress(mailId);
                break;
            case PHONE_NUMBER:
                setNewPhoneNumber(mailId);
                break;
            case BACK_TO_PROFILE_PAGE:
                break;

        }
    }

    public void editStudentProfile(String studentMailId) throws IOException {
        AdminEditProfileOptions[] adminEditProfileOptions = AdminEditProfileOptions.values();
        System.out.println("Choose category which u want to edit:");
        UtilFunction.printOptions(adminEditProfileOptions);
        int input = Verification.inputVerification(adminEditProfileOptions.length);
        AdminEditProfileOptions preference = adminEditProfileOptions[input - 1];
        switch (preference) {
            case ADDRESS -> setNewAddress(studentMailId);
            case PHONE_NUMBER -> setNewPhoneNumber(studentMailId);
            case RESIDENTIAL_STATUS -> {
                System.out.println("Enter your residential status");
                ResidentialStatus[] residentialStatuses = ResidentialStatus.values();
                UtilFunction.printOptions(residentialStatuses);
                int resStatusPreference = Verification.inputVerification(residentialStatuses.length);
                ResidentialStatus residentialStatus = residentialStatuses[resStatusPreference - 1];
                db.setResidentialStatus(studentMailId, residentialStatus);
                System.out.println("Residential status updated successfully");
            }

        }
    }

    public void setNewAddress(String mailId) throws IOException {
        String attribute = "address";
        System.out.println("Enter ur new address");
        String newAddress = Verification.addressVerification();
        db.editData(mailId, attribute, newAddress);
        System.out.println("Address updated successfully");
    }

    public void setNewPhoneNumber(String mailId) {
        String attribute = "phoneNumber";
        System.out.println("Enter ur new phone number");
        String newPhoneNumber = Verification.phoneNumVerification();
        db.editData(mailId, attribute, newPhoneNumber);
        System.out.println("Phone Number updated successfully");

    }
}