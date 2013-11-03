<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:simple_layout title="Clients">
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped table-hover">
                    <c:forEach var="item" items="${clients}">
                        <tr>
                            <td>${item.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </jsp:attribute>
</t:simple_layout>
