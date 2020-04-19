<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список работников</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp" %>
<table id="WorkerTable">
    <tr>
        <th>Имя</th>
        <th>Дата найма</th>
        <th>Образование</th>
        <th>Должность</th>
        <th>Подразделение</th>
    </tr>
    <tr>
        <td><input type="text" id="name" onkeyup="FilterFunction(0, id)" placeholder="Поиск по имени..."></td>
        <td><input type="text" id="hireDate" onkeyup="FilterFunction(1, id)" placeholder="Поиск по дате найма..."></td>
        <td><input type="text" id="education_degree" onkeyup="FilterFunction(2, id)" placeholder="Поиск по образованию..."></td>
        <td><input type="text" id="posType" onkeyup="FilterFunction(3, id)" placeholder="Поиск по должности..."></td>
        <td><input type="text" id="department" onkeyup="FilterFunction(4, id)" placeholder="Поиск по подразделению..."></td>
    </tr>
    <c:forEach var="workPosition" items="${workerList}">
        <tr>
            <td><a href="worker?id=${workPosition.worker_id.worker_id}">
                    ${workPosition.worker_id.name}
            </a></td>
            <td><fmt:formatDate value="${workPosition.worker_id.hire_date}" pattern="dd.MM.yyyy г"/></td>
            <td>${workPosition.worker_id.education_degree}</td>
            <td><a href="postype?id=${workPosition.postype_id.postype_id}">
                    ${workPosition.postype_id.postype_name}
            </a></td>
            <td><a href="department?id=${workPosition.department_id.department_id}">
                    ${workPosition.department_id.department_name}
            </a></td>
        </tr>
    </c:forEach>
    <c:forEach var="worker" items="${spareWorkerList}">
        <tr>
            <td><a href="worker?id=${worker.worker_id}">
                    ${worker.name}
            </a></td>
            <td><fmt:formatDate value="${worker.hire_date}" pattern="dd.MM.yyyy г"/></td>
            <td>${worker.education_degree}</td>
            <td>Не работает</td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<a href="worker_add">
    <div>Добавить данные работника</div>
</a>
<%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>

<script>
    function FilterFunction(id, input) {
        var filter, table, tr, td, i, txtValue;
        filter = document.getElementById(input).value.toUpperCase();
        table = document.getElementById("WorkerTable");
        tr = table.getElementsByTagName("tr");
        for (i = 2; i < tr.length; ++i) {
            td = tr[i].getElementsByTagName("td")[id];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
