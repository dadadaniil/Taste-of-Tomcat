<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${bundle.locale}" />
<fmt:setBundle basename="localization.messages" />
<html>
<body>
    <h1><fmt:message key="register_heading" /></h1>
</body>
</html>