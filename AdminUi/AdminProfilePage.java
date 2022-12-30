package AdminUi;

import Helper.UtilFunction;
import Helper.Verification;
import Database.PortalDatabase;
import Database.MailIdDatabase;
import ProfilePage.Profile;

import java.io.IOException;

public class AdminProfilePage {
    private final MailIdDatabase db= PortalDatabase.getInstance();
    public void getAdminProfilePage(String mailId) throws IOException {
        Profile profile=new Profile();
        AdminProfileMenuLoop:while (true){
            UtilFunction.printOptions(AdminProfileMenu.values());
            int input= Verification.inputVerification(AdminProfileMenu.values().length);
            AdminProfileMenu preference=AdminProfileMenu.values()[input-1];
            switch (preference){
                case VIEW_OWN_PROFILE -> profile.viewProfile(mailId);
                case EDIT_OWN_PROFILE -> profile.editProfile(mailId);
                case VIEW_STUDENT_PROFILE -> {
                    System.out.println("Enter student mailID:");
                    String studentMailId = Verification.mailVerification();

                    if (!Verification.isStudent(studentMailId)) {
                        System.out.println("Enter student mailId");
                        studentMailId = Verification.mailVerification();
                    }
                    if (!db.getId(studentMailId)) {
                        System.out.println("Student doesn't exist");
                    } else {
                        profile.viewProfile(studentMailId);
                    }
                }
                case EDIT_STUDENT_PROFILE -> {
                    System.out.println("Enter student mailID:");
                    String studentMailId = Verification.mailVerification();

                    if (!Verification.isStudent(studentMailId)) {
                        System.out.println("Enter student mailId");
                        studentMailId = Verification.mailVerification();
                    }
                    if (!db.getId(studentMailId)) {
                        System.out.println("Student doesn't exist");
                    } else {
                        profile.editProfile(studentMailId);
                    }
                }
                case BACK_TO_MENU_PAGE -> {
                    break AdminProfileMenuLoop;
                }
            }
            }
        }
    }

