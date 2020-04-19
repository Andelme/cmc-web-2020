<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<html>
<head>
    <title>${worker.name}</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<h2>${worker.name}</h2>
<div>
    <h4>Дата рождения</h4>
    <p><fmt:formatDate value="${worker.birth_date}" pattern="dd.MM.yyyy г"/></p>
</div>
<div>
    <h4>Дата найма</h4>
    <p><fmt:formatDate value="${worker.hire_date}" pattern="dd.MM.yyyy г"/></p>
</div>
<div>
    <h4>Образование</h4>
    <p>${worker.education_degree}</p>
</div>
<div>
    <h4>Телефон</h4>
    <p>${worker.phone_number}</p>
</div>
<div>
    <h4>Адрес</h4>
    <p>${worker.address}</p>
</div>
<div>
    <h4>Текущее место работы</h4>
    <p>${currentPosition.postype_id.postype_name} ${currentPosition.department_id.department_name}</p>
</div>
<div>
    <h4>История работы</h4>
    <table>
        <tr>
            <th>Должность</th>
            <th>Подразделение</th>
            <th>Ставка</th>
            <th>Дата назначения</th>
            <th>Дата перевода</th>
        </tr>
        <c:forEach var="workPosition" items="${positionHistory}">
            <tr>
                <td>${workPosition.postype_id.postype_name}</td>
                <td>${workPosition.department_id.department_name}</td>
                <td>${workPosition.work_rate}</td>
                <td><fmt:formatDate value="${workPosition.appointment_date}" pattern="dd.MM.yyyy г"/></td>
                <td><c:choose>
                    <c:when test="${workPosition.retire_date != null}">
                        <fmt:formatDate value="${workPosition.retire_date}" pattern="dd.MM.yyyy г"/>
                    </c:when>
                    <c:otherwise>
                        н.в.
                    </c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <a href="worker_edit?id=${worker.worker_id}">
        Изменить данные работника
    </a>
</div>
<div>
    <a href="worker_delete?id=${worker.worker_id}">
        Удалить данные работника
    </a>
</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>
