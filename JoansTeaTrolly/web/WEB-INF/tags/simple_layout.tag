<!DOCTYPE html>
<%@tag description="Simple Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="script" fragment="true" %>
<%@attribute name="style" fragment="true" %>
<%@attribute name="content" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Joan's Tea Shop - ${title}</title>
        <!-- LE META -->
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <jsp:invoke fragment="style" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/Views/Shared/Header.jsp" />
            <div class="row">&nbsp;</div>
            <jsp:invoke fragment="content" />
        </div>
    </body>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <jsp:invoke fragment="script" />
</html>