<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome ${sessionScope.userName}</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid orange;
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
Welcome, ${sessionScope.userName}
<br><br>
<%@ include file="librarian_header.html" %>
<br>

<c:set var="books_list" value="${sessionScope.get('BOOKS_LIST')}"/>
<c:choose>
    <c:when test="${books_list.size() == 0}">
        No books present.
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <td>Image</td>
                <td>ISBN 13</td>
                <td>ISBN 10</td>
                <td>Title</td>
                <td>Description</td>
                <td>Authors</td>
                <td>Publisher</td>
                <td>Publish Date</td>
                <td>Category</td>
                <td>Avg. Rating</td>
                <td>Quantity</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books_list}">
                <tr>
                    <td><img src="${book.imageUrl}"></td>
                    <td>${book.isbn13}</td>
                    <td>${book.isbn10}</td>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                    <td>${book.pageCount}</td>
                    <td>${book.authors}</td>
                    <td>${book.publisher}</td>
                    <td>${book.publishDate}</td>
                    <td>${book.category}</td>
                    <td>${book.avgRating}</td>
                    <td>${book.quantity}</td>
                    <td><a href="op_books.jsp?edit=true&bookId=${book.bookID}">EDIT</a></td>
                    <td><a href="op_books?delete=true&bookId=${book.bookID}">DELETE</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>