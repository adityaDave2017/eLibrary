package com.library.bean;


import javax.annotation.Generated;
import javax.persistence.*;
import java.io.File;


@Entity
@Table(name = "member")
@NamedQueries({
        @NamedQuery(name = "getAllMembers", query = "FROM Librarian"),
        @NamedQuery(name = "getMemberByMobileNo", query = "FROM Member member WHERE member.mobileNo = :mobileNo"),
        @NamedQuery(name = "updateMember", query = "UPDATE Member member SET member.firstName = :firstName, member.middleName = :middleName, member.lastName = :lastname, member.emailId = :emailId, member.occupation = :occupation, member.addressLine1 = :addressLine1, member.addressLine2 = :addressLine2, member.area = :area, member.zipCode = :zipCode, member.mobileNo = :mobileNo WHERE member.memberId = :memberid"),
        @NamedQuery(name = "removeMemberById", query = "DELETE Member member WHERE member.memberId = :memberId")
})
@SuppressWarnings({"unused", "SameParameterValue"})
public class Member {

    @Id
    @Generated(value = "assigned")
    private int memberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "addr_1")
    private String addressLine1;

    @Column(name = "addr_2")
    private String addressLine2;

    @Column(name = "area")
    private String area;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "id_proof")
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
