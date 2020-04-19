<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>

<a href="department?id=${department.department_id}">
    ${department.department_name}
</a>

<ul>
    <c:forEach var="department" items="${department.child_departments}">
        <li>
            <c:set var="department" value="${department}" scope="request"/>
            <jsp:include page="departmentNode.jsp"/>
        </li>
    </c:forEach>
</ul>