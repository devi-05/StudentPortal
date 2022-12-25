package FeePortal;

import PortalDatabase.Database;
import Verification.Verification;
import ProfilePage.Department;
import java.util.Arrays;
import java.util.List;

public class PaymentService {
    Database db=Database.getInstance();
    public void pay(String mailId){
        String user= Verification.getUser(mailId);
        if(user.equals("edu")){
            long feesTobePaid=db.getTotalFees(mailId);
            long feesPaid= db.getFeesPaid(mailId);
            System.out.println("fees to be paid : "+feesTobePaid);
            System.out.println( "fees Paid : " + feesPaid);
        System.out.println("choose mode of payment:");
        System.out.println("""
                1.UPI
                2.DEBIT CARD
                3.CREDIT CARD
                4.NET BANKING""");
            List<PaymentConvenienceFees>paymentConvenienceFees= Arrays.asList(PaymentConvenienceFees.values());
        int preference= Verification.inputVerification(4);
        double convenienceFeesPercent;
        double convenienceFees = 0;
        PaymentConvenienceFees modeOfPayment = null;
         switch (preference) {
            case 1 ->  {modeOfPayment = paymentConvenienceFees.get(0);
                convenienceFeesPercent=modeOfPayment.getPercentExtra();
                convenienceFees=((convenienceFeesPercent*db.getTotalFees(mailId))/100);}
            case 2 -> {modeOfPayment = paymentConvenienceFees.get(1);
                convenienceFeesPercent=modeOfPayment.getPercentExtra();
                convenienceFees=((convenienceFeesPercent*db.getTotalFees(mailId))/100);}
            case 3 -> {modeOfPayment = paymentConvenienceFees.get(2);
                convenienceFeesPercent=modeOfPayment.getPercentExtra();
                convenienceFees=((convenienceFeesPercent*db.getTotalFees(mailId))/100);}
            case 4 -> {modeOfPayment = paymentConvenienceFees.get(3);
                convenienceFeesPercent=modeOfPayment.getPercentExtra();
                convenienceFees=((convenienceFeesPercent*db.getTotalFees(mailId))/100);}
            default -> {
                System.out.println("enter valid options");
            }
        }
            System.out.println("Payment bill:");
            System.out.println("Mode of joining fees : "+db.getModeOfJoiningFees(mailId)+"\nResidential fees : "+db.getResidentialStatusFees(mailId)+"\nTransport fees : "+db.getTransportFees(mailId)+"\n Miscellaneous fees : "+db.getMiscellaneousFees(mailId)+"\nconvenience fees : "+convenienceFees);
            System.out.println("--------------------------------------------------------");
            System.out.println("Total fees : "+(db.getModeOfJoiningFees(mailId)+db.getResidentialStatusFees(mailId)+db.getMiscellaneousFees(mailId)+db.getTransportFees(mailId)+convenienceFees));
         System.out.println("enter amount to pay(HINT: AMOUNT SHOULD BE MINIMUM 100RS )");
        long amount= Verification.amountVerification();
        feesTobePaid= (long) (db.getModeOfJoiningFees(mailId)+db.getResidentialStatusFees(mailId)+db.getMiscellaneousFees(mailId)+db.getTransportFees(mailId)+convenienceFees);
        feesTobePaid-=amount;
        feesPaid+=amount;
        db.updateFees(mailId,feesTobePaid,feesPaid);
        System.out.println("payment successful!!");
        System.out.println("do u need receipt for this payment(y/n)");
        String input= Verification.yesOrNoVerification();
        if(!input.equals("y")){
            System.out.println("you don't have access to this page ");}
        getReceipt(mailId,db.getName(mailId),db.getRollNum(mailId),db.getDepartment(mailId),db.getFeesPaid(mailId),db.getTotalFees(mailId),amount,modeOfPayment);

    }}
    public void viewBalance(){
        System.out.println("enter mailId:");
        String mailId= Verification.mailVerification();
        if(!Verification.getUser(mailId).equals("edu")){
            System.out.println("enter student mail id:");
            mailId=Verification.mailVerification();
        }
        while (!db.getId(mailId)){
            System.out.println("mailId doesn't exist");
            System.out.println("do u want to continue(y/n)");
            if(Verification.yesOrNoVerification().equals("n")){
                break;}
                System.out.println("enter mailId");
            mailId=Verification.mailVerification();}
            if (db.getId(mailId)){
            System.out.println("Name : "+db.getName(mailId)+"\nMail id:"+mailId+"\nRoll number:"+db.getRollNum(mailId)+"\ndepartment:"+db.getDepartment(mailId)+"\nFee balance:"+db.getTotalFees(mailId));}


    }

    public void getReceipt(String mailId, String name, String rollNum, Department department, long feesPaid, long totalFees, long amount, PaymentConvenienceFees modeOfPayment){
        System.out.println("Payer Details:");
        System.out.println("------------------------------------------");
        System.out.println("MAIL ID:"+mailId+"\nNAME:"+name+"\nROLL NUMBER:"+rollNum+"\nDEPARTMENT:"+department);
        System.out.println("Payment status and Details");

        System.out.println("------------------------------------------");
        System.out.println("\nAMOUNT PAID:"+amount+"\nMODE OF PAYMENT:"+modeOfPayment+"\nFEES BALANCE:"+totalFees+"\nFEES PAID TILL DATE:"+feesPaid);

    }

}