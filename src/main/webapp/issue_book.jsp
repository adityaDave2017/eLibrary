<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issue Book</title>
</head>
<body>

Welcome ${sessionScope.userName}<br>
<%@include file="librarian_header.html" %>
<br><br>

<form action="issue_book" method="post">
    <label>Book ISBN: <input type="number" name="Isbn" required="required"></label><br><br>
    <label>Member's Mobile No.: <input type="number" name="MobileNo" required="required"></label><br><br>
    <label>Issue <input type="radio" name="Action" value="ISSUE"></label>&nbsp;&nbsp;&nbsp;<label>Return<input
        type="radio" name="Action" value="RETURN"></label><br>
    <br>
    <input type="submit" value="Go">
</form>
</body>
</html>
