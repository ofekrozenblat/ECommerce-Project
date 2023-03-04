window.onload = function() {
  const form = document.querySelector('#login-form');

	form.addEventListener('submit', function(event) {
		event.preventDefault();
		
		let error = document.getElementById("submit-error-copy");
		if(error){
			error.remove();
		}
		
		let email = document.getElementById("emailInput").value 
		let password = document.getElementById("passwordInput").value 
		
		let data = `email=${email}&password=${password}`;
		let address = "Login"
		
		ajaxPOST(address, data, function(response){
			if(response.getResponseHeader("success")){
				window.location.href = "Home";
			}else{
				unsuccessfull();
			}
		});
	});
};


function unsuccessfull(){
	let submit_error = document.getElementById("submit-error");
	
	let submit_error_copy = submit_error.cloneNode(true);
	submit_error_copy.classList.remove("hide");
	submit_error_copy.classList.add("show");
	submit_error_copy.id = "submit-error-copy";
	submit_error.after(submit_error_copy);
}
