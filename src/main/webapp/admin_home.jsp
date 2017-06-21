<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Admin</title>
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
Welcome, Admin
<%@include file="header.html"%>
<br>
<a href="op_librarian.jsp?add=true">ADD</a>
<br><br>
<table>
    <thead>
        <tr>
            <td>Name</td>
            <td>User Name</td>
            <td>Email ID</td>
            <td>Mobile No.</td>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="librarian" items="${sessionScope.get('LIBRARIAN_LIST')}">
    <tr>
        <td>${librarian.name}</td>
        <td>${librarian.username}</td>
        <td>${librarian.emailId}</td>
        <td>${librarian.mobileNo}</td>
        <td><a href="op_librarian.jsp?edit=true&librarian_id=${librarian.librarianId}">EDIT</a></td>
        <td><a href="op_librarian?delete=true&librarian_id=${librarian.librarianId}">DELETE</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
