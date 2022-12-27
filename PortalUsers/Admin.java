package PortalUsers;

import ProfilePage.BloodGroup;

public class Admin extends User {
    private final String employeeId;
    private final String dateOfJoining;

    public Admin(String mailId, String name, BloodGroup bloodGroup, String address, String phoneNumber, String employeeId, String dateOfJoining) {
        super(mailId, name, bloodGroup, address, phoneNumber);
        this.employeeId = employeeId;
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Your profile:" +
                "\nname=" + getName() +
                "\nmailId=" + getMailId() +
                "\nemployeeId=" + employeeId +
                "\ndateOfJoining=" + dateOfJoining +
                "\nbloodGroup=" + getBloodGroup() +
                "\nphoneNumber=" + getPhoneNumber() +
                "\naddress=" + getAddress();
    }

}
