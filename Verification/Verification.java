package Verification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
    private static final Scanner s = new Scanner(System.in);

    public static String mailVerification(){
        String mailId=s.next();
        if(mailId.matches("[a-z0-9.]+@(student|admin).in")){
            return mailId;
        }
        else {
            System.out.println("enter mail id which contains alphanumeric characters and special character(.) is included and ends with student or admin .in");
            return mailVerification();
        }
    }
    public static String passwordVerification(){
        String password=s.next();
        if(password.matches("[A-Za-z1-9@]{2,6}")){
            return password;
        }
        else {
            System.out.println("enter password using alphanumeric characters and special character (@) is allowed and it should be between 2 to 6 characters");
            return passwordVerification();
        }
    }
    public static boolean getUserAsStudent(String mailId){
        String splitter=mailId.split("@")[1];
        return splitter.split("\\.")[0].equals("student");
    }

    public static String nameVerification(){
        String name=s.next();
        if(name.matches("[A-Z][a-z]+[-.][A-Za-z]{1,10}")){
            return name;
        }
        else {
            System.out.println("name should contain 5 to 15 alphabets and starting letter should be in caps!!!!");
            return nameVerification();
        }
    }
    public  static String phoneNumVerification() {
        String input = s.next();
        if (input.matches("[0-9]{10}")) {
            return input;
        } else {
            System.out.println("enter proper phone number starts with 9 and should contain 10 digits");
            return phoneNumVerification();
        }

    }
    public static String addressVerification() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input=bufferedReader.readLine();
        if (input.matches("[A-Za-z\s,-.0-9]{5,25}")) {
            return input;
        } else {
            System.out.println("enter proper address with at least 5 to 25 alphanumeric characters");
            return addressVerification();
        }
    }


    public static int yearVerification() {
        String input = s.next();
        if (input.matches("[1-9][0-9]{3}")) {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            String formattedYear = date.format(formatter);
            if (input.equals(formattedYear)) {
                return Integer.parseInt(input);
            } else if (Integer.parseInt(input) > Integer.parseInt(formattedYear)) {
                System.out.println("entered year is beyond current year");
                return yearVerification();
            } else  {
                System.out.println("entered year is less than current year");
                return yearVerification();
            }
        } else {
            System.out.println("enter 4 digit num");
            return yearVerification();
        }

    }

    public static String empDojVerification() {
        String input = s.next();
        Pattern pat=Pattern.compile("[1-31]-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)-[1-2][0-9]{3}");
        Matcher mat=pat.matcher(input);
        if(input.matches("([0-9]{1,2}-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)-[1-2][0-9]{3})")){
            String[] a=input.split("-");
            List<String> str=new ArrayList<>();
            str.addAll(Arrays.asList(a));
            int number=0;
            String string =(str.get(1).toUpperCase());
            switch (string) {
                case "JAN" -> number = Months.JAN.getDays();
                case "FEB" -> number = Months.FEB.getDays();
                case "MAR" -> number = Months.MAR.getDays();
                case "APR" -> number = Months.APR.getDays();
                case "MAY" -> number = Months.MAY.getDays();
                case "JUN" -> number = Months.JUN.getDays();
                case "JUL" -> number = Months.JUL.getDays();
                case "AUG" -> number = Months.AUG.getDays();
                case "SEP" -> number = Months.SEP.getDays();
                case "OCT" -> number = Months.OCT.getDays();
                case "NOV" -> number = Months.NOV.getDays();
                case "DEC" -> number = Months.DEC.getDays();
            }
            LocalDate date=LocalDate.now();
            DateTimeFormatter formatterYear=DateTimeFormatter.ofPattern("yyyy");
            String formattedYear=date.format(formatterYear);
            if(number<Integer.parseInt(str.get(0))){
                System.out.println("enter correct date");
                return empDojVerification();
            }
            if (Integer.parseInt(str.get(2))>Integer.parseInt(formattedYear)){
                System.out.println("entered year is beyond current date");
                return empDojVerification();
            }
        }
        else {
            System.out.println("enter in correct format");
            return empDojVerification();
        }
        return input;
    }
    public static long amountVerification(){
        long input = s.nextLong();
        if (String.valueOf(input).matches("[0-9]{3,6}")){
            return input;
        } else {
            System.out.println("enter  minimum 100");
            return amountVerification();
        }
    }
    public static String yesOrNoVerification(){
        String input=s.next();
        if(input.matches("[y|n]")){
            return input;
        }
        else {
            System.out.println("enter input y or n)");
            return yesOrNoVerification();
        }
    }

    public static String semesterSubjectVerification(){
        String input=s.next();
        if(input.matches("[a-zA-Z\s?]+")){
            return input;
        }
        else {
            System.out.println("enter alphanumeric characters)");
            return semesterSubjectVerification();
        }
    }

    public static int inputVerification(int noOfOptions){
        String input = s.next();
        if (input.matches("[0-9]+?") && (Integer.parseInt(input) <= noOfOptions)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("enter options between 1 and "+noOfOptions);
            return inputVerification(noOfOptions);
        }

    }




    }
