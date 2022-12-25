package ResultPortal;

public enum GradeValues {
    O(10),
    A(9),
    B(8),
    C(7),
    D(6);
    private final int point;

    GradeValues(int point){
        this.point=point;
    }
    int getPoint(){
        return point;
    }
}
