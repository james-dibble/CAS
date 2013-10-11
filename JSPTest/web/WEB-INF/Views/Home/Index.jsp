<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped table-hover">
    <c:forEach var="thing" items="${tests}">
        <tr>
            <td>${thing.name}</td>
            <td>${thing.age}</td>
        </tr>
    </c:forEach>
</table>