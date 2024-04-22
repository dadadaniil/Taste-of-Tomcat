<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.messages"/>
<html lang="${sessionScope.lang}">
<html>
<head>
    <title><fmt:message key="login_heading"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<h2><fmt:message key="login_heading"/></h2>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<c:if test="${not empty successMessage}">
    <div style="color: green;">${successMessage}</div>
</c:if>
<form action="${pageContext.request.contextPath}/login-servlet" method="post">
    <label for="email"><fmt:message key="email"/>:</label><br>
    <input type="text" id="email" name="email"><br>
    <label for="password"><fmt:message key="password"/>:</label><br>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="<fmt:message key="login_button" />">
</form>
<form action="${pageContext.request.contextPath}/change-locale-servlet" method="post">
    <button type="submit" name="locale" value="en" class="lang-button"
            style="background-image: url('${pageContext.request.contextPath}/images/en.png');"></button>
    <button type="submit" name="locale" value="be" class="lang-button"
            style="background-image: url('${pageContext.request.contextPath}/images/be.png');"></button>
</form>
</body>
</html>