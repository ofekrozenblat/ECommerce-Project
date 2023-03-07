window.onload = function() {
  const form = document.querySelector('#account-information-form');

	form.addEventListener('submit', function(event) {
		event.preventDefault();
		updateAccountInformation();
	});
};

function updateAccountInformation(){
	let address = "Account"
	let data = getFormData();
	
	let urlData = data[1];
	
	let submit_order_btn = document.getElementById("submit-accountinfo")
		
		submit_order_btn.innerHTML = `
		<span class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
					Updating...
		`;
		
		submit_order_btn.setAttribute("disabled", "");
		
		ajaxPOST(address, urlData + "&updateAccountInfo", function(response) {
			if (response.getResponseHeader("success")) {
				window.location.href = "Account?updatedSuccess=true";
			} 
			else{
				// order failed to process in backend
				unsuccessfull();
				submit_order_btn.removeAttribute("disabled");
				submit_order_btn.innerHTML = "Update Information"
			}
		});
}

function getFormData(){
	const form = document.getElementById('account-information-form');
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

function unsuccessfull(){
	let error = document.getElementById("submit-error-copy");
	if(error){
		error.remove();
	}
	
	let submit_error = document.getElementById("submit-error");
	
	let submit_error_copy = submit_error.cloneNode(true);
	submit_error_copy.classList.remove("hide");
	submit_error_copy.classList.add("show");
	submit_error_copy.id = "submit-error-copy";
	submit_error.after(submit_error_copy);
}