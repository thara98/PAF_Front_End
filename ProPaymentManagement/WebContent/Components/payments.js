/**
 * 
 */
$(document).ready(function()
{

 
 $("#alertSuccess").hide();
 $("#alertError").hide();

});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validatePaymentForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentsAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentSaveComplete(response.responseText, status);
 }
 });
});


function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }
$("#hidPaymentIDSave").val("");
 $("#formPayment")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
 $("#paymentCardNo ").val($(this).closest("tr").find('td:eq(0)').text());
 $("#paymentCvv ").val($(this).closest("tr").find('td:eq(1)').text());
 $("#paymentExpireDate ").val($(this).closest("tr").find('td:eq(2)').text());
 $("#paymentCardholderName ").val($(this).closest("tr").find('td:eq(3)').text());
});




// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "PaymentsAPI",
 type : "DELETE",
 data : "paymentID=" + $(this).data("paymentid"),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentDeleteComplete(response.responseText, status);
 }
 });
});

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}






// CLIENT-MODEL================================================================
function validatePaymentForm()
{
// paymentCardNo
if ($("#paymentCardNo ").val().trim() == "")
 {
 return "Insert Payment Card No.";
 }
// paymentCvv
if ($("#paymentCvv ").val().trim() == "")
 {
 return "Insert Payment Cvv.";
 } 
// paymentExpireDate-------------------------------
if ($("#paymentExpireDate ").val().trim() == "")
 {
 return "Insert Payment Expire Date.";
 }

// paymentCardholderName------------------------
if ($("#paymentCardholderName ").val().trim() == "")
 {
 return "Insert Payment Cardholder Name.";
 }
return true;
}