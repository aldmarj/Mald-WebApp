<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css'>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/Eonasdan-bootstrap-datetimepicker/css/bootstrap-datetimepicker.css'>
    <script src='${pageContext.request.contextPath}/webjars/jquery/jquery.js'></script>
    <script src='${pageContext.request.contextPath}/webjars/momentjs/moment.js'></script>
    <script src='${pageContext.request.contextPath}/webjars/Eonasdan-bootstrap-datetimepicker/js/bootstrap-datetimepicker.js'></script>
    <style rel="stylesheet">
        .datetimepicker {
            width: 200px;
        }
        #description-group {
            width: 50%;
        }
        #client-group {
            width: 30%;
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
    <h2>Work Log</h2>
    <form:form method="POST" action="/${businessTag}/logWork" modelAttribute="workLog">
        <div class="form-group">
            <form:label path="startTime">Start Time</form:label>
            <div class="input-group date datetimepicker">
                <form:input type="text" path="startTime" class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <div class="form-group">
            <form:label path="endTime">End Time</form:label>
            <div class="input-group date datetimepicker">
                <form:input type="text" path="endTime" class="form-control"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <div id="description-group" class="form-group">
            <form:label path="description">Description</form:label>
            <form:textarea path="description" rows="5" placeholder="Enter a description..." class="form-control"/>
        </div>
        <div id="client-group" class="form-group">
            <form:label path="clientId">Client</form:label>
            <form:select path="clientId" class="form-control">
                <c:forEach var="client" items="${clients}">
                    <form:option value="${client.clientId}">${client.clientName}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>
<script>
    $('.datetimepicker').datetimepicker({
        format: '${MOMENTJS_DATE_FORMAT}'
    });
</script>
</body>
</html>