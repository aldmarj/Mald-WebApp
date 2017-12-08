<%--
  Created by IntelliJ IDEA.
  User: Dom
  Date: 24/10/2017
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>login</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	<script src='/webjars/jquery/jquery.min.js'></script>
</head>
<body>
	<div class="container">
		<br>
	    <c:if test="${not empty error}">
			<div class="alert alert-danger">
			  <strong>Error!</strong> ${error}
			</div>
	    </c:if>  
	    
		<p>Login</p>
        <form method="POST" action="/${businessTag}/login">
            <div class="input-group input-sm">
                  <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                  <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
              </div>
              <div class="input-group input-sm">
                  <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                  <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
              </div>
              <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                   
              <div class="form-actions">
                  <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
              </div>
        </form> 
	</div>
</body>
</html>
