<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String queryString = request.getQueryString();
    if (queryString == null) queryString = "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Типы</title>
    <jsp:include page="style.jsp" />
</head>
<body>
<h1><%= "Типы" %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<br/>
<a href="types-xml-servlet?<%= queryString %>">xml-представление запроса</a>
<br/>
<br/>
<jsp:include page="type-servlet?<%= queryString %>"/>
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