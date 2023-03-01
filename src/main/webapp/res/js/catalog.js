

function loadMore(){
	let address = "Catalog"
	let data = "loadMore=true"
	
	let catalog_list = document.getElementById('catalog_list');
	let load_more_button = document.getElementById('load_more');
	ajaxGET(address, data, function(response){
		if(response.length == 2){
			load_more_button.style.display = "none";
		}else{
			catalog_list.innerHTML += response;
		}
	})
}