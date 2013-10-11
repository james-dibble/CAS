<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" action="<c:url value='/contact' />" method="POST">
  <div class="form-group">
    <label for="exampleInputEmail">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail" name="email" placeholder="Enter email">
  </div>
  <div class="form-group">
    <label for="exampleInputMessage">Message</label>
    <input type="text" class="form-control" id="exampleInputMessage" name="message" placeholder="Message">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>