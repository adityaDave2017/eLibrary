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
<c:set var="members_list" value="${sessionScope.get('MEMBERS_LIST')}"/>
<c:choose>
    <c:when test="${param.show == 'books'}">
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
                        <td>Page Count</td>
                        <td>Authors</td>
                        <td>Publisher</td>
                        <td>Publish Date</td>
                        <td>Category</td>
                        <td>Avg. Rating</td>
                        <td>Quantity</td>
                        <td>Issued Qty.</td>
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
                            <td>${book.issuedQty}</td>
                            <td><a href="op_books.jsp?edit=true&bookId=${book.bookID}">EDIT</a></td>
                            <td><a href="op_books?delete=true&bookId=${book.bookID}">DELETE</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${param.show == 'members'}">
        <c:choose>
            <c:when test="${members_list.size() == 0}">
                No Members Enrolled.
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <td>First Name</td>
                        <td>Middle Name</td>
                        <td>Last Name</td>
                        <td>Email ID</td>
                        <td>Occupation</td>
                        <td>Address Line 1</td>
                        <td>Address Line 2</td>
                        <td>Area</td>
                        <td>Zipcode</td>
                        <td>Mobile No.</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="member" items="${members_list}">
                        <tr>
                            <td>${member.firstName}</td>
                            <td>${member.middleName}</td>
                            <td>${member.lastName}</td>
                            <td>${member.emailId}</td>
                            <td>${member.occupation}</td>
                            <td>${member.addressLine1}</td>
                            <td>${member.addressLine2}</td>
                            <td>${member.area}</td>
                            <td>${member.zipCode}</td>
                            <td>${member.mobileNo}</td>
                            <td><a href="op_member.jsp?edit=true&memberId=${member.memberId}">EDIT</a></td>
                            <td><a href="op_member?delete=true&memberId=${member.memberId}">DELETE</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>

</body>
</html>