package FeePortal;

public enum PaymentConvenienceFees {
    UPI(0),
    DEBIT_CARD(1.5),
    CREDIT_CARD(3),
    NET_BANKING(3.5);
    private final double percentExtra;

    PaymentConvenienceFees(double percentExtra) {
        this.percentExtra = percentExtra;
    }

     double getPercentExtra() {
        return percentExtra;
    }

}
