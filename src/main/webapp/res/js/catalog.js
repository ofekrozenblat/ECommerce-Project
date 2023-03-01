function loadMore() {
	let address = "Catalog"
	let data = "loadMore=true"

	let catalog_list = document.getElementById('catalog_list');
	let load_more_button = document.getElementById('load_more');
	showLoading(catalog_list)

	ajaxGET(address, data, function(response) {

		if (response.getResponseHeader("Loaded-All")) {
			load_more_button.style.display = "none";
		}

		catalog_list.innerHTML += response.responseText;
		doneLoading()
	})
}