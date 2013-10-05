<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>James Dibble - .Net Web Developer</title>
        <!-- LE META -->
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <table>
            <c:forEach var="thing" items="${tests}">
                <tr>
                    <td>${thing.name}</td>
                    <td>${thing.age}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
