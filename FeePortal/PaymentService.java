package FeePortal;

import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;
import ProfilePage.Department;

public class PaymentService {
    private final Database db = Database.getInstance();

    public void pay(String mailId) {
        boolean student = Verification.isStudent(mailId);
        double convenienceFees = 0;
        long totalFees;
        long feesPaid;
        long feesToBePaid;
        long feeBalance = db.getFeesBalance(mailId);
        PaymentConvenienceFees modeOfPayment;
        if (!student) {
            System.out.println("Enter Student mail Id");
        } else {
            if (feeBalance != 0) {
                totalFees = db.getTotalFees(mailId);
                feesPaid = db.getFeesPaid(mailId);
                System.out.println("Mode of joining fees : " + db.getModeOfJoiningFees(mailId) + "\nResidential fees : " + db.getResidentialStatusFees(mailId) + "\nTransport fees : " + db.getTransportFees(mailId) + "\nMiscellaneous fees : " + db.getMiscellaneousFees(mailId));
                System.out.println("--------------------------------------------------------");
                System.out.println("Total fees : " + totalFees);
                System.out.println("Fee Balance :" + feeBalance);
                System.out.println("Fee paid :" + feesPaid);
                System.out.println("Choose mode of payment:");
                PaymentConvenienceFees[] paymentConvenienceFeesArray = PaymentConvenienceFees.values();
                UtilFunction.printOptions(paymentConvenienceFeesArray);
                int preference = Verification.inputVerification(paymentConvenienceFeesArray.length);
                modeOfPayment = paymentConvenienceFeesArray[preference - 1];
                double convenienceFeesPercent = modeOfPayment.getPercentExtra();
                convenienceFees = (int) (convenienceFeesPercent * feeBalance / 100);
                System.out.println("convenience fees : " + convenienceFees + "\nFee Balance :" + feeBalance);
                System.out.println("--------------------------------------------------------");
                feesToBePaid = (long) (convenienceFees + feeBalance);
                System.out.println(" fees to be paid : " + feesToBePaid);

                System.out.println("Enter amount to pay(HINT: AMOUNT SHOULD BE MINIMUM 100RS)");
                long amount = Verification.amountVerification(feesToBePaid);
                //feesTobePaid = (long) (db.getModeOfJoiningFees(mailId) + db.getResidentialStatusFees(mailId) + db.getMiscellaneousFees(mailId) + db.getTransportFees(mailId) + convenienceFees);
                feeBalance = (feesToBePaid - amount);
                feesPaid += amount;
                db.updateFees(mailId, feesPaid, feeBalance);
                System.out.println("Payment successful!!");
                System.out.println("Need receipt for this payment(y/n)");
                String input = Verification.yesOrNoVerification();
                if (input.equals("y")) {
                    getReceipt(mailId, db.getName(mailId), db.getRollNum(mailId), db.getDepartment(mailId), db.getFeesPaid(mailId), db.getTotalFees(mailId), amount, modeOfPayment);
                }
            } else {
                System.out.println("NO BALANCE...FULL FEES PAID");
            }
        }
    }

    public void viewBalance(String mailId) {
        if (!Verification.isStudent(mailId)) {
            System.out.println("Enter student mail id:");
            mailId = Verification.mailVerification();
        }
        while (!db.getId(mailId)) {
            System.out.println("MailId doesn't exist");
            System.out.println("Do u want to continue in view balance page(y/n)");
            if (Verification.yesOrNoVerification().equals("n")) {
                break;
            }
            System.out.println("Enter mailId");
            mailId = Verification.mailVerification();
        }
        if (db.getId(mailId)) {
            System.out.println("Name : " + db.getName(mailId) + "\nMail id:" + mailId + "\nRoll number:" + db.getRollNum(mailId) + "\ndepartment:" + db.getDepartment(mailId) + "\nFee balance:" + db.getTotalFees(mailId));
        }


    }

    public void getReceipt(String mailId, String name, String rollNum, Department department, long feesPaid, long totalFees, long amount, PaymentConvenienceFees modeOfPayment) {
        System.out.println("Payer Details:");
        System.out.println("------------------------------------------");
        System.out.println("MAIL ID:" + mailId + "\nNAME:" + name + "\nROLL NUMBER:" + rollNum + "\nDEPARTMENT:" + department);
        System.out.println("Payment status and Details");

        System.out.println("------------------------------------------");
        System.out.println("\nAMOUNT PAID:" + amount + "\nMODE OF PAYMENT:" + modeOfPayment + "\nFEES BALANCE:" + totalFees + "\nFEES PAID TILL DATE:" + feesPaid);

    }

}