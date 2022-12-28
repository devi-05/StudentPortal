package Testing;

enum days {
    MON(1),
    TUES(2);
    private final int num;

    days(int num) {
        this.num = num;
    }

    public int get() {
        return num;
    }

}

public class Tester2 {

    public boolean sample() {
        return true;
    }


    public static void main(String[] args) {
        days[] arr=days.values();
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i + 1) + "." + arr[i]);
        }
    }
}
