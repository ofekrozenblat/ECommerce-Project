<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL - Catalog</title>

<%@ include file="/views/main-layout/head.html"%>

<link rel="StyleSheet" href="res/css/catalog.css" type="text/css"></link>
<link rel="StyleSheet" href="res/css/item-listing.css" type="text/css"></link>
<script type="text/javascript" src="res/js/catalog.js"></script>
</head>
<body class="d-flex flex-column min-vh-100">

	<jsp:include page="/views/main-layout/nav.jsp"/>

	<div class="page-wrapper overflow-hidden">

		<header class="bg-dark py-5 mt-3">
			<div class="container px-4 px-lg-5 my-5">
				<div class="text-center text-white">
					<h1 class="display-4 fw-bolder">Shop Glasses</h1>
					<p class="lead fw-normal text-white-50 mb-0">Shop the Latest
						Trends: Get Your Perfect Look Today!</p>
				</div>
			</div>
		</header>

		<div class="catalog-container mt-3 d-flex flex-column">
		
			<div class="sort ms-auto me-5 d-flex flex-row">
				<span> Sorty By: </span> <select class="form-select">
					<option class="sort-option" selected>Featured</option>
					<option class="sort-option">Price: High to Low</option>
					<option class="sort-option">Price: Low to High</option>
					<option class="sort-option" value="1">Name</option>
				</select>
			</div>
			
			<div class="d-flex flex-row w-100">
				<div class="w-25 ms-5 me-3 d-flex flex-column text-start">
					<h3 class="filter-title">Filters</h3>
				</div>
				<div class="w-75 me-5 ms-auto text-center">
					<div id="catalog_list">
						<jsp:include page="item/item-listing.jsp">
							<jsp:param name="list_name" value="catalog_list" />
						</jsp:include>
					</div>
					<button onclick="loadMore()" id="load_more" class="btn btn-danger mt-3 mb-3">Show More</button>
				</div>
			</div>
			
		</div>

	</div>

	<%@ include file="/views/main-layout/footer.html"%>

</body>
</html>