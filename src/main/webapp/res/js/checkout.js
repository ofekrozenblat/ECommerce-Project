window.onload = function() {
  const form = document.querySelector('#checkout-form');

	form.addEventListener('submit', function(event) {
		event.preventDefault();
		placeOrder();
	});
};


function placeOrder() {
	let address = "Checkout"
	let data = getFormData();
	
	let jsonData = data[0];
	let urlData = data[1];
	
	let validaited = validateCreditCard(jsonData["cc-number"], jsonData["cc-cvv"], jsonData["cc-expiration"]);
	if(validaited == true){
		ajaxPOST(address, urlData, function(response) {
		if (response.getResponseHeader("success")) {
			//placeorder
		} else {
			var modal = document.getElementById('askToLogin');
			var bootstrapModal = new bootstrap.Modal(modal);
			bootstrapModal.show();
		}
		});
	}else{
		unsuccessfull(validaited);
	}

}

function unsuccessfull(message){
	let submit_error = document.getElementById("submit-error");
	
	let submit_error_copy = submit_error.cloneNode(true);
	submit_error_copy.classList.remove("hide");
	submit_error_copy.classList.add("show");
	submit_error_copy.id = "submit-error-copy";
	submit_error_copy.querySelector("span").innerHTML = message;
	submit_error.after(submit_error_copy);
}

function getFormData(){
	const form = document.getElementById('checkout-form');
	const formData = new FormData(form);
	
	let jsonData = {};
	let urlData = "";
	
	for (const pair of formData.entries()) {
	  // append pair to URL parameters object
	  jsonData[pair[0]] = pair[1];
	  urlData += `${pair[0]}=${pair[1]}&`
	}
	
	urlData = urlData.substring(0, urlData.length - 1)
	return [jsonData, urlData];
}

function validateCreditCard(ccNumber, ccCVV, ccExpiration) {
  // Remove any whitespace from the credit card number
  ccNumber = ccNumber.replace(/\s+/g, '');

  // Validate the credit card number
  if (!/^\d{13,19}$/.test(ccNumber)) {
    return "Invalid Credit Card Number";
  }

  // Validate the CVV code
  if (!/^\d{3,4}$/.test(ccCVV)) {
    return "Invalid Credit Card CVV";
  }

  // Validate the expiration date
  const today = new Date();
  const expirationDate = new Date(ccExpiration);
  if (expirationDate <= today) {
    return "Expired Credit Card";
  }

  // If all validations pass, return true
  return true;
}