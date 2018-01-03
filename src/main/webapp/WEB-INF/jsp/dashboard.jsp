<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Dashboard</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
    <script src='webjars/jquery/jquery.min.js'></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB94W0BDX-9ozCc44w9fzwKavWncNBcokw">
    </script>
    <style>
        #map {
            height: 400px;
            width: 100%;
        }

        .mypanel {
            height: 350px;
            overflow-y: auto;
        }

    </style>

    <script>
        var geocoder;
        var map;

        function initialize() {
            geocoder = new google.maps.Geocoder();
            var latlng = new google.maps.LatLng(-34.397, 150.644);
            var mapOptions = {
                zoom: 8,
                center: latlng
            }
            map = new google.maps.Map(document.getElementById('map'), mapOptions);

            codeAddress();
        }

        function codeAddress() {
            <c:forEach items="${clients}" var="clients">
            <c:set var="size" scope="session" value="${clients.getLocations().size()}"/>
            <c:if test="${size != 0}">
            var address = '<c:out value="${clients.getLocations().get(0).getPostCode()}"/>';
            geocoder.geocode({'address': address}, function (results, status) {
                if (status == 'OK') {
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });

            </c:if>
            </c:forEach>
        }
    </script>


</head>
<body onload="initialize()">
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

    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Top Employee</h3>
                </div>
                <div class="panel-body mypanel">
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
                <div class="panel-body mypanel">
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
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Clients</h3>
                </div>
                <div class="panel-body">
                    <table id="client-table" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Client ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Postcode</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${clients}" var="clients">
                            <tr>
                                <td>
                                    <c:out value="${clients.getClientId()}"/>
                                </td>
                                <td>
                                    <c:out value="${clients.getClientName()}"/>
                                </td>
                                <td>
                                    <c:set var="size" scope="session" value="${clients.getLocations().size()}"/>
                                    <c:if test="${size != 0}">
                                        <c:out value="${clients.getLocations().get(0).getDescription()}"/>
                                    </c:if>
                                </td>
                                <td>
                                    <c:set var="size" scope="session" value="${clients.getLocations().size()}"/>
                                    <c:if test="${size != 0}">
                                        <c:out value="${clients.getLocations().get(0).getPostCode()}"/>
                                    </c:if>
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


</body>
</html>
