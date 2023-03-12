<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark vh-100 me-3" style="width: 280px;">
    <a href="Admin" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">Admin Panel</span>
    </a>
    <hr>
    <span class="fs-5 text-center">Run Reports</span>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <button onclick="runVisitsReport()" class="btn btn-custom w-100 my-2" aria-current="page">
          Visits
        </button>
      </li>
       <li class="nav-item">
        <button onclick="runSalesReport()" class="btn btn-custom w-100 my-2" aria-current="page">
          Sales
        </button>
      </li>
    </ul>
    <hr>
    <div class="dropdown">
    <a class="nav-link active text-white icon-nav-item dropdown-toggle"
		type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
		aria-expanded="false"> <span
		class="material-icons-outlined icon-30">account_circle</span> <span
		class="icon-nav-item-text">${username}</span>
	</a>
      <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="Logout">Sign out</a></li>
         <li><a class="dropdown-item" href="Home">Back Home</a></li>
      </ul>
    </div>
  </div>