<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
    <script src='webjars/jquery/jquery.min.js'></script>

</head>
<body>
<div class="container">
    <br>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <strong>Error!</strong> ${error}
        </div>
    </c:if>
    <h2>Work Log</h2>
    <form:form method="POST" action="/{businessTag}/logWork" modelAttribute="workLog">
        <div class="form-group">
            <form:label path="startTime">Start Time</form:label>
            <div class="input-group date datetimepicker">
                <form:input type="date" path="startTime" class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
            <form:label path="endTime">End Time</form:label>
            <div class="input-group date datetimepicker">
                <form:input type="date" path="endTime" class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <div class="form-group">
            <form:label path="description">Description</form:label>
            <form:textarea path="description" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>
<script>
    $('.datetimepicker').datetimepicker({
      format: '${BOOTSTRAP_DATETIMEPICKER_FORMAT}'
    });
</script>
</body>
</html>