package Testing;

class Tester {
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
    }
//
//    public static void main(String[] args) {
////        Tester t = new Tester();
//        String mailId = "devi@student.in";
//        System.out.println(getTester2());
//        //verification for user mail id either student or staff or admin
//
//    }
//
//    private  Tester2 getTester2() {
//       getWelcomeMessage();
//    }
//
//    public void getWelcomeMessage(String mailId) {
//        String splitName = mailId.split("@")[1];
//        System.out.println(splitName);
//        String user = splitName.split("\\.")[0];
//        if (user.equals("student")) {
//            System.out.println("welcome to the portal......u logged in as STUDENT");
//        } else if (user.equals("admin")) {
//            System.out.println("welcome to the portal......u logged in as ADMIN");
//        } else {
//            System.out.println("welcome to the portal......u logged in as STAFF");
//
//        }
//    }





