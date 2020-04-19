<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<ul>
    <c:forEach var="department" items="${departmentList}">
        <li>
            <c:set var="department" value="${department}" scope="request"/>
            <jsp:include page="departmentNode.jsp"/>
        </li>
    </c:forEach>
</ul>
<a href="department_add">
    <div>Добавить подразделение</div>
</a>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>
