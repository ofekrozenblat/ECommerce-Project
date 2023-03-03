<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="utill.SessionManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/item-detail.css" type="text/css"></link>
<link rel="StyleSheet" href="res/css/item-listing.css" type="text/css"></link>
<script type="text/javascript" src="res/js/item-detail.js"></script>

</head>
<body class="d-flex flex-column min-vh-100">

	<jsp:include page="/views/main-layout/nav.jsp"/>
	<jsp:include page="item-write-review.jsp"/>

	<div class="page-wrapper overflow-hidden d-flex flex-column">
		<div class="m-auto w-75">
			<div class="card mt-3 w-100">
			
				<!--DETAIL SECTION -->
				<div class="info-container">
					<img src="res/img/glasses-default.png" class="img-fluid" alt="">
					<div class="col">
						<div class="card-block px-3 py-3 d-flex flex-column">
							<h4 class="card-title mb-1 fw-bold">${name}</h4>
							<p class="card-text text-muted">${description}</p>

							<div class="d-flex flex-row flex-wrap align-items-center mb-2">
								<jsp:include page="star-rating.jsp">
									<jsp:param name="rating" value="${rating}" />
								</jsp:include>
								<span class="rating-count">(${review_count} Reviews)</span>
							</div>
							<h4 class="card-title mb-5 fw-bold ps-1">$${price}</h4>
							<button onclick="addToCart()" class="btn btn-custom-round w-50 mx-auto"> Add to Cart </button>
						</div>
					</div>
				</div>
				
				<!--  DESCRIPTION SECTION -->
				<hr></hr>
				<h4 class="mb-2 fw-bold ms-2">DETAILS</h4>
				<p class="ms-2 me-5">Color: ${color}</p>
				<p class="ms-2 me-5">Brand: ${brand}</p>
				<p class="ms-2 me-5">Category: ${category}</p>
				<a href="" class="btn btn-custom-round w-25 mx-auto">Try them on!</a>
				
				<!--  REVIEWS SECTION -->
				<hr></hr>
				<h4 class="mb-3 ms-2 fw-bold">REVIEWS</h4>
				<div class="mb-3 ms-2 me-5 d-flex flex-row justify-content-between">
				<% 
				SessionManager sm = (SessionManager) request.getSession().getAttribute(SessionManager.SESSION_MANAGER);
				boolean is_Auth = sm.isAuth();
				if(is_Auth){
					%>
						<button type="button" class="btn btn-custom-round  w-25" data-bs-toggle="modal" data-bs-target="#write_review_modal">
						Write a review
						</button>
					<%
					}
				else{
					%>
					<div class="w-25"></div>
					<%
				}
				%>
					<div class="d-flex flex-row w-25">
							<select id="rating-sort" class="form-select">
								<option value=1 class="sort-option" selected>Latest</option>
								<option value=2 class="sort-option">Rating: High to Low</option>
								<option value=3 class="sort-option">Rating: Low to High</option>
							</select>
					</div>
				</div>
				
				<div id="review-list" class="d-flex flex-row flex-wrap justify-content-center">
					<jsp:include page="item-review-listing.jsp"/>
				</div>
				
				<!--  RECOMMENDATION SECTION -->
				<hr></hr>
				<h5 class="mb-2 fw-bold ms-2">Customers also viewed</h5>
				<div class="text-center">
					<jsp:include page="item-listing.jsp">
						<jsp:param name="list_name" value="recommendation_list" />
					</jsp:include>
				</div>

			</div>
		</div>
	</div>

	<%@ include file="/views/main-layout/footer.html"%>

</body>
</html>