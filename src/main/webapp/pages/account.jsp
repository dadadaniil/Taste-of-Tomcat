<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Management</title>
</head>
<body>
<h2>Account Management</h2>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<c:if test="${not empty successMessage}">
    <div style="color: green;">${successMessage}</div>
</c:if>

<img src="data:image/png;base64,${userImage}" alt="User Image">

<form action="${pageContext.request.contextPath}/account-servlet" method="post">
    <div>
        <label for="newUsername">New Username:</label>
        <input type="text" id="newUsername" name="newUsername">
        <input type="submit" name="action" value="Change Username">
    </div>
    <div>
        <label for="letterToJoBiden">New letter to Joe Biden:</label>
        <textarea id="letterToJoBiden" name="letterToJoBiden"></textarea>
        <input type="submit" name="action" value="Send letter">
    </div>
    <div>
        <input type="submit" name="action" value="Logout">
        <input type="submit" name="action" value="Delete Account">
    </div>
</form>
</body>
</html>