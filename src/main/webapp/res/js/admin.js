function runVisitsReport(){
	let addrress = "Admin";
	let data = "visits=true";
	loading();
	ajaxPOST(addrress, data, function(response){
		let json_data = JSON.parse(response.responseText);
		createDataTableHTML(json_data);
	});
}

function runSalesReport(){
	let addrress = "Admin";
	let data = "sales=true";
	loading();
	ajaxPOST(addrress, data, function(response){
		let json_data = JSON.parse(response.responseText);
		createDataTableHTML(json_data);
	});
}

function createDataTableHTML(data) {
  // Get the keys from the first object in the array
  const keys = Object.keys(data[0]);

  // Create the HTML for the DataTable
  let html = '<table id="myTable" class="display"><thead><tr>';

  // Create header columns
  for (let i = 0; i < keys.length; i++) {
    html += '<th>' + keys[i] + '</th>';
  }

  html += '</tr></thead><tbody>';

  // Create rows
  for (let i = 0; i < data.length; i++) {
    html += '<tr>';

    for (let j = 0; j < keys.length; j++) {
      html += '<td>' + data[i][keys[j]] + '</td>';
    }

    html += '</tr>';
  }

  html += '</tbody></table>';
  
  		
  let page = document.querySelector(".page-wrapper ");
  page.innerHTML = html;

  // Initialize DataTable
   $('#myTable').DataTable({
  });
}

function loading(){
	let loading = ` <div class="d-flex align-items-center justify-content-center">
            <div class="text-center">
            <h1 class="display-5 fw-bold">Running Report...</h1>
                <div id="spinner" class="spinner-border text-danger my-2" role="status" 
					style="width: 4rem; height: 4rem;"> <span class="visually-hidden">Loading...</span> </div>
            </div>
        </div>`;
  let page = document.querySelector(".page-wrapper ");
  page.innerHTML = loading;
}