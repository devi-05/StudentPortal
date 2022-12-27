package PortalUsers;

import ProfilePage.BloodGroup;

public class User {
    private final String mailId;
    private final String name;
    private final BloodGroup bloodGroup;
    private String address;
    private String phoneNumber;


    public User(String gmail, String name, BloodGroup bloodGroup, String address, String phoneNumber) {
        this.mailId = gmail;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public String getName() {
        return name;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }
}
