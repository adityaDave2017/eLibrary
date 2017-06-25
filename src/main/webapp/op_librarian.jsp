<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.opcode} </title>
</head>
<body>

    <%@include file="admin_header.html"%>
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
                <label>Name: <input type="text" name="Name" value="${edit_librarian.name}" required="required"></label><br>
                <label>Username: <input type="text" name="Username" value="${edit_librarian.username}" required="required"></label><br>
                <label>Email Id: <input type="text" name="EmailId" value="${edit_librarian.emailId}" required="required"></label><br>
                <label>Mobile No. <input type="text" name="MobileNo" value="${edit_librarian.mobileNo}" required="required"></label><br>
                <br>
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:when test="${param.add == true}">
            <form action="op_librarian?add=true" method="post">
                <label>Name: <input type="text" name="Name" required="required"></label><br>
                <label>Username: <input type="text" name="Username" required="required"></label><br>
                <label>Password: <input type="password" name="Password" required="required"></label><br>
                <label>Email Id: <input type="text" name="EmailId" required="required"></label><br>
                <label>Mobile No. <input type="text" name="MobileNo" required="required"></label><br>
                <br>
                <input type="submit" value="Add">
            </form>
        </c:when>
    </c:choose>

</body>
</html>
