<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
<script src='webjars/jquery/2.1.4/jquery.min.js'></script>

</head>
<body>
	<div class="container">
		<h2>Employee Management System</h2>
		<p>Welcome to the Employee Management System, we currently
			support:</p>

		<div>
			<table id="business-table" class="table table-striped">
				<thead>
					<tr>
						<th>Business Name</th>
						<th>Business Tag</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<script>
		//json from the servlet
		var businesses = 
			JSON.parse('[{"businessName":"baesystems","businessTag":"bae"},{"businessName":"tbusinessName","businessTag":"ttag"}]');

		$(document).ready(function() {
				var table = $('#business-table');
				
				$(businesses).each(function(i, business) {
					$('<tr/>').appendTo(table)
						.append($('<td/>').text(business.businessName))
							.append($('<td/>').html(
									"<a href=" + window.location.href + "/" + business.businessTag + ">"
										+ business.businessTag + "</a>"));
			});
		});
	</script>
</body>
</html>