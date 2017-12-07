<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css'>
    <script src='${pageContext.request.contextPath}/webjars/jquery/jquery.js'></script>
    <style rel="stylesheet">
        #name-group {
            width: 50%;
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
    <h2>Client</h2>
    <form:form method="POST" action="/${businessTag}/addClient" modelAttribute="client">
        <div id="name-group" class="form-group">
            <form:label path="clientName">Name</form:label>
            <form:input path="clientName" placeholder="Enter a name..." class="form-control"/>
        </div>
        <form:errors path="clientName" cssClass="alert alert-danger" element="div"/>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>
</body>
</html>