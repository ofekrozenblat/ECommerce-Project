window.onload = function() {
  const form = document.querySelector('#checkout-form');

	form.addEventListener('submit', function(event) {
		event.preventDefault();
		placeOrder();
	});
};


function placeOrder() {
	let address = "Checkout"
	let data = "";

	ajaxPOST(address, data, function(response) {
		if (response.getResponseHeader("success")) {
			//placeorder
		} else {
			var modal = document.getElementById('askToLogin');
			var bootstrapModal = new bootstrap.Modal(modal);
			bootstrapModal.show();
		}


	})
}