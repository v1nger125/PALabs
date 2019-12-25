<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<html>
<head>
    <title>Add</title>
</head>
<body>

<form action="add" method="post">
    <label for="nickname">Chose name:
        <input type="text" id="nickname" value="" name="nickname" />
    </label>
    <input type="submit" value="Add"/>
</form>

</body>
</html>
