/**
 * 
 */

$(document).ready(function(){
	
	if ($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	
});

// save **********************************************
$(document).on("click", ".btnSave", function(event){
	
	// Clear alerts--------------------- 
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide(); 
	
	//Form validation ******************
	var status = validateformPayments();
	if (status != true){
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	}
	
	//If valid ******************
	//$("#formPayments").submit(); 
	$("#alertSuccess").text("Saved successfully.");
	$("#alertSuccess").show();
	//$("#formPayments")[0].reset();
});


//Update ************************************************
$(document).on("click", ".btnUpdate", function(event){
	
	$("#PaymentID").val($(this).closest("tr").find('#PaymentID').val());
	$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#CardNo").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Cvc").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Exp").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#AppointmentNo").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#Amount").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#PayDate").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#Email").val($(this).closest("tr").find('td:eq(7)').text()); 
	
	
});


// Client - model ******************************************

function validateformPayments(){
	
	//Card holder name
	if ($("#Name").val().trim() == ""){
		return "Enter Name";
	}
	
	//Card number
	if ($("#CardNo").val().trim() == ""){
		return "Enter Card Number";
	}
	
	//is CardNo numerical value
	var tmpCardNo = $("#CardNo").val().trim(); 
	if (!$.isNumeric(tmpCardNo)){
		return "Enter a numerical value for CardNo"; 
	}
	
	
	//Cvc
	if ($("#Cvc").val().trim() == ""){
		return "Enter CVC ";
	}
	
	//is Cvc numerical value
	var tmpCvc = $("#Cvc").val().trim(); 
	if (!$.isNumeric(tmpCvc)){
		return "Enter a numerical value for Cvc"; 
	}
	
	//Expiary date
	if ($("#Exp").val().trim() == ""){
		return "Enter EXP Date";
	}
	
	//AppointmentID
	if ($("#AppointmentNo").val().trim() == ""){
		return "Enter Appointment ID";
	}
	
	//is AppointmentNo numerical value
	var tmpAppointmentNo = $("#AppointmentNo").val().trim(); 
	if (!$.isNumeric(tmpAppointmentNo)){
		return "Enter a numerical value for AppointmentNo"; 
	}
	
	//Amount
	if ($("#Amount").val().trim() == ""){
		return "Enter Amount";
	}
	
	//is Amount numerical value
	var tmpAmount = $("#Amount").val().trim(); 
	if (!$.isNumeric(tmpAmount)){
		return "Enter a numerical value for Amount"; 
	}
	
	//Payment date
	if ($("#PayDate").val().trim() == ""){
		return "Enter Pay Date";
	}
	
	//Email
	if ($("#Email").val().trim() == ""){
		return "Enter Email";
	}
	
		
	return true;
}


$(document).on("click","#btnSave", function(event){
	
	//Form validation ******************
	var status = validateformPayments();
	if (status != true){
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	}
	
	
	
var type = ($("#PaymentID").val() == "") ? "POST" : "PUT"; 

$.ajax( 
{
	url : "PaymentAPI",
	type : type, 
	data : $("#formPayments").serialize(), 
	dataType : "text",
	complete : function(response, status){
		onPaymentSaveComplete(response.responseText, status); 
	}
	});	

});

function onPaymentSaveComplete(response, status){
	if (status == "success"){
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show();
			
			$("#divItemsGrid").html(resultSet.data);
			
		}else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		}
	}else if (status == "error"){
		
		$("#alertError").text("Error while saving."); 
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#PaymentID").val("");
	//$("#formPayments").submit();
	$("#formPayments")[0].reset();
}



$(document).on("click", ".btnRemove", function(event){
	//event.preventDefault();
	$.ajax(
	{
		url : "PaymentAPI",
		type : "DELETE",
		data : "PaymentID=" + $(this).data("paymentid"),
		dataType : "text",
		complete : function(response, status) {
			onPaymentDeleteComplete(response.responseText, status);
			
		}
	});
});

function onPaymentDeleteComplete(response, status){
	
	if (status == "success"){
		
		var resultSet = JSON.parse(response); 
		
		if (resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			 
			$("#divItemsGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);   
			$("#alertError").show(); 
		} else if (status == "error") {
			$("#alertError").text("Error while deleting.");  
			$("#alertError").show();
		}else{
			$("#alertError").text("Unknown error while deleting.."); 
			$("#alertError").show();
		}
	}
}






