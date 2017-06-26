package com.library.bean;


import javax.annotation.Generated;
import javax.persistence.*;


@Entity
@Table(name = "librarian")
@NamedQueries({
        @NamedQuery(name = "getAllLibrarians", query = "FROM Librarian"),
        @NamedQuery(name = "findLibrarianByMobileNo", query = "FROM Librarian librarian WHERE librarian.mobileNo = :mobileNo"),
        @NamedQuery(name = "findLibrarianByUsername", query = "FROM Librarian librarian WHERE librarian.username = :username"),
        @NamedQuery(name = "updateLibrarianById", query = "UPDATE Librarian librarian SET librarian.name = :name, librarian.username = :username, librarian.emailId = :emailId, librarian.mobileNo = ? WHERE librarian.librarianId = :librarianId"),
        @NamedQuery(name = "getLibrarianName", query = "FROM Librarian librarian WHERE librarian.username = :username"),
        @NamedQuery(name = "removeLibrarian", query = "DELETE Librarian librarian WHERE librarian.librarianId = :librarianId"),
        @NamedQuery(name = "changePassword", query = "UPDATE Librarian librarian SET librarian.password = :newPassword WHERE username = :username")
})
public class Librarian {

    @Id
    @Generated(value = "assigned")
    private int librarianId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email_id")
    private String emailId;


    public Librarian() {
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "librarianId=" + librarianId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
