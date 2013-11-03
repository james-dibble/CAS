<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Items">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped table-hover">
                    <col width="45%">
                    <col width="45%">
                    <col width="10%">
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>${item.name}</td>
                            <td>$${item.price}</td>
                            <td>
                                <a href="<c:url value='/items/edit/${item.id}' />" class="btn btn-primary pull-right">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </jsp:attribute>
</t:simple_layout>

