package Testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Tester {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("[0-9]+");//. represents single character
        Matcher m = p.matcher("10");
        System.out.println(m.matches());
    }
}
//
//    public static void main(String[] args) {
////        Tester t = new Tester();
//        String mailId = "devi@student.in";
//        System.out.println(getTester2());
//        //verification for user mail id either student or staff or admin
//
//    }
//
//    private  Tester2 getTester2() {
//       getWelcomeMessage();
//    }
//
//    public void getWelcomeMessage(String mailId) {
//        String splitName = mailId.split("@")[1];
//        System.out.println(splitName);
//        String user = splitName.split("\\.")[0];
//        if (user.equals("student")) {
//            System.out.println("welcome to the portal......u logged in as STUDENT");
//        } else if (user.equals("admin")) {
//            System.out.println("welcome to the portal......u logged in as ADMIN");
//        } else {
//            System.out.println("welcome to the portal......u logged in as STAFF");
//
//        }
//    }





