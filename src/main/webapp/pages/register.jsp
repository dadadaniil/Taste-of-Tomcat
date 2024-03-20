<%--
  Created by IntelliJ IDEA.
  User: Dadadaniil
  Date: 3/19/2024
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register Page</h2>
<form action="${pageContext.request.contextPath}/register-servlet" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    <div>
        <input type="submit" value="submit">
    </div>

</form>
</body>
</html>