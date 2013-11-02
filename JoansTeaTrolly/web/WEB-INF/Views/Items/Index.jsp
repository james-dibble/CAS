<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <table class="table table-striped table-hover">
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.name}</td>
                    <td>$${item.price}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
