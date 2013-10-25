<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="alert alert-danger">
    <strong>Uhoh!</strong> That page didn't work. <a class="btn btn-primary" href="<c:url value='/redirect' />">Go back</a>
</div>