<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<html>
<head>
	<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	<script src='webjars/jquery/jquery.min.js'></script>
    <style>
        .left {
            float: left;
            width: 45%;
        }
        .right {
             margin-left: 50%;
        }
        .submitButton {
		float: right;
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
	
		<h2>Hi David & Andy! I made this change for the demo</h2>
		
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
							<form:label path="initialEmployee.account.userName">User Name</form:label>
		                    <form:input path="initialEmployee.account.userName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="initialEmployee.firstName">First Name</form:label>
		                    <form:input path="initialEmployee.firstName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="initialEmployee.surName">Sur Name</form:label>
		                    <form:input path="initialEmployee.surName" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="initialEmployee.jobRole">Job Role</form:label>
		                    <form:input path="initialEmployee.jobRole" class="form-control"/>
					 </div>
					 <div class="form-group">
		                    <form:label path="initialEmployee.requestedPassword">Password</form:label>
		                    <form:password path="initialEmployee.requestedPassword" class="form-control"/>
					 </div>
  				</div>
       		</section>
       		<button type="submit" class="btn btn-default submitButton">Submit</button>
        </form:form>  
	
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
	</div>
</body>
</html>