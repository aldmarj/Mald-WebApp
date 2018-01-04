<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>EMS - Dashboard</title>
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
                    var infowindow = new google.maps.InfoWindow({

                        content: '<h1 id="firstHeading" class="firstHeading">' + '<c:out value="${clients.getClientName()}"/>' + '</h1>' + '<c:out value="${clients.getLocations().get(0).getDescription()}"/>'
                    });
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location,
                        title: '<c:out value="${clients.getClientName()}"/>',
                        optimized: false
                    });
                    marker.addListener('click', function () {
                        infowindow.open(map, marker);

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
            <a class="navbar-brand" href="#">EMS - Dashboard</a>
        </div>
    </div><!-- /.container-fluid -->

</nav>


<div class="container" style="margin-top: 80px">

    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                	<div class="row">
                        <div class="col-md-6">
                    		<h2 class="panel-title">Top Employees</h2>  
                    	</div>              
		                <div class="col-md-6">
		                	<a type="button" class="btn btn-success pull-right" href="/${businessTag}/addEmployee">New Employee</a>
		                </div>
		        	</div>
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
        <div class="col-md-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6">
                            <h2 class="panel-title">Clients</h2>
                        </div>
                        <div class="col-md-6">
                            <a type="button" class="btn btn-success pull-right" href="/${businessTag}/addClient">New Client</a>
                        </div>
                    </div>
                </div>
                <div class="panel-body mypanel">

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
                    <div class="row">
                        <div class="col-md-6">
                            <h2 class="panel-title">Work Log</h2>
                        </div>
                        <div class="col-md-6">
                            <a type="button" class="btn btn-success pull-right" href="/${businessTag}/logWork">New Work Entry</a>
                        </div>
                    </div>
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
                                    <c:forEach items="${clients}" var="client">
                                        <c:set var="clientId" scope="session" value="${worklog.getClientId()}"/>
                                        <c:set var="clientName" scope="session" value="${client.getClientId()}"/>
                                        <c:if test="${clientId == clientName}">
                                            <c:out value="${client.getClientName()}"/>
                                        </c:if>
                                    </c:forEach>
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


</body>
</html>
