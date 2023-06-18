<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создание животного</title>
    <jsp:include page="style.jsp" />
</head>
<body>
<h1><%= "Создание животного" %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<br>
<br>
<form action="animals-servlet" method="post" accept-charset="UTF-8">
    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="name">Тип:</label><br>
    <jsp:include page="types-selector"/>
    <input type="submit" value="Отправить">
</form>

</body>
</html>