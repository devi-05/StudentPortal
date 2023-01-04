package Testing;

import java.util.Arrays;

enum days {
    MON(1),
    TUES(2),
    WED(3);
    private final int num;

    days(int num) {
        this.num = num;
    }

    public int get() {
        return num;
    }

}

public class Tester2 {
    public static void get() {
        System.out.println("hiii");
    }


    public static void main(String[] args) {
        days[] arr = days.values();
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 1, 2)));
        //System.out.println(Arrays.toString(Arrays.copyOf(arr, arr.length - 1)));
    }
}
