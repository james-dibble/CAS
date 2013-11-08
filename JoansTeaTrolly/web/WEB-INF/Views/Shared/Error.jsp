<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="OH SNAP">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-danger">
                    <strong>Oh snap!</strong>&nbsp;This page has an error.
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:simple_layout>
