package Menu;

import PortalDatabase.Database;
import ProfilePage.Profile;
import Verification.Verification;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProfileMenu {
    Database db = Database.getInstance();

    public void profileOption(String mailId) throws IOException {
        Profile profile = new Profile();
        int input;
        List<String>studentProfileMenuList= Arrays.asList("view profile","edit profile","back to menu page");
        List<String>adminProfileMenuList= Arrays.asList("view profile","edit profile","back to menu page","view student profile");
        if(Verification.isStudent(mailId)){
            Verification.printOptions(studentProfileMenuList);
            input = Verification.inputVerification(3);
        }
        else {
            Verification.printOptions(adminProfileMenuList);
            input=Verification.inputVerification(4);
        }
            ProfilePageMenuLoop:
            while (true) {
                System.out.println("""
                        1.view profile
                        2.edit profile
                        3.back to menu page""");
                switch (input) {
                    case 1 -> profile.viewProfile(mailId);
                    case 2 -> profile.editProfile(mailId);
                    case 3 -> {
                        break ProfilePageMenuLoop;
                    }
                    case 4 -> {
                        System.out.println("enter student mailID:");
                        String studentMailId = Verification.mailVerification();

                        if (!Verification.isStudent(studentMailId)) {
                            System.out.println("enter student mailId");
                            studentMailId = Verification.mailVerification();
                        }
                        if (!db.getId(studentMailId)) {
                            System.out.println("student doesn't exist");
                        } else {
                            profile.viewProfile(studentMailId);
                        }
                    }

                }
            }

            }
        }

