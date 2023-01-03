package ProfilePage;

import Helper.UtilFunction;
import Helper.Verification;
import Menu.MainMenu;
import PortalDatabase.Database;

import java.io.IOException;

public class Profile {
    private final Database db = Database.getInstance();
    private final MainMenu menuObj = new MainMenu();

    public void createProfile(String mailId) throws IOException {
       UserProfileDetails userProfileDetails=new UserProfileDetails();
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
