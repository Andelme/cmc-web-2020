<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вакансии</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<main>
    <table>
        <tr>
            <th>Должность</th>
            <th>Подразделение</th>
            <th>Ставка</th>
            <th>Оклад</th>
        </tr>
        <c:forEach var="vacancy" items="${vacancyList}">
            <tr>
                <td><a href="postype?id=${vacancy.postype_id.postype_id}">
                        ${vacancy.postype_id.postype_name}
                </a></td>
                <td><a href="department?id=${vacancy.department_id.department_id}">
                        ${vacancy.department_id.department_name}
                </a></td>
                <td>${vacancy.work_rate}</td>
                <td>${vacancy.postype_id.salary * vacancy.work_rate} руб</td>
                <td><a href="position_delete?dep=&id=${vacancy.position_id}">удалить</a></td>
            </tr>
        </c:forEach>
        <tr>
            <c:url value="/position_add?dep=" var="addr"/>
            <form:form modelAttribute="newVacancy" action="${addr}" method="POST">
                <td>
                    <form:select path="postype_id">
                        <form:option value="" selected="true" disabled="true">выберите должность</form:option>
                        <form:options items="${postypeList}" itemValue="postype_id" itemLabel="postype_name"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="department_id">
                        <form:option value="" selected="true" disabled="true">выберите подразделение</form:option>
                        <form:options items="${departmentList}" itemValue="department_id" itemLabel="department_name"/>
                    </form:select>
                </td>
                <td><form:input type = "number" min="0.25" max="1.0" step="0.25" path = "work_rate" value = "1.0"/></td>
                <td></td>
                <td><input type = "submit" value = "добавить"/></td>
            </form:form>
        </tr>
    </table>
</main>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>