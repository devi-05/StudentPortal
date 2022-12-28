package Main;

import Helper.UtilFunction;
import Helper.Verification;
import StudentPortal.Portal;
import StudentPortal.StudentPortal;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Portal s = new StudentPortal();
        System.out.println("Welcome to student portal!!!!!");
        mainLoop:
        while (true) {
            UtilFunction.printOptions(PortalOptions.values());
            System.out.println("enter ur preference :");
            int input = Verification.inputVerification(PortalOptions.values().length);
            PortalOptions preference=PortalOptions.values()[input-1];
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
