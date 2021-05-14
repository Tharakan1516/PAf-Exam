<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>


<div class="container"><div class="row"><div class="col-6">

	<h1>Items Management V10.1</h1>
	
	<form id="formPayment" name="formPayment">
	 Name:
	 <input id="Name" name="Name" type="text"
	 			class="form-control form-control-sm">
	 			
	 <br> Product Name:
	 <input id="ProductName" name="ProductName" type="text"
	 			class="form-control form-control-sm">
	 			
	 <br> Product Id:
	 <input id="ProductId" name="ProductId" type="text"
				 class="form-control form-control-sm">
	 
	 <br> Amount:
	 <input id="Amount" name="Amount" type="text"
	 			class="form-control form-control-sm">
	 
	 <br> Payment Type:
	 <input id="PaymentType" name="PaymentType" type="text"
	 			class="form-control form-control-sm">
		
	 <br> Card Number:
	 <input id="CardNo" name="CardNo" type="text"
	 			class="form-control form-control-sm">
	 			
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save"
	 			class="btn btn-primary">
	 <input type="hidden" id="hidPaymentIdSave"
	 			name="hidPaymentIdSave" value="">
	 			
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="divPaymentsGrid">
		 <%
			 Payment Obj = new Payment();
			 out.print(Obj.readPayment());
		 %>
	</div>
</div> </div> </div>
</body>
</html>