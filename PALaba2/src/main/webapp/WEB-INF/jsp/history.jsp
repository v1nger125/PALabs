<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ban history</title>
</head>
<body>

<h3>History:</h3>(<a href="list">back</a>)
<ol>
    <c:forEach items="${history}" var="historyItem">
        <li>
                ${historyItem.user.getId()} | ${historyItem.banDate} | ${historyItem.duration}
        </li>
    </c:forEach>
</ol>