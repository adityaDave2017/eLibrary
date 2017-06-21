<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.opcode} </title>
</head>
<body>

    <%@include file="header.html"%>
    <br><br>

    <c:choose>
        <c:when test="${param.edit == true}">
            <c:forEach var="librarian" items="${sessionScope.get('LIBRARIAN_LIST')}">
                <c:if test="${librarian.librarianId == param.librarian_id}">
                    <c:set var="edit_librarian" value="${librarian}"/>
                </c:if>
            </c:forEach>
            <form action="op_librarian?edit=true" method="post">
                <input type="hidden" name="LibrarianId" value="${edit_librarian.librarianId}">
                <label>Name: <input type="text" name="Name" value="${edit_librarian.name}"></label><br>
                <label>Username: <input type="text" name="Username" value="${edit_librarian.username}"></label><br>
                <label>Email Id: <input type="text" name="EmailId" value="${edit_librarian.emailId}"></label><br>
                <label>Mobile No. <input type="text" name="MobileNo" value="${edit_librarian.mobileNo}"></label><br>
                <br>
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:when test="${param.add == true}">
            <form action="op_librarian?add=true" method="post">
                <label>Name: <input type="text" name="Name"></label><br>
                <label>Username: <input type="text" name="Username"></label><br>
                <label>Password: <input type="password" name="Password"></label><br>
                <label>Email Id: <input type="text" name="EmailId"></label><br>
                <label>Mobile No. <input type="text" name="MobileNo"></label><br>
                <br>
                <input type="submit" value="Add">
            </form>
        </c:when>
    </c:choose>

</body>
</html>
