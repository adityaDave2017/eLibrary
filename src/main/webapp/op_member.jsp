<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Member</title>
</head>
<body>

<%@ include file="librarian_header.html" %>
<br><br>

<c:choose>
    <c:when test="${param.add == true}">
        <form action="op_member?add=true" method="post">
            <label>First Name: <input type="text" name="FirstName" required="required"></label><br><br>
            <label>Middle Name: <input type="text" name="MiddleName" required="required"></label><br><br>
            <label>Last Name: <input type="text" name="LastName" required="required"></label><br><br>
            <label>Email ID: <input type="email" name="EmailId" required="required"></label><br><br>
            <label>Occupation: <input type="text" name="Occupation"></label><br><br>
            <label>Address Line 1: <input type="text" name="Addr1"></label><br><br>
            <label>Address Line 2: <input type="text" name="Addr2"></label><br><br>
            <label>Area: <input type="text" name="Area"></label><br><br>
            <label>Zip Code: <input type="text" name="ZipCode"></label><br><br>
            <label>Mobile No.: <input type="number" name="MobileNo"></label><br><br>
            <label>ID Proof: <input type="file" name="IdProof" disabled="disabled"></label><br>
            <br>
            <input type="submit" value="Add">
        </form>
    </c:when>

    <c:when test="${param.edit == true}">
        <c:forEach var="member" items="${sessionScope.MEMBERS_LIST}">
            <c:if test="${member.memberId == param.memberId}">
                <c:set var="edit_member" value="${member}"/>
            </c:if>
        </c:forEach>
        <form action="op_member?edit=true" method="post">
            <input type="hidden" name="MemberId" value="${edit_member.memberId}">
            <label>First Name: <input type="text" name="FirstName" value="${edit_member.firstName}" required="required"></label><br><br>
            <label>Middle Name: <input type="text" name="MiddleName" value="${edit_member.middleName}"
                                       required="required"></label><br><br>
            <label>Last Name: <input type="text" name="LastName" value="${edit_member.lastName}"
                                     required="required"></label><br><br>
            <label>Email ID: <input type="email" name="EmailId" value="${edit_member.emailId}"
                                    required="required"></label><br><br>
            <label>Occupation: <input type="text" name="Occupation" value="${edit_member.occupation}"></label><br><br>
            <label>Address Line 1: <input type="text" name="Addr1" value="${edit_member.addressLine1}"></label><br><br>
            <label>Address Line 2: <input type="text" name="Addr2" value="${edit_member.addressLine2}"></label><br><br>
            <label>Area: <input type="text" name="Area" value="${edit_member.area}"></label><br><br>
            <label>Zip Code: <input type="text" name="ZipCode" value="${edit_member.zipCode}"></label><br><br>
            <label>Mobile No.: <input type="number" name="MobileNo" value="${edit_member.mobileNo}"></label><br><br>
            <label>ID Proof: <input type="file" name="IdProof" disabled="disabled"></label><br>
            <br>
            <input type="submit" value="Edit">
        </form>
    </c:when>
</c:choose>

</body>
</html>
