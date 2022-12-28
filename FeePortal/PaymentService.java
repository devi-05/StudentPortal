package FeePortal;

import Helper.UtilFunction;
import Helper.Verification;
import PortalDatabase.Database;
import ProfilePage.Department;

public class PaymentService {
    Database db = Database.getInstance();

    public void pay(String mailId) {
        boolean user = Verification.isStudent(mailId);
        double convenienceFees = 0;
        long feesTobePaid;
        long feesPaid = 0;
        PaymentConvenienceFees modeOfPayment = null ;
        if (user) {
            feesTobePaid = db.getTotalFees(mailId);
            feesPaid = db.getFeesPaid(mailId);
            System.out.println("fees to be paid : " + feesTobePaid);
            System.out.println("fees Paid : " + feesPaid);
            System.out.println("choose mode of payment:");
            UtilFunction.printOptions(PaymentConvenienceFees.values());
            int preference = Verification.inputVerification(PaymentConvenienceFees.values().length);
            modeOfPayment = PaymentConvenienceFees.values()[preference - 1];
            double convenienceFeesPercent = modeOfPayment.getPercentExtra();
            convenienceFees = ((convenienceFeesPercent * db.getTotalFees(mailId)) / 100);
        }
        System.out.println("Payment bill:");
        System.out.println("Mode of joining fees : " + db.getModeOfJoiningFees(mailId) + "\nResidential fees : " + db.getResidentialStatusFees(mailId) + "\nTransport fees : " + db.getTransportFees(mailId) + "\n Miscellaneous fees : " + db.getMiscellaneousFees(mailId) + "\nconvenience fees : " + convenienceFees);
        System.out.println("--------------------------------------------------------");
        System.out.println("Total fees : " + (db.getModeOfJoiningFees(mailId) + db.getResidentialStatusFees(mailId) + db.getMiscellaneousFees(mailId) + db.getTransportFees(mailId) + convenienceFees));
        System.out.println(" enter amount to pay(HINT: AMOUNT SHOULD BE MINIMUM 100RS) ");
        long amount = Verification.amountVerification();
        feesTobePaid = (long) (db.getModeOfJoiningFees(mailId) + db.getResidentialStatusFees(mailId) + db.getMiscellaneousFees(mailId) + db.getTransportFees(mailId) + convenienceFees);
        feesTobePaid -= amount;
        feesPaid += amount;
        db.updateFees(mailId, feesTobePaid, feesPaid);
        System.out.println("payment successful!!");
        System.out.println("do u need receipt for this payment(y/n)");
        String input = Verification.yesOrNoVerification();
        if (input.equals("y")) {
            getReceipt(mailId, db.getName(mailId), db.getRollNum(mailId), db.getDepartment(mailId), db.getFeesPaid(mailId), db.getTotalFees(mailId), amount, modeOfPayment);
        }
    }

    public void viewBalance(String mailId) {
        if (!Verification.isStudent(mailId)) {
            System.out.println("enter student mail id:");
            mailId = Verification.mailVerification();
        }
        while (!db.getId(mailId)) {
            System.out.println("mailId doesn't exist");
            System.out.println("do u want to continue in view balance page(y/n)");
            if (Verification.yesOrNoVerification().equals("n")) {
                break;
            }
            System.out.println("enter mailId");
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