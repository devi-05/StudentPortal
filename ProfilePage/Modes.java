package ProfilePage;

public enum Modes {

    COUNSELING(50000),
    MANAGEMENT(100000);

    private final int fees;

    Modes(int fees) {
        this.fees = fees;
    }

    public int getFees() {
        return fees;
    }


}
