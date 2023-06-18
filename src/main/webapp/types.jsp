<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Типы</title>
</head>
<body>
<h1><%= "Типы" %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<jsp:include page="type-servlet?<%= request.getQueryString() %>"/>
<br/><br/>
<form action="types.jsp" method="get">
    <label for="name">Название:</label><br>
    <input id="name" type="text" name="name">
    <br/>
    <br/>
    <label for="approx_count">Минимальное количество видов:</label><br>
    <input id="approx_count"  type="text" name="approx_count">
    <br/>
    <br/>
    <input type="submit" value="Искать">
</form>
<br/>
<a href="types.jsp">Сбросить фильтры</a>
</body>
</html>