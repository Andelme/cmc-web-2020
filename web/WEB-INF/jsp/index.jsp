<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HelloPage</title>
</head>
<body>
<c:forEach items="${workerList}" var="worker">
    <td>${worker.name}</td>
</c:forEach>
</body>
</html>
