<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
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
		<p>Want to add yourself?</p>
        <form:form method="POST" action="/businesses" modelAttribute="business">
             <table>
                <tr>
                    <td><form:label path="businessName">Name</form:label></td>
                    <td><form:input path="businessName"/></td>
                </tr>
                <tr>
                    <td><form:label path="businessTag">Tag</form:label></td>
                    <td><form:input path="businessTag"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
	</div>
</body>
</html>