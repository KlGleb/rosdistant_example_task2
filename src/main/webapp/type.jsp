<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Тип</title>
</head>
<body>
<h1><%= "Просмотр и редактирование типа с id=" %><%= request.getQueryString() %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<br>
<br>
<jsp:include page="type?<%= request.getQueryString() %>" />
</body>
</html>