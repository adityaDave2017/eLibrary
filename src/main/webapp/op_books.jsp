<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book Operations</title>
</head>
<body>

<%@include file="librarian_header.html"%>
<br><br>

<c:if test="${param.find == true}">
    <%@include file="find_book.html"%>
</c:if>

<c:if test="${param.edit == true}">
    <c:set var="books_list" value="${sessionScope.get('BOOKS_LIST')}"/>
    <c:forEach var="book" items="${books_list}">
        <c:if test="${book.bookID == param.bookId}">
            <form action="op_books?edit=true" method="post">
                <input type="hidden" name="bookId" value="bookId=${book.bookID}">
                <label>ISBN 13: <input type="number" name="isbn13" value="${book.isbn13}"></label><br><br>
                <label>ISBN 10: <input type="number" name="isbn10" value="${book.isbn10}"></label><br><br>
                <label>Title: <input type="text" name="title" value="${book.title}"></label><br><br>
                <label>Description: <textarea name="description">${book.description}</textarea></label><br><br>
                <label>Page Count: <input type="number" name="pageCount" value="${book.pageCount}"></label><br><br>
                <label>Authors: <input type="text" name="authors" value="${book.authors}"></label><br><br>
                <label>Publisher: <input type="text" name="publisher" value="${book.publisher}"></label><br><br>
                <label>Publish Date: <input type="text" name="publishDate" value="${book.publishDate}"></label><br><br>
                <label>Category: <input type="text" name="category" value="${book.category}"></label><br><br>
                <label>Avg. Rating: <input type="number" name="avgRating" value="${book.avgRating}"></label><br><br>
                <label>Image URL: <input type="text" name="imageUrl" value="${book.imageUrl}"></label><br><br>
                <label>Quantity: <input type="number" name="quantity" value="${book.quantity}"></label><br>
                <br>
                <input type="submit" value="Edit">
            </form>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${param.add == true}">
    <c:set var="newBookList" value="${sessionScope.FOUND_BOOKS_LIST}"/>
    <c:choose>
        <c:when test="${newBookList.size() == 0}">
            No Books Found. Try Again!
            <br><br>
            <%@include file="find_book.html"%>
        </c:when>
        <c:otherwise>
            <c:forEach var="newBook" items="${newBookList}">
                <form action="op_books?add=true" method="post">
                    <label>ISBN 13: <input type="number" name="isbn13" value="${newBook.isbn13}" required="required"></label><br><br>
                    <label>ISBN 10: <input type="number" name="isbn10" value="${newBook.isbn10}" required="required"></label><br><br>
                    <label>Title: <input type="text" name="title" value="${newBook.title}" required="required"></label><br><br>
                    <label>Description: <textarea name="description" required="required">${newBook.description}</textarea></label><br><br>
                    <label>Page Count: <input type="number" name="pageCount" value="${newBook.pageCount}" required="required"></label><br><br>
                    <label>Authors: <input type="text" name="authors" value="${newBook.authors}" required="required"></label><br><br>
                    <label>Publisher: <input type="text" name="publisher" value="${newBook.publisher}" required="required"></label><br><br>
                    <label>Publish Date: <input type="text" name="publishDate" value="${newBook.publishDate}" required="required"></label><br><br>
                    <label>Category: <input type="text" name="category" value="${newBook.category}" required="required"></label><br><br>
                    <label>Avg. Rating: <input type="number" name="avgRating" value="${newBook.avgRating}" required="required"></label><br><br>
                    <label>Image URL: <input type="text" name="imageUrl" value="${newBook.imageUrl}" required="required"></label><br><br>
                    <label>Quantity: <input type="number" name="quantity" required="required"></label><br>
                    <br>
                    <input type="submit" value="Add">
                </form>
                <br><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</c:if>

</body>
</html>
