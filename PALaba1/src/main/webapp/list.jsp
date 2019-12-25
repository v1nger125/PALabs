<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>

<h3>Все пользователи:</h3>(<a href="add">добавить</a>)
(<a href="history">история</a>)
<ol>
    <c:forEach items="${users}" var="user">
        <li>
                id | nickname | Дата создания | Число блокировок | Состояние
        </li>
        <li>
                ${user.id} | ${user.nickname} | ${user.registerDate} | ${user.banNumber} | ${user.banChecker}
            <a href="ban?id=${user.id}">Бан</a> <a href="unBan?id=${user.id}">Разбан</a>
        </li>
    </c:forEach>
</ol>

</body>
</html>
