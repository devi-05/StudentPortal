import StudentPortal.Portal;
import StudentPortal.StudentPortal;
import Verification.Verification;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Portal s = new StudentPortal();
        System.out.println("Welcome to student portal!!!!!");
        mainLoop:
        while (true) {
            System.out.println("""
                    1.SIGN UP
                    2.SIGN IN
                    3.EXIT""");
            System.out.println("enter ur preference :");
            int input = Verification.inputVerification(3);
            switch (input) {
                case 1 -> s.signUp();
                case 2 -> s.signIn();
                case 3 -> {
                    break mainLoop;
                }
            }


        }
    }
}
