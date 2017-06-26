package com.library.bean;


import javax.annotation.Generated;
import javax.persistence.*;


@Entity
@Table(name = "books")
@NamedQueries(value = {
        @NamedQuery(name = "getAllBooks", query = "FROM Book "),
        @NamedQuery(name = "findBookByIsbn", query = "FROM Book book WHERE book.isbn13 = :isbn13 OR book.isbn10 = :isbn10"),
        @NamedQuery(name = "editBookById", query = "UPDATE Book book SET book.isbn13 = :isbn13, book.isbn10 = :isbn10, book.title = :title, book.description = :description, book.pageCount = :pageCount, book.authors = :authors, book.publisher = :publisher, book.publishDate = :publishDate, book.category = :category, book.avgRating = :avgRating, book.imageUrl = :imageUrl, book.quantity = :quantity, book.issuedQty = :issuedQty WHERE book.bookID = :bookID"),
        @NamedQuery(name = "deleteBookById", query = "DELETE Book book WHERE book.bookID = :bookID"),
        @NamedQuery(name = "getUnissuedQtyByIsbn", query = "SELECT book.quantity - book.issuedQty AS remaining FROM Book AS book WHERE book.isbn10 = :isbn10 OR book.isbn13 = :isbn13"),
        @NamedQuery(name = "issueBook", query = "UPDATE Book book SET book.issuedQty = book.issuedQty + 1 WHERE book.isbn10 =:isbn10 OR book.isbn13 = :isbn13"),
        @NamedQuery(name = "returnBook", query = "UPDATE Book book SET book.issuedQty = book.issuedQty - 1 WHERE book.isbn10 =:isbn10 OR book.isbn13 = :isbn13")
})
public class Book {

    @Id
    @Generated(value = "assigned")
    private int bookID;

    @Column(name = "isbn_13")
    private String isbn13;

    @Column(name = "isbn_10")
    private String isbn10;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "authors")
    private String authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_date")
    private String publishDate;

    @Column(name = "category")
    private String category;

    @Column(name = "avg_rating")
    private String avgRating;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "issued_qty")
    private int issuedQty;


    public Book() {
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIssuedQty() {
        return issuedQty;
    }

    public void setIssuedQty(int issuedQty) {
        this.issuedQty = issuedQty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", isbn13='" + isbn13 + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", category='" + category + '\'' +
                ", avgRating='" + avgRating + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", issued quantity=" + issuedQty +
                '}';
    }
}
