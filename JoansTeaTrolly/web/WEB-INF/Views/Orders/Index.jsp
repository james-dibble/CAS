<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Orders">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${model == null || model.size() == 0}">
                        <div class="alert alert-info">
                            No orders yet.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel-group" id="accordion">
                            <c:forEach var="client" items="${model}">
                                <c:choose>
                                    <c:when test="${client.key.name == null}">
                                        <c:set var="clientName" value="Ex-Clients" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="clientName" value="${client.key.name}" />
                                    </c:otherwise>
                                </c:choose>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#${clientName}">
                                                ${clientName}
                                            </a>
                                            <form class="pull-right" method="POST" action="<c:url value='/orders/removeordersforclient' />">
                                                <input type="hidden" name="clientId" value="${client.key.id}" />
                                            </form>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="${clientName}">
                                        <div class="panel-body">
                                            <table class="table table-striped">
                                                <col width="40%">
                                                <col width="40%">
                                                <col width="20%">
                                                <tr>
                                                    <th>Item</th>
                                                    <th>Quantity</th>
                                                </tr>
                                                <c:forEach var="item" items="${client.value}">
                                                    <tr>
                                                        <td>${item.item.name}</td>
                                                        <td>${item.quantity}</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td></td>
                                                    <td><strong>Total:</strong> $${model.GetTotalForClient(client.key)}</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
    </jsp:attribute>
</t:simple_layout>