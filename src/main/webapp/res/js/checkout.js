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
	
	let validaited = validateInput(jsonData["cc-number"], jsonData["cc-cvv"], jsonData["cc-expiration"], jsonData["postal_code"]);
	
	if(validaited == true){
		
		let submit_order_btn = document.getElementById("submit-order")
		
		submit_order_btn.innerHTML = `
		<span class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
					Placing your order...
		`;
		submit_order_btn.setAttribute("disabled", "");
		
		ajaxPOST(address, urlData, function(response) {
			if (response.getResponseHeader("success")) {
				window.location.href = "Checkout?orderSuccess=true"
			} 
			else{
				// order failed to process in backend
				unsuccessfull("Something went wrong! Please try again later.");
				submit_order_btn.innerHTML = 'Place order';
				submit_order_btn.removeAttribute("disabled");
			}
		});
	}else{
		unsuccessfull(validaited);
	}

}

function unsuccessfull(message){
	let error = document.getElementById("submit-error-copy");
	if(error){
		error.remove();
	}
	
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

function validateInput(ccNumber, ccCVV, ccExpiration, postal_code) {
  const caPostalCodeRegex = /^[A-Za-z]\d[A-Za-z][- ]?\d[A-Za-z]\d$/;
  
  //Validate the postal code
  if(!caPostalCodeRegex.test(postal_code)){
	  return "Invalid Postal Code";
  }
  
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
  if (!validateExpirationDate(ccExpiration)) {
    return "Invalid or Expired Credit Card Expiration";
  }

  // If all validations pass, return true
  return true;
}

function validateExpirationDate(expirationDate) {
  const [month, year] = expirationDate.split('/');
  const expirationMonth = parseInt(month, 10);
  const expirationYear = parseInt(year, 10) + 2000;
  
  // Check if the date is in the future
  const now = new Date();
  const currentYear = now.getFullYear();
  const currentMonth = now.getMonth() + 1;
  
  if (expirationYear < currentYear || (expirationYear === currentYear && expirationMonth < currentMonth)) {
    return false;
  }
  
  // Check if the month is valid
  if (expirationMonth < 1 || expirationMonth > 12) {
    return false;
  }
  
  // The date is valid
  return true;
}