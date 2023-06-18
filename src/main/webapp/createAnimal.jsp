<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создание животного</title>
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

    <label for="id_type"> ID типа:</label>
    <input type="text" id="id_type" name="id_type" required><br><br>

    <input type="submit" value="Отправить">
</form>

</body>
</html>