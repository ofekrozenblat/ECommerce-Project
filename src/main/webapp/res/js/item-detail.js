
window.onload = function() {
	setWriteReviewRatingFunctionality();
	setRatingSortFunctionality();
};


function setWriteReviewRatingFunctionality() {
	let review_rating = document.getElementById("write-review-rating");
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

function setRatingSortFunctionality(){
	const rating_sort = document.getElementById("rating-sort");
	sortLatest();
	rating_sort.addEventListener("change", function() {
	  const selectedValue = this.value;
	  if(selectedValue == 1){
		  sortLatest();
	  }
	  if(selectedValue == 2){
		  sortRatingHighToLow();
	  }
	   if(selectedValue == 3){
		  sortRatingLowToHigh();
	  }
	});
}

function sortLatest(){
	let review_list = document.getElementById("review-list");
	const reviews = Array.from(review_list.children);
	
	
	reviews.sort((a, b) => {
	  const dateA = new Date(a.getAttribute("data-date"));
	  const dateB = new Date(b.getAttribute("data-date"));
	  return dateB - dateA;
	});
	
	reviews.forEach(review => review_list.appendChild(review));
}

function sortRatingLowToHigh(){
	let review_list = document.getElementById("review-list");
	const reviews = Array.from(review_list.children);
	
	
	reviews.sort((a, b) => {
	  const dateA = new Date(a.getAttribute("data-rating"));
	  const dateB = new Date(b.getAttribute("data-rating"));
	  return dateA - dateB;
	});
	
	reviews.forEach(review => review_list.appendChild(review));
}

function sortRatingHighToLow(){
	let review_list = document.getElementById("review-list");
	const reviews = Array.from(review_list.children);
	
	
	reviews.sort((a, b) => {
	  const dateA = new Date(a.getAttribute("data-rating"));
	  const dateB = new Date(b.getAttribute("data-rating"));
	  return dateB - dateA;
	});
	
	reviews.forEach(review => review_list.appendChild(review));
}

function submitReview(){
	let title = document.getElementById("write-review-title").value;
	let description = document.getElementById("write-review-description").value;
	let rating = document.getElementById("write-review-rating").getAttribute("value");
	
	
	// TO DO: SEND DATA TO BACKEND

	let address = "Item_detail"
	let data = `new-review=true&title=${title}&description=${description}&rating=${rating}`;
	
	ajaxPOST(address, data, function(){
		successfullySubmited();
	})
}


function successfullySubmited(){
	let write_review_modal = document.getElementById("write_review_modal");
	
	let body = write_review_modal.querySelector(".modal-body");
	let submit_button = write_review_modal.querySelector(".btn-custom");
	body.innerHTML = "Thank you for the review!";
	submit_button.style.display = "none";
}