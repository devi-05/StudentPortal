package Testing;

class Tester {
    public static void main(String[] args) {
        Tester t = new Tester();
        String mailId = "devi@student.in";
        t.getWelcomeMessage(mailId);
        //verification for user mail id either student or staff or admin

    }

    public void getWelcomeMessage(String mailId) {
        String splitName = mailId.split("@")[1];
        System.out.println(splitName);
        String user = splitName.split("\\.")[0];
        if (user.equals("student")) {
            System.out.println("welcome to the portal......u logged in as STUDENT");
        } else if (user.equals("admin")) {
            System.out.println("welcome to the portal......u logged in as ADMIN");
        } else {
            System.out.println("welcome to the portal......u logged in as STAFF");

        }
    }

}



