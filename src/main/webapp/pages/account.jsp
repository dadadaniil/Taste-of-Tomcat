<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css">
</head>
<body>
<h2>Account Management</h2>

<c:choose>
    <c:when test="${not empty sessionScope.successMessage}">
        <div style="color: green;">${sessionScope.successMessage}</div>
        <c:remove var="successMessage" scope="session"/>
    </c:when>
    <c:when test="${not empty requestScope.errorMessage}">
        <div style="color: red;">${requestScope.errorMessage}</div>
    </c:when>
    <c:when test="${not empty requestScope.successMessage}">
        <div style="color: green;">${requestScope.successMessage}</div>
    </c:when>
</c:choose>

<!-- Rest of your code -->
</body>
</html>


<style>
    .user-image {
        width: 150px;
        height: 150px;
        object-fit: cover;
    }
</style>
<form action="${pageContext.request.contextPath}/account-servlet" method="post" enctype="multipart/form-data">
    <img class="user-image" src="data:image/png;base64,${userImage}" alt="User Image">
    <div>
        <label for="avatar">New Avatar:</label>
        <input type="file" id="avatar" name="avatar">
        <input type="submit" name="action" value="Upload Avatar">

    </div>
    <div>
        <label for="username">Username:</label>
        <span>${user.username}</span>
    </div>
    <div>
        <label for="newUsername">New Username:</label>
        <input type="text" id="newUsername" name="newUsername">
        <input type="submit" name="action" value="Change Username">
    </div>
    <div>
        <label for="email">Email:</label>
        <span>${user.email}</span>
    </div>
    <div>
        <label for="letterToJoBiden">New letter to Joe Biden:</label>
        <textarea id="letterToJoBiden" name="letterToJoBiden"></textarea>
        <input type="submit" name="action" value="Send letter">
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/account-servlet" method="post">
            <input type="submit" name="action" value="Logout">
            <input type="submit" name="action" value="Delete Account">
        </form>
    </div>
</form>
</body>
</html>