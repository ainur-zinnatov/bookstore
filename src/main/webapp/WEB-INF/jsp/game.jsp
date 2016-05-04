<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Game</title>
</head>
<body>
<h5>${message}</h5>
<form method="post" action="">
    <input type="text" name="word"> <br> <br>
    <input type="submit" value="say">

</form>

<c:forEach items="${words}" var="entry">
    ${entry}<br>
</c:forEach>
</body>
</html>