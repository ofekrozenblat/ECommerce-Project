let showing = 12;
let max = 0;
let filters = new Proxy(
  { 
    rating: 0,
    brands: [],
    colors: [],
    categories: []
  }, 
  {
    set: function(target, key, value) {
      target[key] = value;
      filterCatalog();
      return true;
    },
    get: function(target, key) {
      return target[key];
    }
  }
);

window.onload = function(){
	setFilterRatingFunctionality();
	loadMore();
}

function loadMore() {

	
	let catalog_lists = document.querySelector(".item-listing");
	let max = catalog_lists.children.length;
	

	const list = Array.from(catalog_lists.children);
		list.forEach(function(item, index){
			if(index < showing){
				item.classList.remove('hide');
			}else{
				item.classList.add('hide');
			}
		});
	
	if(showing >= max){
		let load_more_button = document.getElementById('load_more');
		load_more_button.style.display = "none";
	}
	
	showing += 12;
}

function filterCatalog(){
	
	let load_more_button = document.getElementById('load_more');
	load_more_button.style.display = "none";
	
	let catalog_lists = document.querySelector(".item-listing");
	
	const list = Array.from(catalog_lists.children);

	list.forEach(function(item){
		hide = false;
		
		if(item.getAttribute("data-rating") < filters.rating){
			hide = true;
		}
		
		if(filters.brands.length > 0 && !filters.brands.includes(item.getAttribute("data-brand"))){
			hide = true;
		}
		
		if(filters.colors.length > 0 && !filters.colors.includes(item.getAttribute("data-color"))){
			hide = true;
		}
		
		if(filters.categories.length > 0 && !filters.categories.includes(item.getAttribute("data-category"))){
			hide = true;
		}
		
		if(hide === false){
			item.classList.remove('hide');
		}else{
			item.classList.add('hide');
		}
	});
}

function clearFilters(){
	window.location.reload();
}

function updateFilters(type, value){
	list = filters[type];
	
	if(!list.includes(value)){
		list.push(value);
	}else{
		let index = list.indexOf(value);
		if (index > -1) {
		  list.splice(index, 1);
		}
	}
	
	filters[type] = list;
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
			filters.rating = i + 1;
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


