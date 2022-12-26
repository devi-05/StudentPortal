package Menu;

import PortalDatabase.Database;
import Verification.Verification;
import ProfilePage.Profile;

import java.io.IOException;

public class ProfileMenu {
    Database db=Database.getInstance();
    public void profileOption(String mailId,String user) throws IOException {
        Profile profile=new Profile();

        if (user.equals("edu")) {
            boolean a= true;
            while (a) {
                System.out.println("1.view profile" + "\n2.edit profile" + "\n3.back");
                int input = Verification.inputVerification(3);
                switch (input) {
                    case 1 -> profile.viewProfile(mailId, user);
                    case 2 -> profile.editProfile(mailId, user);
                    case 3 -> a=false;
                }
            }
        } else {
            boolean p = true;
            while (p) {
                System.out.println("""
                        1.view profile
                        2.edit profile
                        3.view student profile
                        4.Back""");
                int input = Verification.inputVerification(4);
                switch (input) {
                    case 1 -> profile.viewProfile(mailId, user);
                    case 2 -> profile.editProfile(mailId, user);
                    case 3 -> {
                        System.out.println("enter student mailID:");
                        String studentMailId = Verification.mailVerification();
                        String alternateUser = Verification.getUser(studentMailId);
                        if (!alternateUser.equals("edu")) {
                            System.out.println("enter student mailId");
                            studentMailId = Verification.mailVerification();
                            alternateUser = Verification.getUser(studentMailId);
                        }
                        if (!db.getId(studentMailId)) {
                            System.out.println("student doesn't exist");
                        } else {
                            profile.viewProfile(studentMailId, alternateUser);
                        }
                    }
                    case 4 -> {
                        p = false;
                    }
                }
            }
        }
    }
}
