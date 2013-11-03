<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Create Order">
    <jsp:attribute name="style">
        <link rel="stylesheet" href="https://raw.github.com/jharding/typeahead.js-bootstrap.css/master/typeahead.js-bootstrap.css" />
        <style>
            .twitter-typeahead .tt-hint {
                display: block;
                height: 34px;
                padding: 6px 12px;
                font-size: 14px;
                line-height: 1.428571429;
                border: 1px solid transparent;
                border-radius:4px;
            }
            .twitter-typeahead .hint-small {
                height: 30px;
                padding: 5px 10px;
                font-size: 12px;
                border-radius: 3px;
                line-height: 1.5;
            }
            .twitter-typeahead .hint-large {
                height: 45px;
                padding: 10px 16px;
                font-size: 18px;
                border-radius: 6px;
                line-height: 1.33;
            }

            .twitter-typeahead {
                width: 100%;
            }
            .tt-dropdown-menu {
                width: 100%;
            }
            .tt-hint {
                width: 100%;
            }

            .panel-title{
                height: 34px;
            }

            .panel-title a{
                margin-top: 8px;
                position: absolute;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form" method="POST" action="<c:url value='/orders/addtoorder' />">
                    <div class="form-group">
                        <label class="sr-only" for="clients">Client</label>
                        <input id="inputClient" name="client" data-val="true" data-val-required="Please add a client." class="form-control" type="text" placeholder="Client" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="client" data-valmsg-replace="true"></span>
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Item</label>
                        <input id="inputItem" name="item" data-val="true" data-val-required="Please add an item." class="form-control" type="text" placeholder="Item" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="item" data-valmsg-replace="true"></span>
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Quantity</label>
                        <input id="inputQuantity" name="quantity" data-val="true" data-val-required="Please add a quantity." data-val-regex="Please enter a quantity" data-val-regex-pattern="^[0-9]+$" class="form-control" type="text" placeholder="Quantity" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="quantity" data-valmsg-replace="true"></span>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add&nbsp<i class="glyphicon glyphicon-plus"></i></button>
                    </div>
                    <input type="hidden" name="clientId" />
                    <input type="hidden" name="itemId" />
                </form>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form" method="POST" action="<c:url value='/orders/saveorders' />">
                    <button type="submit" class="btn btn-primary">Save Orders</button>
                </form>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${sessionScope.orders == null || sessionScope.orders.size() == 0}">
                        <div class="alert alert-info">
                            No orders yet.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel-group" id="accordion">
                            <c:set var="currentClient" value="${sessionScope.orders[0].client}" />
                            <c:set var="total" value="0" />
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${currentClient.name}">
                                            ${currentClient.name}
                                        </a>
                                        <form class="pull-right" method="POST" action="<c:url value='/orders/removeordersforclient' />">
                                            <input type="hidden" name="clientId" value="${currentClient.id}" />
                                            <button type="submit" class="btn btn-primary">Remove ${currentClient.name}'s Orders</button>
                                        </form>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse" id="${currentClient.name}">
                                    <div class="panel-body">
                                        <table class="table table-striped">
                                            <col width="40%">
                                            <col width="40%">
                                            <col width="20%">
                                            <tr>
                                                <th>Item</th>
                                                <th>Quantity</th>
                                                <th></th>
                                            </tr>
                                            <c:forEach var="item" items="${sessionScope.orders}">
                                                <c:if test="${item.client.id != currentClient.id}">
                                                    <c:set var="currentClient" value="${item.client}" />
                                                    <tr>
                                                        <td></td>
                                                        <td><strong>Total:</strong> $${total}</td>
                                                        <td>
                                                        </td>
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
                                                <form class="pull-right" method="POST" action="<c:url value='/orders/removeordersforclient' />">
                                                    <input type="hidden" name="clientId" value="${item.client.id}" />
                                                    <button type="submit" class="btn btn-primary">Remove ${item.client.name}'s Orders</button>
                                                </form>
                                            </h4>
                                        </div>
                                        <div class="panel-collapse collapse" id="${item.client.name}">
                                            <div class="panel-body">
                                                <table class="table table-striped">
                                                    <col width="40%">
                                                    <col width="40%">
                                                    <col width="20%">
                                                    <tr>
                                                        <th>Item</th>
                                                        <th>Quantity</th>
                                                        <th></th>
                                                    </tr>
                                                </c:if>

                                                <tr>
                                                    <td>${item.item.name}</td>
                                                    <td>${item.quantity}</td>
                                                    <td>
                                                        <form class="pull-right" method="POST" action="<c:url value='/orders/removeorder' />">
                                                            <input type="hidden" name="clientId" value="${currentClient.id}" />
                                                            <input type="hidden" name="itemId" value="${item.item.id}" />
                                                            <input type="hidden" name="quantity" value="${item.quantity}" />
                                                            <button type="submit" class="btn btn-primary">Remove Order</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <c:set var="total" value="${total + (item.item.price * item.quantity)}" />
                                            </c:forEach>
                                            <tr>
                                                <td></td>
                                                <td><strong>Total:</strong> $${total}</td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://twitter.github.io/typeahead.js/js/hogan-2.0.0.js"></script>
        <script type="text/javascript" src="http://twitter.github.io/typeahead.js/releases/latest/typeahead.js"></script>
        <script type="text/javascript" src="http://asset.jdibble.co.uk/script/form.js"></script>
        <script type="text/javascript">
            $(function() {
                var clientTypeahead = $('#inputClient').typeahead([
                    {
                        name: 'clients',
                        prefetch: {
                            url: '<c:url value="/clients/getallclients" />',
                            ttl: 1,
                            filter: function(data) {
                                var options = [];

                                $(data).each(function(i, client) {
                                    options.push({
                                        name: client._name,
                                        id: client._id,
                                        value: client._name,
                                        tokens: [client._name]
                                    });
                                });

                                return options;
                            }
                        },
                        template: '<p><strong>{{name}}</strong></p>', engine: Hogan
                    }
                ]);
                var itemTypeahead = $('#inputItem').typeahead([
                    {
                        name: 'items',
                        prefetch: {
                            url: '<c:url value="/items/getallitems" />',
                            ttl: 1,
                            filter: function(data) {
                                var options = [];

                                $(data).each(function(i, client) {
                                    options.push({
                                        name: client._name,
                                        id: client._id,
                                        value: client._name,
                                        tokens: [client._name]
                                    });
                                });

                                return options;
                            }},
                        template: '<p><strong>{{name}}</strong></p>',
                        engine: Hogan
                    }
                ]);
            });

            $('#inputItem').bind('typeahead:selected', function(obj, datum, name) {
                $('input[name="itemId"]').attr('value', datum.id);
            });

            $('#inputClient').bind('typeahead:selected', function(obj, datum, name) {
                $('input[name="clientId"]').attr('value', datum.id);
            });

            $('#inputItem').bind('typeahead:autocompleted', function(obj, datum, name) {
                $('input[name="itemId"]').attr('value', datum.id);
            });

            $('#inputClient').bind('typeahead:autocompleted', function(obj, datum, name) {
                $('input[name="clientId"]').attr('value', datum.id);
            });
        </script>
    </jsp:attribute>
</t:simple_layout>