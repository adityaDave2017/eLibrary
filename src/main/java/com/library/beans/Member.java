package com.library.beans;


public class Member {

    private int memberId;
    private String firstName;
    private String middleName;
    private String emailId;
    private String occupation;
    private String addressLine1;
    private String addressLine2;
    private String area;
    private String zipcode;
    private String mobileNo;

    public Member() {
    }

    public Member(int memberId, String firstName, String middleName, String emailId, String occupation, String addressLine1, String addressLine2, String area, String zipcode, String mobileNo) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.emailId = emailId;
        this.occupation = occupation;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.area = area;
        this.zipcode = zipcode;
        this.mobileNo = mobileNo;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
                ", zipcode='" + zipcode + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
