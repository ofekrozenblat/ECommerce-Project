<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html"%>
<link rel="StyleSheet" href="res/css/item-detail.css" type="text/css"></link>

</head>
<body class="d-flex flex-column min-vh-100">

	<%@ include file="/views/main-layout/nav.html"%>


	<div class="page-wrapper overflow-hidden d-flex flex-column">
		<div class="m-auto w-75">
			<div class="card mt-3 w-100">
			
				<div class="info-container">
					<img src="res/img/glasses-default.png" class="img-fluid" alt="">
					<div class="col">
						<div class="card-block px-3 py-3 d-flex flex-column">
							<h4 class="card-title mb-1 fw-bold">Item Name</h4>
							<p class="card-text text-muted">Bold and colorful, these glasses are sure to make a statement.</p>
							
							<div class="d-flex flex-row flex-wrap align-items-center mb-2">
								<jsp:include page="item-rating.jsp">
									<jsp:param name="rating" value="3" />
								</jsp:include> 
								<span class="rating-count">(123 Ratings)</span>
							</div>
							<h4 class="card-title mb-5 fw-bold ps-1">$999</h4>
							<a href="" class="btn add-to-cart-btn w-50 mx-auto">
								Add to Cart
							</a>
						</div>
					</div>
				</div>
				
				<hr></hr>
				<h4 class="mb-1 fw-bold ms-2">DESCRIPTION</h4>
				<p class="ms-2 me-5">
				Luxury glasses are high-end eyewear that are crafted from premium materials and designed with meticulous attention to detail. 
				These glasses are often adorned with exquisite embellishments such as precious metals, Swarovski crystals, or intricate engraving.
				</p>
				
				<hr></hr>
				<h4 class="mb-1 fw-bold ms-2">REVIEWS</h4>
			</div>
		</div>
	</div>

	<%@ include file="/views/main-layout/footer.html"%>

</body>
</html>