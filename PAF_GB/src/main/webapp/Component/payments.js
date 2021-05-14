$(document).ready(function()
{
	 
	 $("#alertSuccess").hide();
	 
	 $("#alertError").hide();
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear status msges-------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();


	// Form validation----------------
	var status = validatePaymentForm();
	
	// If not valid-------------------
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }

	// If valid------------------------
	var type = ($("#hidPaymentIdSave").val() == "") ? "POST" : "PUT";
	
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
			 $("#hidPaymentIdSave").val("");
			 $("#formPayment")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidPaymentIdSave").val($(this).data("paymentid"));
	 $("#Name").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#ProductName").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#ProductId").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#Amount").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#PaymentType").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#CardNo").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 		$.ajax(
 		{
				 url : "PaymentsAPI",
				 type : "DELETE",
				 data : "Id=" + $(this).data("paymentid"),
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

// CLIENT-MODEL=================================================================
function validateItemForm()
{
	// NAME
	if ($("#Name").val().trim() == "")
	 {
		 return "Insert Customer name.";
	 }
	
	//PRODUCT NAME
	if ($("#ProductName").val().trim() == "")
	 {
		 return "Insert Product name.";
	 }
	
	// PRODUCT ID
	if ($("#ProductId").val().trim() == "")
	 {
		 return "Insert Product Id.";
	 }
	
	// AMOUNT
	if ($("#Amount").val().trim() == "")
	 {
		 return "Insert Amount.";
	 }
	
	// PAYMENT TYPE
	if ($("#PaymentType").val() == "0")
	 {
		 return "Select Type.";
	 }

	// CARD NO
	if ($("#CardNo").val().trim() == "")
	 {
		 return "Insert Card Number.";
	 }

		
			// is numerical value
	var tmpPrice = $("#Amount").val().trim();
	if (!$.isNumeric(tmpPrice))
	 {
	 return "Insert a numerical value for Amount.";
	 }
	// convert to decimal price
	 $("#Amount").val(parseFloat(tmpPrice).toFixed(2));


	return true;
}
