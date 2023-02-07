<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REAGAIL</title>
<jsp:include page="main-layout/head.html"/>
<link rel="StyleSheet" href="res/css/home.css" type="text/css"></link>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="main-layout/nav.html" />

<div class="page-wrapper overflow-hidden">

	<div class="home-header card text-white">
		<img src="res/img/home_background.jpg" alt="..." class="img-fluid" >
		<div class="card-img-overlay home-header-buttons">
		    <div class="home-header-button"><span>BROWSE CATALOG</span></div>
		    <div class="home-header-button"><span>BEST SELLERS</span></div>
	  </div>
	</div>
	
</div>

<jsp:include page="main-layout/footer.html" />

</body>
</html>