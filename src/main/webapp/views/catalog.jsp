<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="utill.CatalogFilters" %>
<!DOCTYPE html>
<% 

//set filters
CatalogFilters filters = new CatalogFilters();
List<String> filterCategories = filters.getCategoryFilters();
List<String> filterBrands = filters.getBrandFilters();
List<String> filterColors = filters.getColorFilters();

List<String> priceFilters = filters.getPriceFilters();
double filterMaxPrice = Double.parseDouble(priceFilters.get(0));
double filterMinPrice = Double.parseDouble(priceFilters.get(1));

%>
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
			
			<div class="d-flex flex-row w-100 overflow-auto">
				<div class="w-25 ms-5 me-3 d-flex flex-column text-start overflow-auto mb-3" style="max-height: 100vh;">
					<h3 class="filter-title">Filters</h3>
					<hr>
					<h4 class="filter-title">Price</h4>
					 <div class="d-flex flex-row justify-content-between me-2">
						  <input placeholder="$Min" type="number" id="filter-min" min="${filterMinPrice}" max="${filterMaxPrice}">
						  <input placeholder="$Max" type="number" id="filter-max" min="${filterMinPrice}" max="${filterMaxPrice}">
					 </div>
					 <hr>
					 <h4 class="filter-title">Rating</h4>
					  <div id="filter-rating">
						<jsp:include page="item/star-rating.jsp">
							<jsp:param name="rating" value="0" />
						</jsp:include>
					  </div>
					 <hr>
					<h4 class="filter-title">Categories</h4>
					<% for(String value: filterCategories){
						%>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="${value}" id="category-filter-${value}">
							  <label class="form-check-label" for="flexCheckDefault">
							    <%= value %>
							  </label>
							</div>
						<%
					}%>
					<hr>
					<h4 class="filter-title">Brands</h4>
					<% for(String value: filterBrands){
						%>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="${value}" id="brand-filter-${value}">
							  <label class="form-check-label" for="flexCheckDefault">
							    <%= value %>
							  </label>
							</div>
						<%
					}%>
					<hr>
					<h4 class="filter-title">Colors</h4>
					<% for(String value: filterColors){
						%>
							<div class="form-check">
							  <input class="form-check-input" type="checkbox" value="${value}" id="color-filter-${value}">
							  <label class="form-check-label" for="flexCheckDefault">
							    <%= value %>
							  </label>
							</div>
						<%
					}%>
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