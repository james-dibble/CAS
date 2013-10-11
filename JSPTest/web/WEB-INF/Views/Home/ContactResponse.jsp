<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped table-hover">
    <tr>
        <th>Email</th>
        <th>Message</th>
    </tr>
    <tr>
        <td>${contact.email}</td>
        <td>${contact.message}</td>
    </tr>
</table>