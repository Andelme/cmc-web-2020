<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<html>
<head>
    <title>Должности</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<ul>
    <c:forEach var="positionType" items="${postypeList}">
        <li>
            <a href="postype?id=${positionType.postype_id}">
                <div>${positionType.postype_name}</div>
            </a>
        </li>
    </c:forEach>
</ul>
<a href="postype_add">
    <div>Добавить должность</div>
</a>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>