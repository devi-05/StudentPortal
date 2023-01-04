package Main;

import Helper.UtilFunction;
import Helper.Verification;
import StudentPortal.Portal;
import StudentPortal.StudentPortal;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Portal s = new StudentPortal();
        System.out.println("WELCOME TO STUDENT PORTAL!!");
        mainLoop:
        while (true) {
            PortalOptions[] portalOptionsArray = PortalOptions.values();
            UtilFunction.printOptions(portalOptionsArray);

            System.out.println("Enter ur preference :");
            int input = Verification.inputVerification(portalOptionsArray.length);
            PortalOptions preference = portalOptionsArray[input - 1];

            switch (preference) {
                case SIGN_UP -> s.signUp();
                case SIGN_IN -> s.signIn();
                case EXIT -> {
                    break mainLoop;
                }
            }


        }
    }
}
