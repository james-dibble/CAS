<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Home">
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
        </style>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form">
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
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="text/javascript" src="http://twitter.github.io/typeahead.js/js/hogan-2.0.0.js"></script>
        <script type="text/javascript" src="http://twitter.github.io/typeahead.js/releases/latest/typeahead.js"></script>
        <script type="text/javascript" src="http://asset.jdibble.co.uk/script/form.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#inputClient').typeahead([
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
                                        value: client._name,
                                        tokens: [client._name]
                                    });
                                });

                                return options;
                            }
                        },
                        template: '<p><strong>{{name}}</strong></p>',
                        engine: Hogan
                    }
                ]);

                $('#inputItem').typeahead([
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
                                        value: client._name,
                                        tokens: [client._name]
                                    });
                                });

                                return options;
                            }
                        },
                        template: '<p><strong>{{name}}</strong></p>',
                        engine: Hogan
                    }
                ]);
            });
        </script>
    </jsp:attribute>
</t:simple_layout>