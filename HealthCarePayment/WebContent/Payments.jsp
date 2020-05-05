
<%@page import="com.Payment.PaymentRepo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Components/Payment.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">


<title>Payments</title>

<div class="border border-primary">
<h1 class="text-secondary" style="margin-left:5%">Payments</h1>
</head>
<body>
<div>
<form id="formPayments" name="formPayments" method="post" action="Payments.jsp">
<div class="container">
<div class="row">
<div class="col-sm-2"></div>
<div class="col-sm-4">
 <br>Name:
<input id="Name" name="Name" type="text"
 class="form-control form-control-sm">
<br> CardNo:
<input id="CardNo" name="CardNo" type="text"
 class="form-control form-control-sm">
<br> Cvc:
<input id="Cvc" name="Cvc" type="text"
 class="form-control form-control-sm">
 <br> Exp:
<input id="Exp" name="Exp" type="text"
 class="form-control form-control-sm">
 </div>

<div class="col-sm-4">
 <br> AppointmentNo:
<input id="AppointmentNo" name="AppointmentNo" type="text"
 class="form-control form-control-sm">
 <br> Amount:
<input id="Amount" name="Amount" type="text"
 class="form-control form-control-sm">
 <br> PayDate:
<input id="PayDate" name="PayDate" type="date"
 class="form-control form-control-sm">
<br> Email:
<input id="Email" name="Email" type="text"
 class="form-control form-control-sm">
 <br>
 
		
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="PaymentID" name="PaymentID" value="">
<br><br><br>
</div>
<div class="col-sm-2"></div>
</div>
</div>

</form>
</div>
</div>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div class="table-responsive">
		<div id="divItemsGrid" class="table table-striped w-auto">
			<%
				PaymentRepo paymentrepo = new PaymentRepo();
				out.print(paymentrepo.getAllPayments());
			%>
		</div>	
</div>	

</body>
</html>