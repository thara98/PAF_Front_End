<%@page import="com.Payment"%>
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
<h1>Payments Management</h1>
<form id="formPayment" name="formPayment">
 Payment card no:
 <input id="paymentCardNo" name="paymentCardNo" type="text"
 class="form-control form-control-sm">
 <br> Payment cvv:
 <input id="paymentCvv" name="paymentCvv" type="text"
 class="form-control form-control-sm">
 <br> Payment expire date:
 <input id="paymentExpireDate" name="paymentExpireDate" type="text"
 class="form-control form-control-sm">
 <br> Payment cardholder name:
 <input id="paymentCardholderName" name="paymentCardholderName" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidPaymentIDSave"
 name="hidPaymentIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPaymentsGrid">
 <%
 Payment paymentObj = new Payment();
 out.print(paymentObj.readPayments());
 %>
</div>
</div> </div> </div>
</body>
</html>