package ProfilePage;

public enum ResidentialStatus {
    DAY_SCHOLAR(0),
    HOSTELER(35000);
    private final int fees;

    ResidentialStatus(int fees){
        this.fees=fees;
    }
    public int getFees(){
        return fees;
    }

}
