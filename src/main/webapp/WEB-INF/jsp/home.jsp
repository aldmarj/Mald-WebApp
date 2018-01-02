<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<html>
<head>
	<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	<script src='webjars/jquery/jquery.min.js'></script>
    <style rel="stylesheet">
        .left {
            float: left;
            width: 45%;
        }
        .right {
             margin-left: 50%;
        }
    </style>
</head>
<body>
	<div class="container">
		<br>
	    <c:if test="${not empty error}">
			<div class="alert alert-danger">
			  <strong>Error!</strong> ${error}
			</div>
	    </c:if>       
	
		<h2>Employee Management System</h2>
		<p>Welcome to the Employee Management System! We currently support:</p>

		<table id="business-table" class="table table-striped">
			<thead>
				<tr>
					<th>Business Name</th>
					<th>Business Tag</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${businesses}" var="business">   
					<tr>
						<td>
						   	<c:out value="${business.businessName}" />
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/${business.businessTag}">
								<c:out value="${business.businessTag}" />
							</a>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p>Join us today!</p>
        <form:form method="POST" action="/" modelAttribute="business">
        	<section>
				<div class="left">
				     <p>New Business Details </p>
		             <div class="form-group">
							<form:label path="businessName">Name</form:label>
		                    <form:input path="businessName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="businessTag">Tag</form:label>
		                    <form:input path="businessTag" class="form-control"/>
					 </div>
  			 	</div>
  				<div class="right">
  					<p>Initial User Details</p>
  					 <div class="form-group">
							<form:label path="defaultEmployee.account.userName">User Name</form:label>
		                    <form:input path="defaultEmployee.account.userName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="defaultEmployee.firstName">First Name</form:label>
		                    <form:input path="defaultEmployee.firstName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="defaultEmployee.surName">Sur Name</form:label>
		                    <form:input path="defaultEmployee.surName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="defaultEmployee.jobRole">Job Role</form:label>
		                    <form:input path="defaultEmployee.jobRole" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="defaultEmployee.requestedPassword">Password</form:label>
		                    <form:password path="defaultEmployee.requestedPassword" class="form-control"/>
					 </div>
  				</div>
       		</section>
       		<button type="submit" class="btn btn-default">Submit</button>
        </form:form>  
	</div>
</body>
</html>