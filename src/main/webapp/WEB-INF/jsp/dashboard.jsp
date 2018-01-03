<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Dashboard</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
    <script src='webjars/jquery/jquery.min.js'></script>
    <style>
        #map {
            height: 400px;
            width: 100%;
        }

    </style>
</head>
<body>
<!-- Nav Bar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">EMS - Dashboard</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">Log Work<span class="sr-only">(current)</span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->

    </div>
</nav>


<div class="container" style="margin-top: 80px">
    <!-- Weather Widget

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Weather</h3>
                </div>
                <div class="panel-body">
                    <div id="openweathermap-widget-11"></div>
                </div>
            </div>
        </div>
    </div>-->

    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Top Employee</h3>
                </div>
                <div class="panel-body">
                    <table id="topEmployee-table" class="table table-striped">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Surname</th>
                            <th>Hours Worked</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${topEmployee}" var="topEmployee">
                            <tr>
                                <td>
                                    <c:out value="${topEmployee.getFirstName()}"/>
                                </td>
                                <td>
                                    <c:out value="${topEmployee.getSurName()}"/>
                                </td>
                                <td>
                                    <c:out value="${topEmployee.getHoursWorked()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Work Log</h3>
                </div>
                <div class="panel-body">
                    <table id="worklog-table" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Client ID</th>
                            <th>Description</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${worklog}" var="worklog">
                            <tr>
                                <td>
                                    <c:out value="${worklog.getClientId()}"/>
                                </td>
                                <td>
                                    <c:out value="${worklog.getDescription()}"/>
                                </td>
                                <td>
                                    <c:out value="${worklog.getStartTime()}"/>
                                </td>
                                <td>
                                    <c:out value="${worklog.getEndTime()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Client Location</h3>
                </div>
                <div class="panel-body">
                    <div id="map"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>


    function initMap() {
        var plymouth = {lat: 50.3755, lng: -4.1427 };
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: plymouth
        });
        var marker = new google.maps.Marker({
            position: plymouth,
            map: map
        });
    }

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB94W0BDX-9ozCc44w9fzwKavWncNBcokw&callback=initMap">
</script>


</body>
</html>
