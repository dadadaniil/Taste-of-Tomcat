<jsp:useBean id="users" scope="session" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
</head>
<body>
<h1>Users List</h1>
<h2>There are ${users.size()} registered users</h2>
<p>${users.get(1)}peoairhg</p>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Email</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>