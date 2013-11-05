<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Orders">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel-group" id="accordion">
                    <c:set var="currentClient" value="${model[0].client}" />
                    <c:set var="total" value="0" />
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#${currentClient.id}">
                                    ${currentClient.name}
                                </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse" id="${currentClient.id}">
                            <div class="panel-body">
                                <table class="table table-striped">
                                    <col width="50%">
                                    <col width="50%">
                                    <tr>
                                        <th>Item</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <c:forEach var="item" items="${model}">
                                        <c:if test="${item.client.id != currentClient.id}">
                                            <c:set var="currentClient" value="${item.client}" />
                                            <tr>
                                                <td></td>
                                                <td><strong>Total:</strong> $${total}</td>
                                            </tr>
                                            <c:set var="total" value="0" />
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${item.client.name}">
                                            ${item.client.name}
                                        </a>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse" id="${item.client.name}">
                                    <div class="panel-body">
                                        <table class="table table-striped">
                                            <col width="50%">
                                            <col width="50%">
                                            <tr>
                                                <th>Item</th>
                                                <th>Quantity</th>
                                            </tr>
                                        </c:if>

                                        <tr>
                                            <td>${item.item.name}</td>
                                            <td>${item.quantity}</td>
                                        </tr>
                                        <c:set var="total" value="${total + (item.item.price * item.quantity)}" />
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td><strong>Total:</strong> $${total}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
    </jsp:attribute>
</t:simple_layout>