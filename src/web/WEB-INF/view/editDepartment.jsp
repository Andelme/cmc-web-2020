<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <c:choose>
        <c:when test = "${department.department_id == null}">
            <title>Добавить подразделение</title>
        </c:when>
        <c:otherwise>
            <title>Изменить подразделение</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>

<form:form modelAttribute="department"
           method="POST">
    <form:input type = "hidden" path = "department_id" value = "${department.department_id}"/>
    <div>
        <label for="department_name">Название подразделения</label>
        <form:input type="text" path="department_name" id="department_name" value="${department.department_name}"/>
    </div>
    <div>
        <label for="head_department">Головное подразделение</label>
        <form:select path="head_department" id="head_department">
            <option value="">нет</option>
            <c:forEach var="departmentVar" items="${departmentList}">
                <c:choose>
                    <c:when test = "${department.head_department.department_id == departmentVar.department_id}">
                        <form:option value="${departmentVar.department_id}" selected="true">${departmentVar.department_name}</form:option>
                    </c:when>
                    <c:when test = "${department.department_id != departmentVar.department_id}">
                        <form:option value="${departmentVar.department_id}">${departmentVar.department_name}</form:option>
                    </c:when>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div>
    <c:choose>
        <c:when test = "${department.department_id == null}">
            <input type = "submit" value = "добавить"/>
            <a href = "<c:url value='/'/>"}>отмена</a>
        </c:when>
        <c:otherwise>
            <input type = "submit" value = "обновить"/>
            <a href = "department?id=${department.department_id}"}>отмена</a>
        </c:otherwise>
    </c:choose>
    </div>
</form:form>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>
