<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<html>
<head>
    <title>${positionType.postype_name}</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<h2>${positionType.postype_name}</h2>
<div>
    <h4>Оклад</h4>
    <p>${positionType.salary} руб</p>
</div>
<div>
    <h4>Обязанности</h4>
    <p>${positionType.responsibilities}</p>
</div>
<div>
    <a href="postype_edit?id=${positionType.postype_id}">
        Изменить должность
    </a>
</div>
<div>
    <a href="postype_delete?id=${positionType.postype_id}">
        Удалить должность
    </a>
</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>