<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<html>
<head>
<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
<script src='webjars/jquery/2.1.4/jquery.min.js'></script>

</head>
<body>
	<div class="container">
		<h2>Employee Management System</h2>
		<p>Welcome to the Employee Management System, we currently support:</p>

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
		<p>Want your business here to? contact us at 00000 000000 for pricing options.</p>
	</div>
</body>
</html>