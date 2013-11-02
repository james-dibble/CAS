<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value='/' />">Joan's Tea Shop</a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li><a href="<c:url value='/' />">Home</a></li>
            <li><a href="<c:url value='/clients' />">Clients</a></li>
            <li><a href="<c:url value='/items' />">Items</a></li>
            <li><a href="<c:url value='/orders' />">Orders</a></li>
        </ul>
    </div>
</nav>