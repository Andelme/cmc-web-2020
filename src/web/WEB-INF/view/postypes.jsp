<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Должности</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<main>
    <ul>
        <c:forEach var="positionType" items="${postypeList}">
            <li>
                <a href="postype?id=${positionType.postype_id}">
                    ${positionType.postype_name}
                </a>
            </li>
        </c:forEach>
    </ul>
    <a href="postype_add">
        <div>Добавить должность</div>
    </a>
</main>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>