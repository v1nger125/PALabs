<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User list</title>
</head>
<body>

<h3>All users:</h3>(<a href="add">add</a>)
(<a href="history">History</a>)
<ol>
    <c:forEach items="${users}" var="user">
        <li>
                ${user.id} | ${user.nickname} | ${user.registerDate} | ${user.banNumber} | ${user.banChecker}
            <a href="ban?id=${user.id}">Ban</a> <a href="unBan?id=${user.id}">Unbab</a>
        </li>
    </c:forEach>
</ol>

</body>
</html>
