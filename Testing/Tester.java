package Testing;

class Tester {
    public static void main(String[] args) {


        String input = "09-jun-2012";
        if (input.matches("([0-9]{1,2}-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)-[1-2][0-9]{3})")) {
            String[] a = input.split("-");
            for (String i :
                    a) {
                System.out.println(i);

            }
        }
    }
}


