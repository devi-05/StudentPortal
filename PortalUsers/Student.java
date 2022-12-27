package PortalUsers;

import ProfilePage.BloodGroup;
import ProfilePage.Department;
import ProfilePage.Modes;
import ProfilePage.ResidentialStatus;

public class Student extends User {

    private final String rollNumber;
    private final Department department;
    private final int joiningYear;
    private final Modes modeOfJoining;
    private final long modeOfJoiningFees;
    private final long transportFees;
    private final long miscellaneousFees;
    private ResidentialStatus residentialStatus;
    private long residentialStatusFees;
    private long totalFees;
    private long feesPaid;

    public Student(String mailId, String name, BloodGroup bloodGroup, String address, String phoneNumber, String rollNumber, Department department, int year, Modes mode, ResidentialStatus residentialStatus, long modeOfJoiningFees, long residentialStatusFees, long transportFees, long miscellaneousFees, long totalFees, long feesPaid) {
        super(mailId, name, bloodGroup, address, phoneNumber);
        this.rollNumber = rollNumber;
        this.department = department;
        this.joiningYear = year;
        this.modeOfJoining = mode;
        this.residentialStatus = residentialStatus;
        this.modeOfJoiningFees = modeOfJoiningFees;
        this.residentialStatusFees = residentialStatusFees;
        this.transportFees = transportFees;
        this.miscellaneousFees = miscellaneousFees;
        this.totalFees = totalFees;
        this.feesPaid = feesPaid;
    }

    @Override
    public String toString() {

        return "Your profile:" +
                "\nname=" + getName() +
                "\nmailId=" + getMailId() +
                "\nrollNumber=" + rollNumber +
                "\ndepartment=" + department +
                "\nJoining year=" + joiningYear +
                "\nmode of joining=" + modeOfJoining +
                "\nResidential Status=" + residentialStatus +
                "\nTotal fees=" + totalFees +
                "\nfees paid=" + feesPaid +
                "\nbloodGroup=" + getBloodGroup() +
                "\naddress=" + getAddress() +
                "\nphoneNumber=" + getPhoneNumber();

    }


    public String getRollNumber() {
        return rollNumber;
    }

    public ResidentialStatus getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(ResidentialStatus residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public long getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(long totalFees) {
        this.totalFees = totalFees;
    }

    public long getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(long feesPaid) {
        this.feesPaid = feesPaid;
    }

    public long getModeOfJoiningFees() {
        return modeOfJoiningFees;
    }

    public long getResidentialStatusFees() {
        return residentialStatusFees;
    }

    public long getTransportFees() {
        return transportFees;
    }

    public long getMiscellaneousFees() {
        return miscellaneousFees;
    }

    public Department getDepartment() {
        return department;
    }

    public int getJoiningYear() {
        return joiningYear;
    }

    public Modes getModeOfJoining() {
        return modeOfJoining;
    }
}