<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <c:choose>
        <c:when test = "${worker.worker_id == null}">
            <title>Добавить данные работника</title>
        </c:when>
        <c:otherwise>
            <title>Изменить данные работника</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>

<form:form modelAttribute="worker" method="POST">
    <form:input type="hidden" path="worker_id" value="${worker.worker_id}"/>
    <form:input type="hidden" path="hire_date" value="${worker.hire_date}"/>
    <div>
        <label for="name">ФИО</label>
        <form:input type="text" path="name" id="name" value="${worker.name}"/>
    </div>
    <div>
        <label for="birth_date">Дата рождения</label>
        <form:input type="date" path="birth_date" id="birth_date" value="${worker.birth_date}"/>
    </div>
    <div>
        <label for="address">Адрес проживания</label>
        <form:textarea path="address" id="address" value="${worker.address}"/>
    </div>
    <div>
        <label for="phone_number">Номер телефона</label>
        <form:input type="tel" path="phone_number" id="phone_number" value="${worker.phone_number}" placeholder="+7(***)***-**-**"/>
    </div>
    <div>
        <label for="name">Образование</label>
        <form:select type="text" path="education_degree" id="education_degree">
            <form:option value="without_degree"/>
            <form:option value="bachelor"/>
            <form:option value="master"/>
            <form:option value="doctor"/>
        </form:select>
    </div>
    <div>
        <c:choose>
            <c:when test = "${worker.worker_id == null}">
                <input type = "submit" value = "добавить"/>
                <a href = "workers">отмена</a>
            </c:when>
            <c:otherwise>
                <input type = "submit" value = "обновить"/>
                <a href = "worker?id=${worker.worker_id}">отмена</a>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>

<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>