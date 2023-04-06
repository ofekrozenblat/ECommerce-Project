document.addEventListener('DOMContentLoaded', function() {
	const searchbar = document.getElementById('searchbar');
	let timer;

	// Waits for user to stop typing for x time before running the search 
	searchbar.addEventListener('input', function() {
		clearTimeout(timer);
		timer = setTimeout(function() {
			let input = searchbar.value;
			search(input);
		}, 500); // the delay before executing the function
	});

	searchbar.addEventListener('focus', function() {
		let input = searchbar.value;
		if (input != "") {
			search(input);
		}

		blurPage();
	});

	searchbar.addEventListener('blur', function() {
		// Need to set timeout here because a user might try to click a link 
		// in the search results, so set a delay before removing the results.
		setTimeout(function() {
			let searchResults = document.getElementById("searchResults");
			searchResults.innerHTML = "";
		}, 200);
		removeBlur();
	});

});

function search(input) {
	let data = `input=${input}`;
	let address = "Search"

	let searchResults = document.getElementById("searchResults");
	searchResults.style.width = "100%";
	searchResults.style.background = "white";
	searchResults.style.position = "absolute";
	searchResults.style.top = "100%";
	searchResults.style.zIndex = '9999';
	searchResults.style.textAlign = "center";

	let htmlContent = `<div class="spinner-border text-danger my-2" role="status"> 
	<span class="mx-auto visually-hidden">Loading...</span></div>`
	searchResults.innerHTML = htmlContent;

	ajaxGET(address, data, function(response) {
		if (response.getResponseHeader("success")) {
			let results = JSON.parse(response.responseText);

			if (results.length <= 0) {
				searchResults.innerHTML = "No results";
				return;
			}

			let htmlContent = `<ul class="list-group" >`;

			results.forEach(function(result) {
				htmlContent += `<li 
				class="cursor-pointer list-group-item search-result-item" 
				onclick="redirect('Item_detail?item_id=${result.id}')"
				>
				
				${result.name} $${result.price}</li>`;
			});

			htmlContent += "</ul>";
			searchResults.style.textAlign = "left";
			searchResults.innerHTML = htmlContent;
		} else {
			searchResults.innerHTML = "No results";
		}
	});
}

function blurPage() {
	// Make sure Navbar isn;t blured 
	const nav = document.querySelector('.navbar');
	nav.style.zIndex = '9998';

	// Create a new div element
	const blurDiv = document.createElement('div');

	// Set the position, size, and styling of the div
	blurDiv.style.position = 'fixed';
	blurDiv.style.top = '0';
	blurDiv.style.left = '0';
	blurDiv.style.width = '100%';
	blurDiv.style.height = '100%';
	blurDiv.style.zIndex = '9997';
	blurDiv.style.background = 'rgba(0, 0, 0, 0.5)';
	blurDiv.style.backdropFilter = 'blur(5px)';

	// Append the div to the body element
	document.body.appendChild(blurDiv);
}

function removeBlur() {
	// Get the blur div element
	const blurDiv = document.querySelector('div[style*="backdrop-filter"]');

	// Remove the blur div from the DOM if it exists
	if (blurDiv) {
		blurDiv.parentNode.removeChild(blurDiv);
	}
}