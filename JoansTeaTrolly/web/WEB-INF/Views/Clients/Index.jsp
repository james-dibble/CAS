<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Clients">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-inline" role="form" method="POST" action="<c:url value='/clients/addclient' />">
                    <div class="form-group">
                        <label class="sr-only" for="clients">Client Name</label>
                        <input id="inputClient" name="name" data-val="true" data-val-required="Please add a name." class="form-control" type="text" placeholder="Client Name" autocomplete="off" spellcheck="false" dir="auto" />
                        <span class="field-validation-valid help-block" data-valmsg-for="name" data-valmsg-replace="true"></span>
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
                <table class="table table-striped table-hover">
                    <col width="90%">
                    <col width="10%">
                    <c:forEach var="client" items="${model}">
                        <tr>
                            <td>${client.name}</td>
                            <td>
                                <form action="<c:url value="/clients/delete" />" method="POST">
                                    <input type="hidden" value="${client.id}" name="clientId" />
                                    <button class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="text/javascript" src="http://asset.jdibble.co.uk/script/form.js"></script>
    </jsp:attribute>
</t:simple_layout>
