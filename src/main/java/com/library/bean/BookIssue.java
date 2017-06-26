package com.library.bean;

import javax.annotation.Generated;
import javax.persistence.*;


@Entity
@Table(name = "book_issue")
@NamedQueries({
//        @NamedQuery(name = "insertBookIssue", query = "INSERT INTO BookIssue "),
        @NamedQuery(name = "deleteBookIssue", query = "DELETE BookIssue bookIssue WHERE bookIssue.memberId = :memberId AND bookIssue.bookId = :bookId"),
        @NamedQuery(name = "getBookIssueQtyByMember", query = "SELECT COUNT(*) AS qty FROM BookIssue bookIssue WHERE bookIssue.memberId = :memberMobileNo AND bookIssue.bookId = :bookId")
})
@SuppressWarnings({"unused"})
public class BookIssue {

    @Id
    @Generated(value = "assigned")
    private int issueId;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "return_date")
    private String returnDate;


    public BookIssue() {
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BookIssue{" +
                "issueId=" + issueId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }

}
