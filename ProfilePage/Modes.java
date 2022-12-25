package ProfilePage;

public enum Modes {

    counselling(50000),
    management(100000);

    private final int fees;
    Modes(int fees){
        this.fees=fees;
    }
    public int getFees(){
        return fees;
    }


}
