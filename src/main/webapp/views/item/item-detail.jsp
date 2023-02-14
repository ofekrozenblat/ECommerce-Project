<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/item-detail.css" type="text/css"></link>
<link rel="StyleSheet" href="res/css/item-listing.css" type="text/css"></link>

</head>
<body class="d-flex flex-column min-vh-100">

	<%@ include file="/views/main-layout/nav.html"%>
	<jsp:include page="item-write-review.jsp"/>

	<div class="page-wrapper overflow-hidden d-flex flex-column">
		<div class="m-auto w-75">
			<div class="card mt-3 w-100">
			
				<!--DETAIL SECTION -->
				<div class="info-container">
					<img src="res/img/glasses-default.png" class="img-fluid" alt="">
					<div class="col">
						<div class="card-block px-3 py-3 d-flex flex-column">
							<h4 class="card-title mb-1 fw-bold">Item Name</h4>
							<p class="card-text text-muted">Bold and colorful, these
								glasses are sure to make a statement.</p>

							<div class="d-flex flex-row flex-wrap align-items-center mb-2">
								<jsp:include page="star-rating.jsp">
									<jsp:param name="rating" value="${rating}" />
								</jsp:include>
								<span class="rating-count">(${review_count} Reviews)</span>
							</div>
							<h4 class="card-title mb-5 fw-bold ps-1">$999</h4>
							<a href="" class="btn btn-custom-round w-50 mx-auto"> Add to Cart </a>
						</div>
					</div>
				</div>
				
				<!--  DESCRIPTION SECTION -->
				<hr></hr>
				<h4 class="mb-1 fw-bold ms-2">DESCRIPTION</h4>
				<p class="ms-2 me-5">Luxury glasses are high-end eyewear that
					are crafted from premium materials and designed with meticulous
					attention to detail. These glasses are often adorned with exquisite
					embellishments such as precious metals, Swarovski crystals, or
					intricate engraving.</p>
				<a href="" class="btn btn-custom-round w-25 mx-auto">Try them on!</a>
				
				<!--  REVIEWS SECTION -->
				<hr></hr>
				<h4 class="mb-3 ms-2 fw-bold">REVIEWS</h4>
				<div class="mb-3 ms-2 me-5 d-flex flex-row justify-content-between">
					<button type="button" class="btn btn-custom-round  w-25" data-bs-toggle="modal" data-bs-target="#write_review_modal">
					Write a review
					</button>
					<div class="d-flex flex-row w-25">
							<select class="form-select">
								<option class="sort-option" selected>Latest</option>
								<option class="sort-option">Rating: High to Low</option>
								<option class="sort-option">Rating: Low to High</option>
							</select>
					</div>
				</div>
				
				<div class="d-flex flex-row flex-wrap justify-content-center">
					<%
						for(int i = 0; i < 5; i++)
						{%>
					<jsp:include page="item-review.jsp">
						<jsp:param name="rating" value="3" />
						<jsp:param name="date" value="02/13/2023" />
					</jsp:include>
					<%}
					%>
				</div>
				<button class="btn btn-custom-round mt-3 mb-3 mx-auto w-25">Show
					more reviews</button>
				
				<!--  RECOMMENDATION SECTION -->
				<hr></hr>
				<h5 class="mb-2 fw-bold ms-2">Customers also viewed</h5>
				<div class="text-center">
					<jsp:include page="item-listing.jsp">
						<jsp:param name="list_amount" value="4" />
					</jsp:include>
				</div>

			</div>
		</div>
	</div>

	<%@ include file="/views/main-layout/footer.html"%>

</body>
</html>