package com.library.beans;


import java.io.File;

@SuppressWarnings({"unused", "SameParameterValue"})
public class Member {

    private int memberId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String occupation;
    private String addressLine1;
    private String addressLine2;
    private String area;
    private String zipCode;
    private String mobileNo;
    private File idProof;


    public Member() {
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public File getIdProof() {
        return idProof;
    }

    public void setIdProof(File idProof) {
        this.idProof = idProof;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", occupation='" + occupation + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", area='" + area + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
