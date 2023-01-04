package Helper;

public class UtilFunction {
    public static void printOptions(Enum[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + "." + array[i]);
        }
    }

    public static void getWelcomeMessage(String mailId) {
        // String splitName = mailId.split("@")[1];
        // String user = splitName.split("\\.")[0];
        //System.out.println("welcome to portal......u logged in as "+user);
        if (Verification.isStudent(mailId)) {
            System.out.println("welcome to portal......u logged in as STUDENT");
        } else {
            System.out.println("welcome to portal......u logged in as ADMIN");
        }
    }
}
