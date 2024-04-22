<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${bundle.locale}" />
<fmt:setBundle basename="localization.messages" />
<html>
<body>
    <h1><fmt:message key="register_heading" /></h1>
</body>
<form action="${pageContext.request.contextPath}/change-locale-servlet" method="post">
    <select name="locale">
        <option value="en">English</option>
        <option value="be">Belarusian</option>
        <!-- Add more options as needed -->
    </select>
    <input type="submit" value="Change Locale">
</form>
</html>