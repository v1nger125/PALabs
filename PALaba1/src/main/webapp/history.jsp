<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>История банов</title>
</head>
<body>

<h3>История:</h3>(<a href="list">назад</a>)
<ol>
    <li>
        id | Дата бана | длительность
    </li>
    <c:forEach items="${history}" var="historyItem">
        <li>
                ${historyItem.user.id} | ${historyItem.banDate} | ${historyItem.duration}
        </li>
    </c:forEach>
</ol>