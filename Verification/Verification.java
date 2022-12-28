package Verification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Verification {
    private static final Scanner s = new Scanner(System.in);

    public static String mailVerification() {
        String mailId = s.next();
        if (mailId.matches("[a-z0-9.]+@(student|admin).in")) {
            return mailId;
        } else {
            System.out.println("enter mail id which contains alphanumeric characters and special character(.) is included and ends with student or admin .in");
            return mailVerification();
        }
    }

    public static String passwordVerification() {
        String password = s.next();
        if (password.matches("[A-Za-z1-9@]{2,15}")) {
            return password;
        } else {
            System.out.println("enter password using alphanumeric characters and special character (@) is allowed and it should be between 2 to 15 characters");
            return passwordVerification();
        }
    }

    public static boolean isStudent(String mailId) {
        String splitter = mailId.split("@")[1];
        return splitter.split("\\.")[0].equals("student");
    }

    public static String nameVerification() {
        String name = s.next();
        if (name.matches("[A-Za-z]+[-.][A-Za-z]{1,15}")) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            System.out.println("name should be in above format and should contain 5 to 15 alphabets !!!!");
            return nameVerification();
        }
    }

    public static String phoneNumVerification() {
        String input = s.next();
        if (input.matches("[0-9]{10}")) {
            return input;
        } else {
            System.out.println("enter proper phone number with 10 digits");
            return phoneNumVerification();
        }

    }

    public static String addressVerification() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        if (input.matches("[A-Za-z\s,-.0-9]{5,50}")) {
            return input;
        } else {
            System.out.println("enter proper address with at least 5 to 50 alphanumeric characters");
            return addressVerification();
        }
    }


    public static int StudentJoiningYear() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String formattedYear = date.format(formatter);
        return Integer.parseInt(formattedYear);
    }

    public static long amountVerification() {
        long input = s.nextLong();
        if (String.valueOf(input).matches("[0-9]{3,6}")) {
            return input;
        } else {
            System.out.println("enter  minimum 100");
            return amountVerification();
        }
    }

    public static String yesOrNoVerification() {
        String input = s.next();
        if (input.matches("[y|n]")) {
            return input;
        } else {
            System.out.println("enter input y or n");
            return yesOrNoVerification();
        }
    }

    public static String semesterSubjectVerification() {
        String input = s.next();
        if (input.matches("[a-zA-Z\s?]+")) {
            return input;
        } else {
            System.out.println("enter alphanumeric characters)");
            return semesterSubjectVerification();
        }
    }

    public static int inputVerification(int noOfOptions) {
        String input = s.next();
        if (input.matches("[0-9]+?") && (Integer.parseInt(input) <= noOfOptions)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("enter options between 1 and " + noOfOptions);
            return inputVerification(noOfOptions);
        }

    }

    public static void printOptions(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public static void getWelcomeMessage(String mailId) {
        String splitName = mailId.split("@")[1];
        String user = splitName.split("\\.")[0];
        if (user.equals("student")) {
            System.out.println("welcome to the portal......u logged in as STUDENT");
        } else {
            System.out.println("welcome to the portal......u logged in as ADMIN");
        }
    }


}
