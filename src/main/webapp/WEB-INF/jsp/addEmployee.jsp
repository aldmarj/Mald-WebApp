<%--
  User: Lawrence
  Date: 2/1/2017
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	<script src='webjars/jquery/jquery.min.js'></script>
	<style>
	#formGroup {
		width: 50%;
	}
	.submitButton {
		float: right;
	}
	</style>
	
	<title>Add an Employee</title>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="/${businessTag}">Dashboard</a></li>
    <li class="active">Add New Employee</li>
</ol>

<div class="container" id="formGroup">
    <c:if test="${not empty error}">
		<div class="alert alert-danger">
		  <strong>Error!</strong> ${error}
		</div>
    </c:if>   
	    
	<h2>Employee Management System</h2>
	    
	<p>Add another employee:</p>
	<form:form method="POST" action="/${businessTag}/addEmployee" modelAttribute="employee">
		<div class="form-group">
			<form:label path="account.userName">User Name</form:label>
			<form:input path="account.userName" class="form-control" />
		</div>
		<div class="form-group">
			<form:label path="firstName">First Name</form:label>
			<form:input path="firstName" class="form-control" />
		</div>
		<div class="form-group">
			<form:label path="surName">Sur Name</form:label>
			<form:input path="surName" class="form-control" />
		</div>
		<div class="form-group">
			<form:label path="jobRole">Job Role</form:label>
			<form:input path="jobRole" class="form-control" />
		</div>
		<div class="form-group">
			<form:label path="parentUserName">Manager</form:label>
			<form:input path="parentUserName" class="form-control" />
		</div>
		<div class="form-group">
			<form:label path="requestedPassword">Password</form:label>
			<form:password path="requestedPassword"
				class="form-control" />
		</div>
		<button type="submit" class="btn btn-default submitButton">Submit</button>
	</form:form>
</div>
</body>
</html>
