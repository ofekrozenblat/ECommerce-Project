
//varaibles
let selectedSort = 1;
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
	setSortFunctionality();
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
	sort(selectedSort);
}

/* FILTERS */
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
	let div_rating = document.getElementById("filter-rating");
	div_rating.setAttribute('value', 0);

	let stars_div = div_rating.children[0];
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
			div_rating.setAttribute('value', i + 1);
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

/* SROTING */
function setSortFunctionality() {
	const catalog_sort = document.getElementById("catalog-sort");
	sort(1)
	catalog_sort.addEventListener("change", function() {
		const selectedValue = this.value;
		selectedSort = selectedValue;
		if (selectedValue == 1) {
			sort(1)
		}
		if (selectedValue == 2) {
			sort(2)
		}
		if (selectedValue == 3) {
			sort(3)
		}
		if (selectedValue == 4) {
			sort(4)
		}
	});
}


function sort(value) {
	let catalog_lists = document.querySelector(".item-listing");
	const items = Array.from(catalog_lists.children);


	items.sort((a, b) => {
		// featured sorts by rating
		if(value == 1){
			const A = a.getAttribute("data-rating");
			const B = b.getAttribute("data-rating");
			return B - A;
		}
		// price high to low
		if(value == 2){
			const A = a.getAttribute("data-price");
			const B = b.getAttribute("data-price");
			return B - A;
		}
		// price low to high
		if(value == 3){
			const A = a.getAttribute("data-price");
			const B = b.getAttribute("data-price");
			return A - B;
		}
		// sorts by name 
		if(value == 4){
			const A = a.getAttribute("data-name");
			const B = b.getAttribute("data-name");
			return A.localeCompare(B);
		}
	});

	items.forEach(item => catalog_lists.appendChild(item));
}
