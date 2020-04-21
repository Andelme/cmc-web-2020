<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<head>
    <title>${department.department_name}</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<main>
    <h2>${department.department_name}</h2>
    <div style="${department.head_department != null ? 'display:block' : 'display:none'}">
        <h4>Головное подразделение</h4>
        <a href="department?id=${department.head_department.department_id}">
            ${department.head_department.department_name}
        </a>
    </div>
    <div style="${department.child_departments.size() != 0 ? 'display:block' : 'display:none'}">
        <h4>Дочерние подразделения</h4>
        <ul>
            <c:forEach var="child_department" items="${department.child_departments}">
                <li>
                    <a href="department?id=${child_department.department_id}">
                            ${child_department.department_name}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <h4>Работники Подразделения</h4>
        <table>
            <tr>
                <th>ФИО</th>
                <th>Должность</th>
                <th>Ставка</th>
                <th>Оклад</th>
            </tr>
            <c:forEach var="workerPos" items="${workerList}">
                <tr>
                    <td><a href="worker?id=${workerPos.worker_id.worker_id}">
                            ${workerPos.worker_id.name}
                    </a></td>
                    <td><a href="postype?id=${workerPos.postype_id.postype_id}">
                            ${workerPos.postype_id.postype_name}
                    </a></td>
                    <td>${workerPos.work_rate}</td>
                    <td>${workerPos.postype_id.salary * workerPos.work_rate} руб</td>
                    <td><a href="retire?dep=${department.department_id}&id=${workerPos.position_id}">уволить</a></td>
                </tr>
            </c:forEach>
            <c:forEach var="vacancy" items="${vacancyList}">
                <c:set var="workPosition" value="${vacancy}" scope="request"/>
                <tr>
                    <c:url value="/appoint?dep=${department.department_id}" var="addr"/>
                    <form:form modelAttribute="workPosition" action="${addr}" method="POST">
                        <form:input type = "hidden" path = "position_id" value = "${vacancy.position_id}"/>
                        <form:input type = "hidden" path = "postype_id" value = "${vacancy.postype_id.postype_id}"/>
                        <form:input type = "hidden" path = "work_rate" value = "${vacancy.work_rate}"/>
                        <form:input type = "hidden" path = "department_id" value = "${vacancy.department_id.department_id}"/>
                        <td>
                            <form:select path="worker_id">
                                <form:option value="" selected="true" disabled="true">свободно</form:option>
                                <form:options items="${spareWorkerList}" itemValue="worker_id" itemLabel="name"/>
                            </form:select>
                        </td>
                        <td><a href="postype?id=${vacancy.postype_id.postype_id}">
                                ${vacancy.postype_id.postype_name}
                        </a></td>
                        <td>${vacancy.work_rate}</td>
                        <td>${vacancy.postype_id.salary * vacancy.work_rate} руб</td>
                        <td>
                            <input type = "submit" value = "назначить"/>
                            <a href="position_delete?dep=${department.department_id}&id=${vacancy.position_id}">удалить</a>
                        </td>
                    </form:form>
                </tr>
            </c:forEach>
            <tr>
                <c:url value="/position_add?dep=${department.department_id}" var="addr"/>
                <form:form modelAttribute="newPos" action="${addr}" method="POST">
                    <form:input type = "hidden" path = "department_id" value = "${department.department_id}"/>
                    <td>
                        <form:select path="worker_id">
                            <form:option value="" selected="true">свободно</form:option>
                            <form:options items="${spareWorkerList}" itemValue="worker_id" itemLabel="name"/>
                        </form:select>
                    </td>
                    <td>
                        <form:select path="postype_id">
                            <form:option value="" selected="true" disabled="true">выберите должность</form:option>
                            <form:options items="${postypeList}" itemValue="postype_id" itemLabel="postype_name"/>
                        </form:select>
                    </td>
                    <td><form:input type = "number" min="0.25" max="1.0" step="0.25" path = "work_rate" value = "1.0"/></td>
                    <td></td>
                    <td><input type = "submit" value = "добавить"/></td>
                </form:form>
            </tr>
        </table>
    </div>
    <div>
        <a href="department_edit?id=${department.department_id}">
            Изменить подразделение
        </a>
    </div>
    <div>
        <a href="department_delete?id=${department.department_id}">
            Удалить подразделение
        </a>
    </div>
</main>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>