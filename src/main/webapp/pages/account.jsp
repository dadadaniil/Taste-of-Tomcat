<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.messages"/>
<html>
<head>
    <title><fmt:message key="account_management"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css">
</head>
<body>
<h2><fmt:message key="account_management"/></h2>

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
        <label for="avatar"><fmt:message key="new_avatar"/>:</label>
        <input type="file" id="avatar" name="avatar">
        <input type="submit" name="action" value="<fmt:message key="upload_avatar_button" />">

    </div>
    <div>
        <label for="username"><fmt:message key="username"/>:</label>
        <span>${user.username}</span>
    </div>
    <div>
        <label for="newUsername"><fmt:message key="new_username"/>:</label>
        <input type="text" id="newUsername" name="newUsername">
        <input type="submit" name="action" value="<fmt:message key="change_username_button" />">
    </div>
    <div>
        <label for="email"><fmt:message key="email"/>:</label>
        <span>${user.email}</span>
    </div>
    <div>
        <label for="letterToJoBiden">New letter to Joe Biden:</label>
        <textarea id="letterToJoBiden" name="letterToJoBiden"></textarea>
        <input type="submit" name="action" value="<fmt:message key="send_letter_button" />">
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/account-servlet" method="post">
            <input type="submit" name="action" value="<fmt:message key="logout_button" />">
            <input type="submit" name="action" value="<fmt:message key="delete_account_button" />">
        </form>
    </div>
</form>
</body>
</html>