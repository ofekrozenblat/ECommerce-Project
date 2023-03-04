
function editQuantity(item_id, editValue){
	let address = "Cart";
	let data = `editCart=true&item_id=${item_id}&edit_value=${editValue}`;
	ajaxPOST(address, data, function(response){
		if(response.getResponseHeader("Success")){
			window.location.reload();
		}
	});
}

function deleteFromCart(item_id){
	let address = "Cart";
	let data = `deleteFromCart=true&item_id=${item_id}`;
	ajaxPOST(address, data, function(){
		window.location.reload();
	});
}
