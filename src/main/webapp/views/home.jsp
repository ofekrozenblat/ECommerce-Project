<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>

<%@ include file="/views/main-layout/head.html" %>

<link rel="StyleSheet" href="res/css/home.css" type="text/css"></link>
<link rel="StyleSheet" href="res/css/item-listing.css" type="text/css"></link>

</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/views/main-layout/nav.jsp"/>


<div class="page-wrapper overflow-hidden">

	<div class="home-header card text-white">
		<img src="res/img/home_background.jpg" alt="..." class="img-fluid" >
		<div class="card-img-overlay home-header-buttons">
		    <a href="Catalog" class="home-header-button"><span>BROWSE CATALOG</span></a>
		    <a class="home-header-button"><span>BEST SELLERS</span></a>
	  </div>
	</div>
	
	<div class="main-content mb-0">
		<div class="content-section">
			<div class="content-section-title"> FEATURED GLASSES</div>
			<jsp:include page="item/item-listing.jsp" >
			     <jsp:param name="list_amount" value="${featured_count}" />
			</jsp:include>
		</div>
		<div class="content-section">
			<div class="content-section-title"> NEW ARRIVALS </div>
			<jsp:include page="item/item-listing.jsp" >
			     <jsp:param name="list_amount" value="${new_arrivals_count}" />
			</jsp:include>
		</div>
	</div>
	
</div>

<%@ include file="/views/main-layout/footer.html" %>

</body>
</html>