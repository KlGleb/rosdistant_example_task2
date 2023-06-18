<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная</title>
    <jsp:include page="style.jsp" />
</head>
<body>
<h1><%= "Просмотр и редактирование животного с id=" %><%= request.getQueryString() %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<br>
<br>
<jsp:include page="animal?<%= request.getQueryString() %>" />
</body>
</html>