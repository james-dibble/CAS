<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-6">
        <form class="form-inline" role="form" action="<c:url value='/redirect' />" method="POST">
            <div class="input-group">
                <input type="text" class="form-control" name="siteAddress" />
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="submit">Check</button>
                </span>
            </div>
        </form>  
    </div>
    <div class="col-lg-6">
        <a class="btn btn-primary btn-block" href="<c:url value='/redirect/download' />">Download Something</a>
    </div>
</div>