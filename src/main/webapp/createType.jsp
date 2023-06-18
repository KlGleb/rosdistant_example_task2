<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создание типа</title>
    <jsp:include page="style.jsp" />
</head>
<body>
<h1><%= "Создание типа" %>
</h1>
<br/>
<jsp:include page="navigation.jsp" />
<br>
<br>
<form action="type-servlet" method="post" accept-charset="UTF-8">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="approx_count">Приблизительное количество видов:</label>
    <input type="text" id="approx_count" name="approx_count" required><br><br>

    <input type="submit" value="Отправить">
</form>
</body>
</html>