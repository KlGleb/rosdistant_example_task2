<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String queryString = request.getQueryString();
    if (queryString == null) queryString = "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Животные</title>
    <jsp:include page="style.jsp"/>
</head>
<body>
<h1><%= "Животные" %>
</h1>
<br/>
<jsp:include page="navigation.jsp"/>
<br/>
<a href="animals-xml-servlet?<%= queryString %>">xml-представление запроса</a>
<br/>
<br/>
<jsp:include page="animals-servlet?<%= queryString %>"/>
<br/>
<h4>Фильтры</h4>
<br/>
<form action="animals.jsp" method="get">
    <label for="name">Название:</label><br>
    <input id="name" type="text" name="name">
    <br/>
    <br/>
    <label for="name">Тип:</label><br>
    <jsp:include page="types-selector"/>
    <br/>
    <br/>
    <input type="submit" value="Искать">
</form>
<br/>
<a href="animals.jsp">Сбросить фильтры</a>
</body>
</html>