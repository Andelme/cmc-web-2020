<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <c:choose>
        <c:when test = "${positionType.postype_id == null}">
            <title>Добавить должность</title>
        </c:when>
        <c:otherwise>
            <title>Изменить должность</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>

<form:form modelAttribute="positionType"
           method="POST">
    <form:input type = "hidden" path = "postype_id" value = "${positionType.postype_id}"/>
    <div>
        <label for="postype_name">Название должности</label>
        <form:input type="text" path="postype_name" id="postype_name" value="${positionType.postype_name}"/>
    </div>
    <div>
        <label for="salary">Оклад</label>
        <form:input type="number" min="0" path="salary" id="salary" value="${positionType.salary}"/>
    </div>
    <div>
        <label for="responsibilities">Обязанности</label>
        <form:textarea path="responsibilities" id="responsibilities" value="${positionType.responsibilities}"/>
    </div>
    <div>
        <c:choose>
            <c:when test = "${positionType.postype_id == null}">
                <input type = "submit" value = "добавить"/>
                <a href = "postypes">отмена</a>
            </c:when>
            <c:otherwise>
                <input type = "submit" value = "обновить"/>
                <a href = "postype?id=${positionType.postype_id}">отмена</a>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>

<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>