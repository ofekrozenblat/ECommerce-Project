window.onload = function(){
	setFilterRatingFunctionality();
}

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

function setCatalogFiltersFunctionality(){
	
}

function setFilterRatingFunctionality() {
	let review_rating = document.getElementById("filter-rating");
	review_rating.setAttribute('value', 0);

	let stars_div = review_rating.children[0];
	let len = stars_div.children.length

	stars_div.addEventListener("mouseleave", mouseleave = function() {
		for (let j = 0; j < len; j++) {
			stars_div.children[j].classList.remove("material-icons");
			stars_div.children[j].classList.add("material-icons-outlined");
		}
	});


	for (let i = 0; i < len; i++) {

		const child = stars_div.children[i];
		child.classList.add("cursor-pointer");

		child.addEventListener("mouseover", function() {
			for (let j = 0; j < len; j++) {

				if (j <= i) {
					stars_div.children[j].classList.remove("material-icons-outlined");
					stars_div.children[j].classList.add("material-icons");
				} else {
					stars_div.children[j].classList.remove("material-icons");
					stars_div.children[j].classList.add("material-icons-outlined");
				}

			}
		});

		child.addEventListener("click", function() {
			stars_div.removeEventListener("mouseleave", mouseleave);
			review_rating.setAttribute('value', i + 1);
			for (let j = 0; j < len; j++) {

				if (j <= i) {
					stars_div.children[j].classList.remove("material-icons-outlined");
					stars_div.children[j].classList.add("material-icons");
				} else {
					stars_div.children[j].classList.remove("material-icons");
					stars_div.children[j].classList.add("material-icons-outlined");
				}

			}
		});
	}
}
