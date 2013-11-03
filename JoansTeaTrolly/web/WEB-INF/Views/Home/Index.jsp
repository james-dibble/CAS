<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Home">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <h2>Joan's Tea Shop</h2>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">Actions</div>
                    <div class="panel-body">
                        <a href="<c:url value='/orders/create' />" class="btn btn-primary">Create Order</a>
                        <a href="<c:url value='/clients' />" class="btn btn-primary">Create User</a>
                        <a href="<c:url value='/items' />" class="btn btn-primary">Edit Items</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">Clients</div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <tr>
                                <th>Name</th>
                            </tr>
                            <c:forEach var="item" items="${clients}">
                                <tr>
                                    <td>${item.name}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">Items</div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                            </tr>
                            <c:forEach var="item" items="${items}">
                                <tr>
                                    <td>${item.name}</td>
                                    <td>$${item.price}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:simple_layout>