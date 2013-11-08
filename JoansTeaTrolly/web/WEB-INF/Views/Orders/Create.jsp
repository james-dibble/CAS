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

            .tt-suggestions{
                margin-top: 5px;
                border: solid 1px #3276b1;
                background-color: white;
                border-radius: 3px;
            }

            .tt-suggestion{
                margin-top: 3px;
                padding-left: 10px;
                padding-right: 10px;
                padding-top: 4px;
                padding-bottom: 1px;
            }

            .tt-suggestion:hover{
                color: white;
                background-color: #3276b1;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form" method="POST" action="<c:url value='/orders/addtoorder' />">
                    <div class="form-group">
                        <label class="sr-only" for="clients">Client</label>
                        <input id="inputClient" name="client" class="form-control" type="text" placeholder="Client" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="clientId" data-valmsg-replace="true"></span>
                        <input type="hidden" name="clientId" data-val="true" data-val-required="Please add a client." />
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Item</label>
                        <input id="inputItem" name="item" class="form-control" type="text" placeholder="Item" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="itemId" data-valmsg-replace="true"></span>
                        <input type="hidden" name="itemId" data-val="true" data-val-required="Please add an item." />
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Quantity</label>
                        <input id="inputQuantity" name="quantity" data-val="true" data-val-required="Please add a quantity." data-val-regex="Please enter a valid quantity" data-val-regex-pattern="^[0-9]+$" class="form-control" type="text" placeholder="Quantity" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="quantity" data-valmsg-replace="true"></span>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add&nbsp<i class="glyphicon glyphicon-plus"></i></button>
                    </div>                    
                </form>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form" method="POST" action="<c:url value='/orders/saveorders' />">
                    <button type="submit" class="btn btn-success">Save Orders</button>
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
                            <c:set var="total" value="0" />
                            <c:forEach var="client" items="${sessionScope.orders}">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${client.key.name}">
                                            ${client.key.name}
                                        </a>
                                        <form class="pull-right" method="POST" action="<c:url value='/orders/removeordersforclient' />">
                                            <input type="hidden" name="clientId" value="${client.key.id}" />
                                            <button type="submit" class="btn btn-danger">Remove ${client.key.name}'s Orders</button>
                                        </form>
                                    </h4>
                                </div>
                                <div class="panel-collapse collapse" id="${client.key.name}">
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
                                            <c:forEach var="item" items="${client.value}">
                                                <tr>
                                                    <td>${item.item.name}</td>
                                                    <td>${item.quantity}</td>
                                                    <td>
                                                        <form class="pull-right" method="POST" action="<c:url value='/orders/removeorder' />">
                                                            <input type="hidden" name="clientId" value="${client.key.id}" />
                                                            <input type="hidden" name="itemId" value="${item.item.id}" />
                                                            <input type="hidden" name="quantity" value="${item.quantity}" />
                                                            <button type="submit" class="btn btn-danger">Remove Order</button>
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
                            </c:forEach>
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

            $('#inputItem').change(function() {
                $('input[name="itemId"]').attr('value', '');
            });

            $('#inputClient').change(function() {
                $('input[name="clientId"]').attr('value', '');
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