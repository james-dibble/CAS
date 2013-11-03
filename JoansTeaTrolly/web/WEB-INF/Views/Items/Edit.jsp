<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Edit Item">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <form class="form-horizontal" role="form" method="POST" action="<c:url value='/items/edit/${item.id}' />">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <input name="name" data-val="true" data-val-required="Please add a name." class="form-control" type="text" placeholder="Client Name" autocomplete="off" spellcheck="false" dir="auto" value="${item.name}" />
                            <span class="field-validation-valid help-block" data-valmsg-for="name" data-valmsg-replace="true"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon">$</span>
                                <input data-val="true" data-val-required="Please add a price." data-val-regex="Please enter a valid price." data-val-regex-pattern="^[0-9]+$" type="text" class="form-control" name="price" placeholder="Price" value="${item.price}" />
                                <span class="input-group-addon">.00</span>
                            </div>
                            <span class="field-validation-valid help-block" data-valmsg-for="price" data-valmsg-replace="true"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="text/javascript" src="http://asset.jdibble.co.uk/script/form.js"></script>
    </jsp:attribute>
</t:simple_layout>

